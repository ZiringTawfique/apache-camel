package net.gcicom.order.processor.service;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static net.gcicom.order.processor.common.AppConstants.TOTAL_RECORD_COUNT;
import static net.gcicom.order.processor.service.ValidationTypes.RECORD_PROCESSING_ERROR;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import static org.apache.camel.Exchange.FILE_NAME_CONSUMED;
/**
 * Custom error handler to handle unknown errors and do error auditing  
 *TODO need more work
 */
@Component
public class CDRProcessorErrorHandler {

	
	//Delimiter used in CSV file
		private static final String COMMA_DELIMITER = ",";
		private static final String NEW_LINE_SEPARATOR = "\n";
		
		//CSV file header
		private static final String FILE_HEADER = "id,firstName,lastName,gender,age";
		
	private Logger logger = LoggerFactory.getLogger(CDRProcessorErrorHandler.class);
	Map<Integer, String> data = new HashMap<>();
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
	
	/**
	 * @param exchange
	 * @param eventType
	 */
	private void handleEvent(final Exchange exchange, final String eventType) {
		int i=0;
		 Integer count = exchange.getProperty("CamelLoopIndex", Integer.class);
		
		  data.put(i++,  exchange.getIn().getBody().toString());

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
		//  int totalCount = (int)exchange.getIn().getHeader("TOTAL_RECORD_COUNT");
		  
		//  int totalProcessedCount = (int)exchange.getIn().getHeader("TOTAL_RECORD_PROCESSED_COUNT");
		
	//	if(totalCount== totalCount){
			writeCsvFile(data);
		//}
	
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
	

	
	
}
