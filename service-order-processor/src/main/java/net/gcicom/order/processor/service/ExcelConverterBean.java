package net.gcicom.order.processor.service;

import static net.gcicom.order.processor.common.AppConstants.TOTAL_RECORD_COUNT;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import javax.xml.datatype.DatatypeFactory;
import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.draw.binding.ObjectFactory;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import net.gcicom.common.util.DateTimeUtil;
import net.gcicom.order.processor.entity.input.ChargeImportDto;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * Uses POI to convert an Excel spreadsheet to the desired JAXB XML format.
 */
@Component("excelConverterBean")
public class ExcelConverterBean {
    private final static Log log = LogFactory.getLog(ExcelConverterBean.class);

    public List<ChargeImportDto> processExcelData(@Body InputStream inputStream,Exchange ex) throws IOException {
    	
    	    List<ChargeImportDto> chargeImportDtoList = new ArrayList<>();    	    
    	 
    	    Workbook workbook = new XSSFWorkbook(inputStream);
    	    Sheet firstSheet = workbook.getSheetAt(0);
    	    Iterator<Row> iterator = firstSheet.iterator();
    	    int totalRowsCount = firstSheet.getPhysicalNumberOfRows();
    	    ex.getIn().setHeader(TOTAL_RECORD_COUNT, totalRowsCount);
    	    
    	    while (iterator.hasNext()) {
    	    	
    	    /*	Row row = iterator.next ();
    	    	if(row.getRowNum()==0 || row.getRowNum()==1){
    	    	       continue; //just skip the rows if row number is 0 or 1
    	    	      }*/
    	    	
    	        Row nextRow = iterator.next();
    	        if (nextRow.getRowNum() == 0 || nextRow.getRowNum()==1) {
                    continue;// skip first row, as it contains column names
                }
    	        Iterator<Cell> cellIterator = nextRow.cellIterator();
    	        ChargeImportDto chargeImportDto = new ChargeImportDto();
    	        
    	        
    	       
    	        
    	        SimpleDateFormat DtFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    	        String dateFormat = " MM/dd/uuuu HH:mm:ss";
    	      
    	    //    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX");
    	     //   DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat, Locale.UK)
            //            .withResolverStyle(ResolverStyle.STRICT);
    	        
    	        while (cellIterator.hasNext()) {
    	            Cell nextCell = cellIterator.next();
    	            int columnIndex = nextCell.getColumnIndex()  ;    	           
    	            
    	            switch (columnIndex) {
    	            
    	            case 0:
    	               chargeImportDto.setActionCode((String) getCellValue(nextCell));
    	                break;
    	                
    	            case 1:
    	            	chargeImportDto.setItemType((String) getCellValue(nextCell));  
    	                break;
    	                
    	            case 2:
    	            	chargeImportDto.setCustomerName((String) getCellValue(nextCell));    	            
    	                break;
    	                
    	            case 3:
    	            	chargeImportDto.setAccountNumber((String) getCellValue(nextCell));    	            	
    	            	break;
    	            	
    	            case 4:
    	            	chargeImportDto.setNodeName((String) getCellValue(nextCell));    	            	//Persisted  
    	                break;
    	                
    	            case 5:    	            	
    	            	chargeImportDto.setOrderNumber((String)getCellValue(nextCell));
    	                break;
	                 case 6:
     	            	chargeImportDto.setServiceCode((String) getCellValue(nextCell));    	            	//Persisted	
    	                break;
    	              
    	           
    	            case 7:
    	            	chargeImportDto.setBillingReference((String) getCellValue(nextCell));    	            //Persisted	
    	                break;
    	            case 8:
    	            	chargeImportDto.setBillingReferenceDesc((String) getCellValue(nextCell));    	         //Persisted 		
    	                break;
    	            case 9:
    	            	chargeImportDto.setEventTariffName((String) getCellValue(nextCell));    	            		
    	                break;
    	                         
    	                    
    	            case 10:
    	            	chargeImportDto.setGciSalesManager((String) getCellValue(nextCell));    	            		
    	            	 break;
    	          
    	            case 11:
    	        /*    	
    	          	  Date date=nextCell.getDateCellValue();
  	      	        System.out.println(DtFormat.format(date).toString());
  	            	chargeImportDto.setCustomerServiceStartDate(DtFormat.format(date).toString() );   */	 
    	            //	Date date=nextCell.getDateCellValue();
    	            	
    	            	DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
    	            	// Cell cell = sheet.getRow(i).getCell(0);
    	            	 String dateAsString = formatter.formatCellValue(nextCell); //R
    	            	 SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yy");
    	            	 
    	            	LocalDateTime customerServiceStartDateLocalDateTime=DateTimeUtil.convertStringToLocalDateTime(dateAsString,dateFormatter);
    	            //	 LocalDateTime   customerServiceStartDateLocalDateTime = LocalDateTime.parse(date.toString(),dateTimeFormatter);
    	            	 chargeImportDto.setCustomerServiceStartDate(customerServiceStartDateLocalDateTime);
    	            	
    	                break;
    	          /* case 12:
    	            	chargeImportDto.setCustomerServiceEndDate((String) getCellValue(nextCell));    	            		
    	                break;
    	            case 13:
    	            	chargeImportDto.setSupplierContractStartDate((String) getCellValue(nextCell));    	            		
    	                break;
    	            case 14:
    	            	chargeImportDto.setSupplierContractEndDate((String) getCellValue(nextCell));    	            		
    	                break;
    	                
    	                     
    	            case 16:
    	            	chargeImportDto.setCustomerContractStartDate((String) getCellValue(nextCell));    	            		
    	                break;
    	            case 17:
    	            	chargeImportDto.setCustomerContractEndDate((String) getCellValue(nextCell));    	            		
    	                break;
    	            case 18:
    	            	chargeImportDto.setCustomerSiteName((String) getCellValue(nextCell));    	            		
    	                break;
    	            case 19:
    	            	chargeImportDto.setCustomerCustomReference((String) getCellValue(nextCell));    	            		
    	                break;
    	            case 20:
    	            	chargeImportDto.setCustomerCostCentre((String) getCellValue(nextCell));    	            		
    	                break;
    	                
    	                
    	                //TODO CHeck the value
    	            case 21:
    	            	chargeImportDto.setChargeOrderNumber((String) getCellValue(nextCell));    	            		
    	                break;
    	            case 22:
    	            	chargeImportDto.setInstallationPostCode((String) getCellValue(nextCell));    	            		
    	                break;
    	            case 23:
    	            	chargeImportDto.setSupplierOrderNumber((String) getCellValue(nextCell));    	            		
    	                break;
    	            case 24:
    	            	chargeImportDto.setSupplierServiceReference((String) getCellValue(nextCell));    	            		
    	                break;
    	            case 25:
    	            	chargeImportDto.setProductCode((String) getCellValue(nextCell));    	            		
    	                break;
    	                
    	                
    	                
    	                
    	            case 26:
    	            	chargeImportDto.setDescription((String) getCellValue(nextCell));    	            		
    	                break;
    	            case 27:
    	            	chargeImportDto.setCustomerReference((String) getCellValue(nextCell));    	            		
    	                break;
    	            case 28:
    	            	chargeImportDto.setChargeOrderNumber((String) getCellValue(nextCell));    	            		
    	                break;
    	            case 29:
    	            	chargeImportDto.setQuantity((String) getCellValue(nextCell));    	            		
    	                break;
    	            case 30:
    	            	chargeImportDto.setChargeFrequency((String) getCellValue(nextCell));    	            		
    	                break;
    	                
    	                
    	                   	                
    	            case 31:
    	            	chargeImportDto.setUnitCostToGCI((String) getCellValue(nextCell));    	            		
    	                break;
    	            case 32:
    	            	chargeImportDto.setUnitChargeToCustomer((String) getCellValue(nextCell));    	            		
    	                break;
    	            case 33:
    	            	chargeImportDto.setTaxTypeFlag((String) getCellValue(nextCell));    	            		
    	                break;
    	            case 34:
    	            	chargeImportDto.setChargeStartDate((String) getCellValue(nextCell));    	            		
    	                break;
    	            case 35:
    	            	chargeImportDto.setChargeCeaseDate((String) getCellValue(nextCell));    	            		
    	                break;
    	                
    	                
    	                
    	                
    	            case 36:
    	            	chargeImportDto.setChargeBilledUntilDate((String) getCellValue(nextCell));    	            		
    	                break;
    	            case 37:
    	            	chargeImportDto.setChargeSupplierContractStartDate((String) getCellValue(nextCell));    	            		
    	                break;
    	            case 38:
    	            	chargeImportDto.setChargeSupplierContractEndDate((String) getCellValue(nextCell));    	            		
    	                break;
    	            case 39:
    	            	chargeImportDto.setChargeCustomerContractStartDate((String) getCellValue(nextCell));    	            		
    	                break;
    	            case 40:
    	            	chargeImportDto.setCustomerContractEndDate((String) getCellValue(nextCell));    	            		
    	                break;
    	           case 41:
    	            	chargeImportDto.setChargeID((String) getCellValue(nextCell));    	            		
    	                break;      
    	                
    	                
    	         */
    	               
    	                
    	            }
    	 
    	 
    	        }
    	        chargeImportDtoList.add(chargeImportDto);
    	    }
    	 
    	    workbook.close();
    	    inputStream.close();
    	 
    	    return chargeImportDtoList;
    	
    }
    
    private Object getCellValue(Cell cell) {
	    switch (cell.getCellType()) {
	    case Cell.CELL_TYPE_STRING:
	        return cell.getStringCellValue();
	 
	    case Cell.CELL_TYPE_BOOLEAN:
	        return cell.getBooleanCellValue();
	 
	    case Cell.CELL_TYPE_NUMERIC:
	        return cell.getNumericCellValue();
	    }
	    
  
	 
	    return null;
	}
}