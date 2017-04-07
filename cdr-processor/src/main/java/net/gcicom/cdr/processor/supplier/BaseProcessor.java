package net.gcicom.cdr.processor.supplier;

import static net.gcicom.cdr.processor.RouteNames.ADD_CDR;
import static net.gcicom.cdr.processor.RouteNames.MAP_CSV_ROW_TO_VENDOR_CDR;
import static net.gcicom.cdr.processor.RouteNames.MOVE_FILE_ON_ERROR;
import static net.gcicom.cdr.processor.common.AppConstants.CDR_EVENT_FILE_ID;
import static org.apache.camel.Exchange.FILE_NAME_CONSUMED;

import org.apache.camel.LoggingLevel;
import org.apache.camel.spring.SpringRouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import net.gcicom.cdr.processor.entity.mapper.CDRMapper;
import net.gcicom.cdr.processor.service.AlreadyProcessedFileException;
import net.gcicom.cdr.processor.service.CDRAggregator;
import net.gcicom.cdr.processor.service.CDRErrorHandler;
import net.gcicom.cdr.processor.service.ChecksumValidator;
import net.gcicom.cdr.processor.service.GCICDRService;
import net.gcicom.cdr.processor.service.InvalidCDRException;
import net.gcicom.cdr.processor.service.ValidationFailedException;

@Component
public abstract class BaseProcessor extends SpringRouteBuilder {

	private static final Logger LOG = LoggerFactory.getLogger(BaseProcessor.class);
	
	@Value("${gci.route.tracing}")
	private boolean tracing = true;
	
	@Value("${gci.isNoop}")
	private boolean isNoop = false;
	
	@Value("${gci.initial.delay}")
	private Integer initDelay = 1000;
	
	@Value("${gci.batch.size}")
	private Integer batchSize = 1000;
	
	@Value("${gci.aggregation.time.out}")
	private Integer aggregationTimeOut = 1000;
	
	
	@Autowired
	private GCICDRService service;
	
	@Autowired
	private CDRErrorHandler handler;

	
	@Autowired
	private CDRAggregator cdrAggregator;
	
	@Autowired
	private ChecksumValidator chksum;
	
	private boolean autostart = true;
	
	private String fileLocation, filePattern, cron;
	
	private void checkRequired() {
		
		if(StringUtils.isEmpty(fileLocation) || StringUtils.isEmpty(filePattern) || StringUtils.isEmpty(cron) || mapper == null) {
			
			throw new IllegalArgumentException(String.format("Could not initialize %s processor", this.getClass().getCanonicalName()));
		}
	}

	public void setMapper(CDRMapper<?> t) {
		this.mapper = t;
	}

	public void setInFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public void setFilePattern(String filePattern) {
		this.filePattern = filePattern;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	private CDRMapper<?> mapper;
	
	public void setAutostart(boolean autostart) {
		this.autostart = autostart;
	}

	/**Moves files to given file location
	 * @param fileLocation
	 */
	private void moveFileOnError() {
		
		String processorName = this.getClass().getCanonicalName();
		
		from(MOVE_FILE_ON_ERROR + processorName)
		.description(MOVE_FILE_ON_ERROR + processorName, String.format("This route moves file to %s in case of error", fileLocation), null)
		.errorHandler(deadLetterChannel("file:"+ fileLocation + "/error")
		  .allowRedeliveryWhileStopping(false)
		  .maximumRedeliveries(0))
		.log(LoggingLevel.ERROR, LOG, "Moving file")
		.end();
	}
	
	/** Polls files based on given cron expression, file location and file pattern
	 * It is users responsibility to provide not empty valid method arguments. 
	 * Failure to supplied valid arguments will results in runtime failure 
	 * @param inFileLocation
	 * @param filePattern
	 * @param cronExpression
	 */
	private void pollFiles() {
		
		String processorName = this.getClass().getCanonicalName();


		//route to get file and schedule it for parallel processing
		//delay in millisec for next polling 
		//In production make noop false
        from("file:" + fileLocation + "?initialDelay=" + initDelay 
        		+ "&include=" + filePattern
        		+ "&exclude=.*\\.(zip$)|.*\\.(tar$)|.*\\.(gz$)"
        		+ "&noop=" + isNoop 
        		+ "&move=.success"
                + "&moveFailed=.error"
        		+ "&scheduler=spring&scheduler.cron=" + cron)
		.autoStartup(autostart)
		.description("Polling files ".concat(processorName), String.format("This route poll files based on given cron expression %s "
	    			+ "from %s for matching %s file name pattern", cron, fileLocation, filePattern), null)
    	.onException(AlreadyProcessedFileException.class)
			.bean(handler, "errorEvent")
    		.to(MOVE_FILE_ON_ERROR.concat(processorName))
		.end()
		.log(LoggingLevel.INFO, LOG, "START : Processing ${file:name} file")
    	.bean(handler, "startEvent")
		.bean(chksum, "validateMd5")
    	.split(body()
    			.tokenize("\n"))
    	.to(MAP_CSV_ROW_TO_VENDOR_CDR.concat(processorName))
    	.bean(handler, "endEvent")
    	.end();
        
	}
	
	/**Convert vendor specific CDR using {@link CDRMapper} to {@link GCICDR}
	 * and then insert {@link GCICDR} to database
	 * @param mapper
	 */
	@SuppressWarnings("unchecked")
	private void addCdr() {
		
		String processorName = this.getClass().getCanonicalName();

		 from(ADD_CDR.concat(processorName))
			.description(ADD_CDR + processorName, String.format("This route adds cdr records to database"), null)
			.onException(InvalidCDRException.class, ValidationFailedException.class, IllegalArgumentException.class)
				.handled(true)
				.bean(handler, "handleEventInvalidCdr")
				.useOriginalMessage()
				.logHandled(true)
				.logStackTrace(true)
			.end()
	       	.bean(mapper, "convertToGCICDR(*, ${header."+ CDR_EVENT_FILE_ID +"}, ${header."+ FILE_NAME_CONSUMED +"})" )
			.aggregate(constant(true), cdrAggregator)
	       	.completionSize(batchSize)
	       	.completionTimeout(aggregationTimeOut)//just in case cvs rows are less than batch size
			.bean(service, "addCDR")
			.log(LoggingLevel.DEBUG, LOG, "END : Add CVS rows to table.");
	}


	@Override
	public void configure() throws Exception {
		
		getContext().setTracing(tracing);
		
		onException(Exception.class)
			.logStackTrace(true)
		    .bean(handler, "errorEvent")
			.to(MOVE_FILE_ON_ERROR.concat(this.getClass().getCanonicalName()));
		
		checkRequired();
		
		/*actual business calls*/
		//1
        moveFileOnError();
        
		//2
		pollFiles();

        //3
        mapCSVRowToVendorCdr();
        
        //4
        addCdr();
		
	}
	
	/**This method implementation must ensure proper chaining of routes after mapping to CSV row to vendor
	 * specific CDR pojo. Chaining must be from {@link MAP_CSV_ROW_TO_VENDOR_CDR} to {@link ADD_CDR}
	 * 
	 */
	abstract void  mapCSVRowToVendorCdr();
	
}
