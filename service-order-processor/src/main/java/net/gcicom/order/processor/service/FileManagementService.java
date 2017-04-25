package net.gcicom.order.processor.service;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.gcicom.order.processor.entity.input.ChargeImportDto;
import net.gcicom.order.processor.entity.output.ExcelFileHelper;
import static net.gcicom.order.processor.config.AppProperties.PROCESSED_FILE_LOCATION;

@Component("fileManagementService")
@Transactional
public class FileManagementService{

	@Autowired
	Environment env;
	
	public void createProcessedFile(List<ChargeImportDto> chargeImportDtoList,String fileName) {
	
	 try {
			
			String  errorFileLocation= env.getProperty(PROCESSED_FILE_LOCATION);
			 
			ExcelFileHelper.writeExcelFile(chargeImportDtoList,errorFileLocation,fileName.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
    }
}