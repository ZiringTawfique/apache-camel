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

import static net.gcicom.order.processor.common.AppConstants.TOTAL_RECORD_COUNT;
import static net.gcicom.order.processor.service.ValidationTypes.RECORD_PROCESSING_ERROR;

import java.awt.print.Book;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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
public class CDRProcessorErrorHandler {

	
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
		
		
		//CSV file header
		private static final String FILE_HEADER = "Actioncode,ItemType,CustomerName,Account Number,NodeName,OrderNumber,ServiceCode,BillingReference,Description,EventTariffName,GCISalesManager,CustomerServiceStartDate,CustomerServiceEndDate,SupplierContractStartDate,SupplierContractEndDate,CustomerContractStartDate,CustomerContractEndDate,CustomerSiteName,CustomerCustomReference,CustomerCostCentre,CustomerPONumber,InstallationPostCode,SupplierOrderNumber,SupplierServiceReference,ProductCode,Description,CustomerReference,OrderNumber,Quantity,ChargeFrequency,UnitCostToGCI,UnitChargeToCustomer,TaxTypeFlag,ChargeStartDate,ChargeCeaseDate,ChargeBilledUntilDate,SupplierContractStartDate,SupplierContractEndDate,CustomerContractStartDate,CustomerContractEndDate,ChargeID";
		
	private Logger logger = LoggerFactory.getLogger(CDRProcessorErrorHandler.class);
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
			 
			writeExcelFile(chargeImportDtoList,errorFileLocation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//  writeCsvFile(data);
		logger.error("Handled event has following error \n", data.size());
	}
	
	
	public static void writeCsvFile(Map data) {
	
		FileWriter fileWriter = null;				
		try {
			fileWriter = new FileWriter("NEW_ERRORED_FILE");

			//Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());
			
			//Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);
			
			//Write a new student object list to the CSV file
			Iterator iterator = data.keySet().iterator();
		    while (iterator.hasNext()){
		    	int key = (Integer) iterator.next();
		        String occurrence = (String) data.get(key);
		          fileWriter.append(occurrence);
			}

			
			
			System.out.println("CSV file was created successfully !!!");
			
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
			}
			
		}
	}
	

     		
        	 public static void  writeExcelFile(List<ChargeImportDto> chargeImportDtoList,String errorFileLocation) throws IOException {
        		 
        		 XSSFWorkbook  workbook = new XSSFWorkbook();
        		 XSSFSheet  sheet = workbook.createSheet("Billing reference failed validation");
        		
        		
        		    int rowCount = 0;
        		    String excelFilePath = errorFileLocation+"ErrorListOFBillingReference.xlsx";
        		    Row row = sheet.createRow(++rowCount);
        		  //  row = sheet.createRow(++rowCount);
        		    writeHeader(row,sheet);
        		    
        		    for (ChargeImportDto chargeImportDto : chargeImportDtoList) {
        		         writeBook(chargeImportDto, row);
        		         row = sheet.createRow(++rowCount);
        		    }
        		 
        		    try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
        		        workbook.write(outputStream);
        		    }
        		}
        	 
        	 
        	 private static void writeBook(ChargeImportDto aBook, Row row) {
        		    Cell cell = row.createCell(1);
        		    if(aBook.getActionCode() != null)
        		    cell.setCellValue(aBook.getActionCode());
        		 
        		    cell = row.createCell(2);
        		    if(aBook.getItemType() != null)
        		    cell.setCellValue(aBook.getItemType());
        		 
        		    cell = row.createCell(3);
        		    if(aBook.getCustomerName() != null)
        		    cell.setCellValue(aBook.getCustomerName());
        		    
        		    cell = row.createCell(4);
        		    if(aBook.getAccountNumber() != null)
        		    cell.setCellValue(aBook.getAccountNumber());

        		    cell = row.createCell(5);
        		    if(aBook.getNodeName() != null)
        		    cell.setCellValue(aBook.getNodeName());
        		    
        		    cell = row.createCell(6);
        		    if(aBook.getOrderNumber() != null)
        		    cell.setCellValue(aBook.getOrderNumber());
        		    
        		    cell = row.createCell(7);
        		    if(aBook.getServiceCode() != null)
        		    cell.setCellValue(aBook.getServiceCode());
        		    
        		    cell = row.createCell(8);
        		    if(aBook.getBillingReference() != null)
        		    cell.setCellValue(aBook.getBillingReference());
        		    
        		    cell = row.createCell(9);
        		    if(aBook.getBillingReferenceDesc() != null)
        		    cell.setCellValue(aBook.getBillingReferenceDesc());
        		    
        		    cell = row.createCell(10);
        		    if(aBook.getEventTariffName() != null)
        		    cell.setCellValue(aBook.getEventTariffName());
        		    
        		    
        		    cell = row.createCell(11);
        		    if(aBook.getGciSalesManager() != null)
        		    cell.setCellValue(aBook.getGciSalesManager());
        		    
        		     cell = row.createCell(12);
        		     if(aBook.getCustomerServiceStartDate() != null)
        		    cell.setCellValue(aBook.getCustomerServiceStartDate().toString());
        		        
        		     
        		     
        		    cell = row.createCell(13);
        		    if(aBook.getCustomerServiceEndDate() != null)
        		    cell.setCellValue(aBook.getCustomerServiceEndDate().toString());
        		    
        		    cell = row.createCell(14);
        		    if(aBook.getSupplierContractStartDate() != null)
        		    cell.setCellValue(aBook.getSupplierContractStartDate());

        		    cell = row.createCell(15);
        		    if(aBook.getSupplierContractEndDate() != null)
        		    cell.setCellValue(aBook.getSupplierContractEndDate());
        		    
        		    cell = row.createCell(16);
        		    if(aBook.getCustomerContractStartDate() != null)
        		    cell.setCellValue(aBook.getCustomerContractStartDate());
        		    
        		    cell = row.createCell(17);
        		    if(aBook.getCustomerContractStartDate() != null)
        		    cell.setCellValue(aBook.getCustomerContractStartDate());
        		    
        		    cell = row.createCell(18);
        		    if(aBook.getCustomerContractStartDate() != null)
        		    cell.setCellValue(aBook.getCustomerContractStartDate());
        		    
        		    cell = row.createCell(19);
        		    if(aBook.getCustomerContractStartDate() != null)
        		    cell.setCellValue(aBook.getCustomerContractStartDate());
        		    
        		    cell = row.createCell(20);
        		    if(aBook.getCustomerContractStartDate() != null)
        		    cell.setCellValue(aBook.getCustomerContractStartDate());
        		    
        		    
        		    cell = row.createCell(13);
       		       if(aBook.getExceptionMessage() != null)
       		       cell.setCellValue(aBook.getExceptionMessage().toString());
        		}
		
		
        	 
			   private static  void writeHeader( Row row, XSSFSheet sheet) {
	        		 Set<String> keyset = excelHeader.keySet();
	        			int rownum = 0;
	        			for (String key : keyset) {
	        				 row = sheet.createRow(rownum++);
	        				Object [] objArr = excelHeader.get(key);
	        				int cellnum = 0;
	        				for (Object obj : objArr) {
	        					Cell cell = row.createCell(cellnum++);
	        					if(obj instanceof Date) 
	        						cell.setCellValue((Date)obj);
	        					else if(obj instanceof Boolean)
	        						cell.setCellValue((Boolean)obj);
	        					else if(obj instanceof String)
	        						cell.setCellValue((String)obj);
	        					else if(obj instanceof Double)
	        						cell.setCellValue((Double)obj);
	        				}
	        			}
	   
	     		}
        	
		
		
           }
	
	

