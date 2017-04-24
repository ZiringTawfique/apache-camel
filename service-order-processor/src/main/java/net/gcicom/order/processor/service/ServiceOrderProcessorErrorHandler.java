package net.gcicom.order.processor.service;
import static net.gcicom.order.processor.config.AppProperties.ERROR_FILE_LOCATION;
import org.apache.camel.Exchange;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.gcicom.order.processor.entity.input.ChargeImportDto;
import net.gcicom.order.processor.entity.output.ExcelFileHelper;

import static net.gcicom.order.processor.common.AppConstants.TOTAL_RECORD_COUNT;
import static net.gcicom.order.processor.service.ValidationTypes.RECORD_PROCESSING_ERROR;

import java.awt.print.Book;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import org.springframework.core.env.Environment;

import static org.apache.camel.Exchange.FILE_NAME_CONSUMED;
/**
 * Custom error handler to handle unknown errors and do error auditing  
 *TODO need more work
 */
@Component
public class ServiceOrderProcessorErrorHandler {

	
	@Value("${gci.service.order.file.error.location}")
	private static String errorFileLocation;
	
	@Autowired
	Environment env;
	
	@Value("${gci.service.order.file.out.location}")
	private static String processedFileLocation;
	
	//Delimiter used in CSV file
		private static final String COMMA_DELIMITER = ",";
		private static final String NEW_LINE_SEPARATOR = "\n";
		
		static Map<String, Object[]> excelHeader = new HashMap<String, Object[]>();
		
		
		
		
	private Logger logger = LoggerFactory.getLogger(ServiceOrderProcessorErrorHandler.class);
	Map<Integer, String> data = new HashMap<>();
	List<ChargeImportDto> chargeImportDtoList= new ArrayList();
	ChargeImportDto chargeImportDtoWithException;
	//@Autowired
	//Auditor auditor;
	
	/**
	 * @param e
	 */
	public void handleError(Exchange e) {
		
		logger.info("Handle Event" + e);
	//	auditor.errorEvent(e);
		
	}
	
	
	/**
	 * @param e
	 */
	public void handleRecordAlreadyExists(Exchange e) {
		
		logger.info("Handle Event" + e);
		handleEvent(e,RECORD_PROCESSING_ERROR);
		
	}
	
public void invalidRecord(Exchange e) {
		
		logger.info("Handle Event" + e);
		handleEvent(e,RECORD_PROCESSING_ERROR);
		
	}
	
	/**
	 * @param exchange
	 * @param eventType
	 */
	private void handleEvent( Exchange exchange, final String eventType) {
		int i=0;
		 Integer count = exchange.getProperty("CamelLoopIndex", Integer.class);
		 Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
					
		 StringBuilder fileName = exchange.getIn().getHeader(FILE_NAME_CONSUMED, StringBuilder.class);
		 
		 chargeImportDtoWithException = (ChargeImportDto)exchange.getIn().getBody();
		 chargeImportDtoWithException.setExceptionMessage(cause.getMessage());
		 chargeImportDtoList.add(chargeImportDtoWithException);
		 
		 excelHeader.put("1",new Object[] {"Actioncode","ItemType","CustomerName","Account Number","NodeName","OrderNumber","ServiceCode","BillingReference","Description","EventTariffName",
		          "GCISalesManager","CustomerServiceStartDate","CustomerServiceEndDate","SupplierContractStartDate","SupplierContractEndDate","CustomerContractStartDate", 
		          "CustomerContractEndDate","CustomerSiteName","CustomerCustomReference","CustomerCostCentre","CustomerPONumber","InstallationPostCode","SupplierOrderNumber",
		          "SupplierServiceReference","ProductCode","Description","CustomerReference","OrderNumber","Quantity","ChargeFrequency","UnitCostToGCI","UnitChargeToCustomer",
		          "TaxTypeFlag","ChargeStartDate","ChargeCeaseDate","ChargeBilledUntilDate","SupplierContractStartDate","SupplierContractEndDate","CustomerContractStartDate",
		          "CustomerContractEndDate","ChargeID"});
		 // data.put(i++,  exchange.getIn().getBody().toString());


	/*	Throwable reason = exchange.getProperty(EXCEPTION_CAUGHT, Throwable.class);
		if (reason != null) {
			
			logger.error("Handled event has following error \n", reason);
			data.put("reason", reason.getMessage());
			data.put("stacktrace", MessageFormat.format("Detail stack trace {0}", reason));
			if (!(reason instanceof AlreadyProcessedFileException)) {
				
				data.put("cdr", exchange.getIn().getBody(String.class));
			}
			

		} 
	}
	*/
		/* int totalCount = (int)exchange.getIn().getHeader("TOTAL_RECORD_COUNT");
		  
	  int totalProcessedCount = (int)exchange.getIn().getHeader("TOTAL_RECORD_PROCESSED_COUNT");
		
		if(totalCount== totalCount){
			writeCsvFile(data);
		}*/
		 
		 try {
		
			String  errorFileLocation= env.getProperty(ERROR_FILE_LOCATION);
			 
			ExcelFileHelper.writeExcelFile(chargeImportDtoList,errorFileLocation,fileName.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//  writeCsvFile(data);
		logger.error("Handled event has following error \n", data.size());
	}
	 	 
        	
		
		
           }
	
	

