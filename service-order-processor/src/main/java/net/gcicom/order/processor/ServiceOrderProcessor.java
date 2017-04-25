/**
 * 
 */
package net.gcicom.order.processor;

import static net.gcicom.order.processor.RouteNames.MOVE_FILE_ON_ERROR;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Predicate;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.spring.SpringRouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.gcicom.order.processor.entity.input.ChargeImportDto;
import net.gcicom.order.processor.entity.output.ChargeImportDtoToBillingReference;
//import net.gcicom.order.processor.entity.output.ChargeImportDtoToGciChargeImportMapper;

import net.gcicom.order.processor.exception.AlreadyProcessedFileException;
//import net.gcicom.order.processor.service.Auditor;
import net.gcicom.order.processor.service.BillingReferenceAggregator;
import net.gcicom.order.processor.service.ServiceOrderProcessorErrorHandler;
//import net.gcicom.order.processor.service.ChargeImportAggregator;
import net.gcicom.order.processor.service.CustomerProductChargeAggregator;
import net.gcicom.order.processor.service.ExcelConverterBean;
import net.gcicom.order.processor.service.GCIChargeImportService;
import net.gcicom.order.processor.exception.InvalidRecordException;
import net.gcicom.order.processor.exception.RecordAlreadyExistsException;



/**
 * A Simple Camel route builder to process Service Order  feed
 *
 */
@Component
public class ServiceOrderProcessor extends SpringRouteBuilder {
	
	
	Logger logger = LoggerFactory.getLogger(ServiceOrderProcessor.class);
	
	String HEADER = "Actioncode, ItemType, CustomerName, Account Number, NodeName, OrderNumber, ServiceCode, BillingReference,"
			         + " Description, EventTariffName, GCISalesManager, CustomerServiceStartDate, CustomerServiceEndDate,SupplierContractStartDate,"
			         + "SupplierContractStartDate,SupplierContractEndDate,CustomerContractStartDate,CustomerContractEndDate,CustomerSiteName,"
			         + "CustomerCustomReference,CustomerCostCentre,	CustomerPONumber,InstallationPostCode,SupplierOrderNumber,SupplierServiceReference";


	
	@Value("${gci.service.order.file.in.location}")
	private String inFileLocation;
	
	@Value("${gci.service.order.file.out.location}")
	private String outFileLocation;
	
	@Value("${gci.service.order.parallel.processing}")
	private boolean isParallelProcessing = true;
	
	@Value("${gci.service.order.batch.size}")
	private Integer batchSize = 1000;
	
	@Value("${gci.route.tracing}")
	private boolean tracing = true;

	@Value("${gci.service.order.initial.delay}")
	private Integer initDelay = 1000;
	
	@Value("${gci.service.order.next.run.delay}")
	private Integer nextRunDelay = 1000;
	
	
	@Value("${gci.service.order.file.name.pattern}")
	private String filePattern;
	
	@Value("${gci.isNoop}")
	private boolean isNoop = false;
	
	@Value("${gci.aggregation.time.out}")
	private Integer aggregationTimeOut = 1000;
	
	
	//@Autowired
	//private ChargeImportDtoToGciChargeImportMapper mapper;
	
	
	@Autowired
	private ChargeImportDtoToBillingReference billingReferenceMapper;
	
	@Autowired
	private GCIChargeImportService service;
	
	@Autowired
	private ExcelConverterBean excelConverterBean;
	
	
	//@Autowired
	//private Auditor auditor;
	
	//@Autowired
	//private ChargeImportAggregator cdrAggregator;
	
	@Autowired
	private BillingReferenceAggregator billingReferenceAggregator;
	
	@Autowired
	private CustomerProductChargeAggregator customerProductChargeAggregator;
	
	
	@Override
	public void configure() throws Exception {
		getContext().setTracing(tracing);
	
		//Inital configuration 	
		onException(Exception.class)
		.logStackTrace(true)
			.bean(ServiceOrderProcessorErrorHandler.class, "handleError")
			.to(MOVE_FILE_ON_ERROR.concat(this.getClass().getCanonicalName()));

		
		
		//route to get file and schedule it for parallel processing
		//delay in millisec for next polling 
		//In production make noop false
		//TODO Need to add cronexpression
		//TODO Add description
        from("file:" + inFileLocation + "?initialDelay="+ initDelay 
        		+ "&delay="+ nextRunDelay +"&include="+filePattern+"&noop="+isNoop+"&move=.success")
        	//	+ "moveFailed=.error")
        	.onException(AlreadyProcessedFileException.class)
				.bean(ServiceOrderProcessorErrorHandler.class, "handleError")								 
        		.to("direct:move-error-file")
    		.end()
        	.log(LoggingLevel.INFO, logger, "START : Processing ${file:name} file")
    		//.bean(auditor, "startEvent")
    		//.bean(service, "validateMd5")
        	
        	.bean(service,"setRecordCount")
        	.bean(excelConverterBean,"processExcelData")
        	.split(body())
        	
            // .parallelProcessing()
        	//	.streaming()
        	.to("direct:save-to-database")
           //	.bean(auditor, "endEvent")
        	//.bean(billingReferenceAggregator, "")
        	.end();
        
        //get the data and save in db
        from("direct:save-to-database")
	        .onException(InvalidRecordException.class).useOriginalMessage()
		    	.handled(true)
		    	.bean(ServiceOrderProcessorErrorHandler.class, "invalidRecord")
		    	.log("Exception Thrown")
	//	    	.bean(auditor, "handleEventInvalidCdr")
		    .end()
			//.filter(body().isNotEqualTo(constant(HEADER)))//need to filter header of cvs/
		    
		    .choice()
			.when(new Predicate() {
				
				@Override
				public boolean matches(Exchange exchange) {					
					return exchange.getIn().getBody(String.class).startsWith(HEADER) ? false : true;
				}
			})//need to filter header of csv/
    		//.unmarshal()
    			//.bindy(BindyType.Csv, ChargeImportDto.class)    		
    		
    			.bean(billingReferenceMapper, "convertToBillingReference")
    		  // .bean(mapper, "convertToGCICDR(*, ${header."+ CDR_EVENT_FILE_ID +"}, ${header."+ FILE_NAME_CONSUMED +"})" )
    	      // .aggregate(constant(true), customerProductChargeAggregator)
              // .completionSize(batchSize)
             //  .completionTimeout(aggregationTimeOut)//just in case cvs rows are less than batch size
    	     //  .bean(service, "addBillingReference")
    			.log(LoggingLevel.DEBUG, logger, "END : Add CVS rows to table.");
 
		from("direct:move-error-file")
			.errorHandler(deadLetterChannel("file:"+ outFileLocation + "/error"))
			.log(LoggingLevel.ERROR, logger, "END : Could not move file to error location.")
			.end();
	}
	
	


}
