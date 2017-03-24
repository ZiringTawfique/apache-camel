/**
 * 
 */
package net.gcicom.order.processor;

import org.apache.camel.LoggingLevel;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.spring.SpringRouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.gcicom.order.processor.entity.input.ChargeImportDto;

import net.gcicom.order.processor.entity.output.ChargeImportDtoToGciChargeImportMapper;

import net.gcicom.order.processor.service.AlreadyProcessedFileException;
import net.gcicom.order.processor.service.Auditor;

import net.gcicom.order.processor.service.CDRProcessorErrorHandler;
import net.gcicom.order.processor.service.ChargeImportAggregator;

import net.gcicom.order.processor.service.GCIChargeImportService;

/**
 * A Simple Camel route builder to process AbzorbO2 CDR feed
 *
 */
@Component
public class ServiceOrderProcessor extends SpringRouteBuilder {
	
	
	Logger logger = LoggerFactory.getLogger(ServiceOrderProcessor.class);
	
	String HEADER = "Actioncode, ItemType, Account Number";
	
	@Value("${gci.service.order.file.in.location}")
	private String inFileLocation;
	
	@Value("${gci.service.order.file.out.location}")
	private String outFileLocation;
	
	@Value("${gci.abzorb2cdr.parallel.processing}")
	private boolean isParallelProcessing = true;
	
	@Value("${gci.abzorb2cdr.batch.size}")
	private Integer batchSize = 1000;
	
	@Value("${gci.route.tracing}")
	private boolean tracing = true;

	@Value("${gci.abzorb2cdr.initial.delay}")
	private Integer initDelay = 1000;
	
	@Value("${gci.abzorb2cdr.next.run.delay}")
	private Integer nextRunDelay = 1000;
	
	
	@Value("${gci.abzorb2cdr.file.name.pattern}")
	private String filePattern;
	
	@Value("${gci.isNoop}")
	private boolean isNoop = false;
	
	@Value("${gci.aggregation.time.out}")
	private Integer aggregationTimeOut = 1000;
	
	
	@Autowired
	private ChargeImportDtoToGciChargeImportMapper mapper;
	
	@Autowired
	private GCIChargeImportService service;
	
	
	//@Autowired
	//private Auditor auditor;
	
	@Autowired
	private ChargeImportAggregator cdrAggregator;
	
	
	@Override
	public void configure() throws Exception {
		getContext().setTracing(tracing);
		
		onException(Exception.class)
			.bean(CDRProcessorErrorHandler.class, "handleError")
			.to("direct:move-error-file");

		//route to get file and schedule it for parallel processing
		//delay in millisec for next polling 
		//In production make noop false
        from("file:" + inFileLocation + "?initialDelay="+ initDelay 
        		+ "&delay="+ nextRunDelay +"&include="+filePattern+"&noop="+isNoop+"&move=.success")
        	.onException(AlreadyProcessedFileException.class)
				.bean(CDRProcessorErrorHandler.class, "handleError")
        		.to("direct:move-error-file")
    		.end()
        	.log(LoggingLevel.INFO, logger, "START : Processing ${file:name} file")
    	//	.bean(auditor, "startEvent")
    		.bean(service, "validateMd5")
        	.split(body()
        			.tokenize("\n"))
        			.parallelProcessing()
        			.streaming()
        	.to("direct:save-to-database")
        	//.bean(auditor, "endEvent")
        	.end();
        
        //get the data and save in db
        from("direct:save-to-database")
	        .onException(IllegalArgumentException.class)
		    	.handled(true)
		   // 	.bean(auditor, "handleEventInvalidCdr")
		    .end()
			.filter(body().isNotEqualTo(constant(HEADER)))//need to filter header of cvs/
    		.unmarshal()
    			.bindy(BindyType.Csv, ChargeImportDto.class)
    			.bean(mapper, "convertToGCIChargeImport")
    			.aggregate(constant(true), cdrAggregator)
                .completionSize(batchSize)
                .completionTimeout(aggregationTimeOut)//just in case cvs rows are less than batch size
    			.bean(service, "addChargeImport")
    			.log(LoggingLevel.DEBUG, logger, "END : Add CVS rows to table.");
 
		from("direct:move-error-file")
			.errorHandler(deadLetterChannel("file:"+ outFileLocation + "/error"))
			.log(LoggingLevel.ERROR, logger, "END : Could not move file to error location.")
			.end();
	}
	
	


}
