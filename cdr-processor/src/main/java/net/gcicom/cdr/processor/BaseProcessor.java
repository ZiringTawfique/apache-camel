package net.gcicom.cdr.processor;

import org.apache.camel.LoggingLevel;
import org.apache.camel.spring.SpringRouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.gcicom.cdr.processor.entity.output.CDRMapper;
import net.gcicom.cdr.processor.entity.output.GCICDR;
import net.gcicom.cdr.processor.service.AlreadyProcessedFileException;
import net.gcicom.cdr.processor.service.Auditor;
import net.gcicom.cdr.processor.service.CDRAggregator;
import net.gcicom.cdr.processor.service.CDRProcessorErrorHandler;
import net.gcicom.cdr.processor.service.GCICDRService;

@Component
public abstract class BaseProcessor extends SpringRouteBuilder {

	private static final Logger logger = LoggerFactory.getLogger(BaseProcessor.class);
	
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
	private Auditor auditor;

	
	@Autowired
	private CDRAggregator cdrAggregator;
	
	/**Moves files to given file location
	 * @param fileLocation
	 */
	public void moveFileOnError(final String fileLocation) {
		
		String processorName = this.getClass().getCanonicalName();
		
		from(RouteNames.MOVE_FILE_ON_ERROR + processorName)
		.description(RouteNames.MOVE_FILE_ON_ERROR + processorName, String.format("This route moves file to %s in case of error", fileLocation), null)
		.errorHandler(deadLetterChannel("file:"+ fileLocation + "/error")
			    .allowRedeliveryWhileStopping(false)
			    .maximumRedeliveries(0))
		.log(LoggingLevel.ERROR, logger, "Moving file")
		.end();
	}
	
	/** Polls files based on given cron expression, file location and file pattern
	 * It is users responsibility to provide not empty valid method arguments. 
	 * Failure to supplied valid arguments will results in runtime failure 
	 * @param inFileLocation
	 * @param filePattern
	 * @param cronExpression
	 */
	public void pollFiles(final String inFileLocation, final String filePattern, final String cronExpression) {
		
		String processorName = this.getClass().getCanonicalName();

		//route to get file and schedule it for parallel processing
		//delay in millisec for next polling 
		//In production make noop false
        from("file:" + inFileLocation + "?initialDelay=" + initDelay 
        		+ "&include=" + filePattern 
        		+ "&noop=" + isNoop 
        		+ "&move=.success"
                + "&moveFailed=.error"
        		+ "&scheduler=spring&scheduler.cron=" + cronExpression)
	    	.description("Polling files ".concat(processorName), String.format("This route poll files based on given cron expression %s "
	    			+ "from %s for matching %s file name pattern", cronExpression, inFileLocation, filePattern), null)
	    	.onException(AlreadyProcessedFileException.class)
				.bean(CDRProcessorErrorHandler.class, "handleError")
	    		.to(RouteNames.MOVE_FILE_ON_ERROR.concat(processorName))
			.end()
	    	.log(LoggingLevel.INFO, logger, "START : Processing ${file:name} file")
			.bean(auditor, "startEvent")
			.bean(service, "validateMd5")
	    	.split(body()
	    			.tokenize("\n"))
	    	.to(RouteNames.MAP_CSV_ROW_TO_VENDOR_CDR.concat(processorName))
	    	.bean(auditor, "endEvent")
	    	.end();
	}
	
	/**Convert vendor specific CDR using {@link CDRMapper} to {@link GCICDR}
	 * and then insert {@link GCICDR} to database
	 * @param mapper
	 */
	public void addCdr(@SuppressWarnings("rawtypes") final CDRMapper mapper) {
		
		String processorName = this.getClass().getCanonicalName();

		 from(RouteNames.ADD_CDR.concat(processorName))
			.description(RouteNames.ADD_CDR + processorName, String.format("This route adds cdr records to database"), null)
	       	.bean(mapper, "convertToGCICDR")
			.aggregate(constant(true), cdrAggregator)
	       	.completionSize(batchSize)
	       	.completionTimeout(aggregationTimeOut)//just in case cvs rows are less than batch size
			.bean(service, "addCDR")
			.log(LoggingLevel.DEBUG, logger, "END : Add CVS rows to table.");
	}


	@Override
	public void configure() throws Exception {
		
		getContext().setTracing(tracing);
		
		onException(Exception.class)
			.bean(CDRProcessorErrorHandler.class, "handleError")
			.to(RouteNames.MOVE_FILE_ON_ERROR.concat(this.getClass().getCanonicalName()));
		
	}
	
	/**This method implementation must ensure proper chaining of routes after mapping to CSV row to vendor
	 * specific CDR pojo. Chaining must be from {@link RouteNames.MAP_CSV_ROW_TO_VENDOR_CDR} to {@link RouteNames.ADD_CDR}
	 * 
	 */
	abstract void  mapCSVRowToVendorCdr();
	
	
}
