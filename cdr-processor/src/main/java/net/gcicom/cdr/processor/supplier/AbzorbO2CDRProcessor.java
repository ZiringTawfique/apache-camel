/**
 * 
 */
package net.gcicom.cdr.processor.supplier;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.model.dataformat.BindyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.gcicom.cdr.processor.RouteNames;
import net.gcicom.cdr.processor.entity.input.AbzorbO2CDR;
import net.gcicom.cdr.processor.entity.input.BTOpenReachCDR;
import net.gcicom.cdr.processor.entity.mapper.Abzorbo2CDRToGciCDRMapper;
import net.gcicom.cdr.processor.service.CDRErrorHandler;

/**
 * A Simple Camel route builder to process AbzorbO2 CDR feed
 *
 */
@Component
public class AbzorbO2CDRProcessor extends BaseProcessor {
	
	
	private static final Logger LOG = LoggerFactory.getLogger(AbzorbO2CDRProcessor.class);
	
	private static final String HEADER = "Call Type, Customer CLI, Telephone Number, Call Date, Call Time, Duration, Mb, Description, Time Band, Salesprice, Extension, User, Department, Country of Origin, Network, Chargecode, Tariff, Mobile Class, Remote Network";
	
	@Value("${gci.abzorb2cdr.file.in.location}")
	private String inFileLocation;
	
	@Value("${gci.abzorb2cdr.timer}")
	private String cron;
	
	
	@Value("${gci.abzorb2cdr.file.name.pattern}")
	private String filePattern;
	
	@Value("${gci.abzorb2cdr.autostart}")
	private boolean autostart;
	
	@Autowired
	private CDRErrorHandler handler;

	@Autowired
	private Abzorbo2CDRToGciCDRMapper mapper;

	@Override
	public void configure() throws Exception {
		
		setAutostart(autostart);
		setInFileLocation(inFileLocation);
		setFilePattern(filePattern);
		setCron(cron);
		setMapper(mapper);
		super.configure();
		

		/*//1
        moveFileOnError(inFileLocation);
        
		//2
		pollFiles(inFileLocation, filePattern, cron);

        //3
        mapCSVRowToVendorCdr();
        
        //4
        addCdr(mapper);*/
		
	}
	
	

	
	/**Maps a csv file row to a {@link BTOpenReachCDR} and send it to next route for further processing
	 */
	@Override
	void mapCSVRowToVendorCdr() {
		
		LOG.debug("mapCSVRowToVendorCdr");
		
		String processorName = this.getClass().getCanonicalName();
		
		//get the data and save in db
		from(RouteNames.MAP_CSV_ROW_TO_VENDOR_CDR.concat(processorName))
		.description(RouteNames.MAP_CSV_ROW_TO_VENDOR_CDR.concat(processorName), 
				"This route convert each csv rows to a pojo and passes it to ".concat(RouteNames.ADD_CDR.concat(processorName)), null)
        .onException(IllegalArgumentException.class)
	        .handled(true)
	        .bean(handler, "handleEventInvalidCdr")
	        .useOriginalMessage()
	    .end()
		.choice()
			.when(new Predicate() {
				
				@Override
				public boolean matches(Exchange exchange) {
					
					return exchange.getIn().getBody(String.class).startsWith(HEADER) ? false : true;

				}
			})//need to filter header of csv/
			.unmarshal()
			.bindy(BindyType.Csv, AbzorbO2CDR.class)
			.to(RouteNames.ADD_CDR.concat(processorName));
      
	}
	

	
	


}
