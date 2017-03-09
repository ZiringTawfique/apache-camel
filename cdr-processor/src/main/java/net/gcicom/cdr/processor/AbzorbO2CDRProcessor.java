/**
 * 
 */
package net.gcicom.cdr.processor;

import org.apache.camel.LoggingLevel;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.spring.SpringRouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.gcicom.cdr.processor.entity.input.AbzorbO2CDR;
import net.gcicom.cdr.processor.entity.output.Abzorbo2CDRToGciCDRMapper;
import net.gcicom.cdr.processor.service.Auditor;
import net.gcicom.cdr.processor.service.CDRAggregator;
import net.gcicom.cdr.processor.service.CDRProcessorErrorHandler;
import net.gcicom.cdr.processor.service.GCICDRService;
import net.gcicom.cdr.processor.service.InvalidCDRProcessor;


/**
 * A Simple Camel route builder to process AbzorbO2 CDR feed
 *
 */
@Component
public class AbzorbO2CDRProcessor extends SpringRouteBuilder {
	
	
	Logger logger = LoggerFactory.getLogger(AbzorbO2CDRProcessor.class);
	
	
	@Value("${gci.abzorb2cdr.file.in.location}")
	private String inFileLocation;
	
	@Value("${gci.abzorb2cdr.file.out.location}")
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
	private Abzorbo2CDRToGciCDRMapper mapper;
	
	@Autowired
	private GCICDRService service;
	
	@Autowired
	private InvalidCDRProcessor iProcessor;
	
	@Autowired
	private Auditor auditor;
	
	@Autowired
	private CDRAggregator cdrAggregator;
	
	
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
        		+ "&delay="+ nextRunDelay +"&include="+filePattern+"&noop="+isNoop+"&move=." + outFileLocation)
        	.log(LoggingLevel.INFO, logger, "START : Processing ${file:name} file")
    		.bean(auditor, "startEvent")
        	.split(body()
        			.tokenize("\n"))
        			.parallelProcessing()
        			.streaming()
        	.to("direct:save-to-database")
        	.end();
        
        //get the data and save in db
        from("direct:save-to-database")
	        .onException(IllegalArgumentException.class)
		    	.handled(true)
		    	.process(iProcessor)
		    .end()
    		.unmarshal()
    			.bindy(BindyType.Csv, AbzorbO2CDR.class)
    			.bean(mapper, "convertToGCICDR")
    			.aggregate(constant(true), cdrAggregator)
                .completionSize(batchSize)
                .completionTimeout(aggregationTimeOut)//just in case cvs rows are less than batch size
    			.bean(service, "addCDR")
    			.log(LoggingLevel.DEBUG, logger, "END : Add CVS rows to table.");
 
		from("direct:move-error-file")
			.to("file:"+ outFileLocation + "/error")
			.end();
	}
	
	


}
