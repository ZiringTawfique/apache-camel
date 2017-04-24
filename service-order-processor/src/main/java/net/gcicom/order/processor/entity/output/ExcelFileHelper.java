package net.gcicom.order.processor.entity.output;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.gcicom.order.processor.entity.input.ChargeImportDto;

public class ExcelFileHelper {

	static Map<String, Object[]> excelHeader = new HashMap<String, Object[]>();
	
	private static final String NEW_LINE_SEPARATOR = "\n";
	
	//CSV file header
   private static final String FILE_HEADER = "Actioncode,ItemType,CustomerName,Account Number,NodeName,OrderNumber,ServiceCode,BillingReference,Description,EventTariffName,GCISalesManager,CustomerServiceStartDate,CustomerServiceEndDate,SupplierContractStartDate,SupplierContractEndDate,CustomerContractStartDate,CustomerContractEndDate,CustomerSiteName,CustomerCustomReference,CustomerCostCentre,CustomerPONumber,InstallationPostCode,SupplierOrderNumber,SupplierServiceReference,ProductCode,Description,CustomerReference,OrderNumber,Quantity,ChargeFrequency,UnitCostToGCI,UnitChargeToCustomer,TaxTypeFlag,ChargeStartDate,ChargeCeaseDate,ChargeBilledUntilDate,SupplierContractStartDate,SupplierContractEndDate,CustomerContractStartDate,CustomerContractEndDate,ChargeID";
	

	 public static void  writeExcelFile(List<ChargeImportDto> chargeImportDtoList,String errorFileLocation,String fileName) throws IOException {
		 
		 XSSFWorkbook  workbook = new XSSFWorkbook();
		 XSSFSheet  sheet = workbook.createSheet("Billing reference failed validation");
		
		 excelHeader.put("1",new Object[] {"Actioncode","ItemType","CustomerName","Account Number","NodeName","OrderNumber","ServiceCode","BillingReference","Description","EventTariffName",
		          "GCISalesManager","CustomerServiceStartDate","CustomerServiceEndDate","SupplierContractStartDate","SupplierContractEndDate","CustomerContractStartDate", 
		          "CustomerContractEndDate","CustomerSiteName","CustomerCustomReference","CustomerCostCentre","CustomerPONumber","InstallationPostCode","SupplierOrderNumber",
		          "SupplierServiceReference","ProductCode","Description","CustomerReference","OrderNumber","Quantity","ChargeFrequency","UnitCostToGCI","UnitChargeToCustomer",
		          "TaxTypeFlag","ChargeStartDate","ChargeCeaseDate","ChargeBilledUntilDate","SupplierContractStartDate","SupplierContractEndDate","CustomerContractStartDate",
		          "CustomerContractEndDate","ChargeID"});
		 
		    int rowCount = 0;
		    String excelFilePath = errorFileLocation+fileName;
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
 		
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
		 LocalDateTime cellValue;
	        
	        
		       Cell  cell = row.createCell(0);
		    if(aBook.getActionCode() != null)
		    cell.setCellValue(aBook.getActionCode());
		 
		    cell = row.createCell(1);
		    if(aBook.getItemType() != null)
		    cell.setCellValue(aBook.getItemType());
		 
		    cell = row.createCell(2);
		    if(aBook.getCustomerName() != null)
		    cell.setCellValue(aBook.getCustomerName());
		    
		    cell = row.createCell(3);
		    if(aBook.getAccountNumber() != null)
		    cell.setCellValue(aBook.getAccountNumber());

		    cell = row.createCell(4);
		    if(aBook.getNodeName() != null)
		    cell.setCellValue(aBook.getNodeName());
		    
		    cell = row.createCell(5);
		    if(aBook.getOrderNumber() != null)
		    cell.setCellValue(aBook.getOrderNumber());
		    
		    cell = row.createCell(6);
		    if(aBook.getServiceCode() != null)
		    cell.setCellValue(aBook.getServiceCode());
		    
		    cell = row.createCell(7);
		    if(aBook.getBillingReference() != null)
		    cell.setCellValue(aBook.getBillingReference());
		    
		    cell = row.createCell(8);
		    if(aBook.getBillingReferenceDesc() != null)
		    cell.setCellValue(aBook.getBillingReferenceDesc());
		    
		    cell = row.createCell(9);
		    if(aBook.getEventTariffName() != null)
		    cell.setCellValue(aBook.getEventTariffName());
		    
		    
		    cell = row.createCell(10);
		    if(aBook.getGciSalesManager() != null)
		    cell.setCellValue(aBook.getGciSalesManager());
		    
		     cell = row.createCell(11);
		     if(aBook.getCustomerServiceStartDate() != null)
		     cell.setCellValue(aBook.getCustomerServiceStartDate().toString());
		   //   cellValue  = 	 LocalDateTime.parse(aBook.getCustomerServiceStartDate().toString());
		   //  cell.setCellValue(cellValue.format(formatter)); 
		     
		     
		    cell = row.createCell(12);
		    if(aBook.getCustomerServiceEndDate() != null)
		    cell.setCellValue(aBook.getCustomerServiceEndDate().format(formatter));
		    
		    cell = row.createCell(13);
		    if(aBook.getSupplierContractStartDate() != null)
		    cell.setCellValue(aBook.getSupplierContractStartDate().toString());

		    cell = row.createCell(14);
		    if(aBook.getSupplierContractEndDate() != null)
		    cell.setCellValue(aBook.getSupplierContractEndDate().toString());
		    
		    cell = row.createCell(15);
		    if(aBook.getCustomerContractStartDate() != null)
		    cell.setCellValue(aBook.getCustomerContractStartDate().toString());
		    
		    cell = row.createCell(16);
		    if(aBook.getCustomerContractEndDate() != null)
		    cell.setCellValue(aBook.getCustomerContractEndDate().toString());
		    
		    cell = row.createCell(17);
		    if(aBook.getCustomerSiteName() != null)
		    cell.setCellValue(aBook.getCustomerSiteName().toString());
		    
		    cell = row.createCell(18);
		    if(aBook.getCustomerCustomReference() != null)
		    cell.setCellValue(aBook.getCustomerCustomReference().toString());
		    
		    cell = row.createCell(19);
		    if(aBook.getCustomerCostCentre() != null)
		    cell.setCellValue(aBook.getCustomerCostCentre().toString());
		   
		    cell = row.createCell(20);
		    if(aBook.getCustomerPONumber() != null)
		    cell.setCellValue(aBook.getCustomerPONumber().toString());
		    
		    
		    cell = row.createCell(21);
		    if(aBook.getInstallationPostCode() != null)
		    cell.setCellValue(aBook.getInstallationPostCode().toString());
		    
		    
		    cell = row.createCell(22);
		    if(aBook.getSupplierOrderNumber() != null)
		    cell.setCellValue(aBook.getSupplierOrderNumber().toString());
		    
		    
		    cell = row.createCell(23);
		    if(aBook.getSupplierServiceReference() != null)
		    cell.setCellValue(aBook.getSupplierServiceReference().toString());
		    
		    
		    cell = row.createCell(24);
		    if(aBook.getProductCode() != null)
		    cell.setCellValue(aBook.getProductCode().toString());
		    
		    cell = row.createCell(25);
		    if(aBook.getDescription() != null)
		    cell.setCellValue(aBook.getDescription().toString());
		    
		   
		    cell = row.createCell(26);
		    if(aBook.getCustomerReference() != null)
		    cell.setCellValue(aBook.getCustomerReference().toString());
		    
		    cell = row.createCell(27);
		    if(aBook.getOrderNumber() != null)
		    cell.setCellValue(aBook.getOrderNumber().toString());
		    
		          		    
		    cell = row.createCell(28);
		    if(aBook.getQuantity() != null)
		    cell.setCellValue(aBook.getQuantity().toString());
		    
		    cell = row.createCell(29);
		    if(aBook.getChargeFrequency() != null)
		    cell.setCellValue(aBook.getChargeFrequency().toString());
		   
		    cell = row.createCell(30);
		    if(aBook.getUnitCostToGCI()!= null)
		    cell.setCellValue(aBook.getUnitCostToGCI().toString());
		    
		    
		    cell = row.createCell(31);
		    if(aBook.getUnitChargeToCustomer() != null)
		    cell.setCellValue(aBook.getUnitChargeToCustomer().toString());
		    
		    
		    cell = row.createCell(32);
		    if(aBook.getTaxTypeFlag() != null)
		    cell.setCellValue(aBook.getTaxTypeFlag().toString());
		    
		    
		    cell = row.createCell(33);
		    if(aBook.getChargeStartDate() != null)
		    cell.setCellValue(aBook.getChargeStartDate().toString());
		    
		    
		    cell = row.createCell(34);
		    if(aBook.getChargeCeaseDate() != null)
		    cell.setCellValue(aBook.getChargeCeaseDate().toString());
		    
		    cell = row.createCell(35);
		    if(aBook.getChargeBilledUntilDate() != null)
		    cell.setCellValue(aBook.getChargeBilledUntilDate().toString());
		    
		    
		    
		    cell = row.createCell(36);
		    if(aBook.getSupplierContractStartDate() != null)
		    cell.setCellValue(aBook.getSupplierContractStartDate().toString());
		    
		    cell = row.createCell(37);
		    if(aBook.getSupplierContractEndDate() != null)
		    cell.setCellValue(aBook.getSupplierContractEndDate().toString());
		    
		          		    
		    cell = row.createCell(38);
		    if(aBook.getCustomerContractStartDate() != null)
		    cell.setCellValue(aBook.getCustomerContractStartDate().toString());
		    
		    cell = row.createCell(39);
		    if(aBook.getCustomerContractEndDate() != null)
		    cell.setCellValue(aBook.getCustomerContractEndDate().toString());
		   
		    cell = row.createCell(40);
		    if(aBook.getChargeID()!= null)
		    cell.setCellValue(aBook.getChargeID().toString());
		    
		    
		    
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
		
}
