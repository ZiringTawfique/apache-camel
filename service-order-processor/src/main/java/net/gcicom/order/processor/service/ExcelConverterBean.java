package net.gcicom.order.processor.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.math.BigDecimal;
import javax.xml.datatype.DatatypeFactory;
import org.apache.camel.Body;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.draw.binding.ObjectFactory;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import net.gcicom.order.processor.entity.input.ChargeImportDto;
import org.apache.poi.ss.usermodel.Cell;
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

    public List<ChargeImportDto> processExcelData(@Body InputStream inputStream) throws IOException {
    	
    	    List<ChargeImportDto> chargeImportDtoList = new ArrayList<>();
    	    
    	 
    	    Workbook workbook = new XSSFWorkbook(inputStream);
    	    Sheet firstSheet = workbook.getSheetAt(0);
    	    Iterator<Row> iterator = firstSheet.iterator();
    	 
    	    while (iterator.hasNext()) {
    	        Row nextRow = iterator.next();
    	        Iterator<Cell> cellIterator = nextRow.cellIterator();
    	        ChargeImportDto chargeImportDto = new ChargeImportDto();
    	 
    	        while (cellIterator.hasNext()) {
    	            Cell nextCell = cellIterator.next();
    	            int columnIndex = nextCell.getColumnIndex();
    	 
    	            switch (columnIndex) {
    	            case 1:
    	            	chargeImportDto.setActionCode((String) getCellValue(nextCell));
    	            	  
    	                break;
    	            case 2:
    	            	chargeImportDto.setItemType((String) getCellValue(nextCell));
    	                break;
    	            case 3:
    	            	chargeImportDto.setCustomerName((String) getCellValue(nextCell));
    	            	
    	            case 4:
    	            	chargeImportDto.setAccountNumber((String) getCellValue(nextCell));
    	            	  
    	                break;
    	            case 5:
    	            	chargeImportDto.setNodeName((double) getCellValue(nextCell));
    	                break;
    	           /* case 6:
    	            	chargeImportDto.setOrderNumber((String) getCellValue(nextCell));    	            		
    	                break;
    	            case 7:
    	            	chargeImportDto.setOrderNumber((String) getCellValue(nextCell));    	            		
    	                break;
    	            case 8:
    	            	chargeImportDto.setOrderNumber((String) getCellValue(nextCell));    	            		
    	                break;
    	            case 9:
    	            	chargeImportDto.setOrderNumber((String) getCellValue(nextCell));    	            		
    	                break;
    	            case 10:
    	            	chargeImportDto.setOrderNumber((String) getCellValue(nextCell));    	            		
    	                break;*/
    	                
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