package net.gcicom.cdr.processor.service;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Custom error handler to handle unknown errors and do error auditing  
 *TODO need more work
 */
@Component
public class CDRProcessorErrorHandler {

	private Logger logger = LoggerFactory.getLogger(CDRProcessorErrorHandler.class);
	
	/**
	 * @param e
	 */
	public void handleError(Exchange e) {
		
		logger.info("Handle Event" + e);
		
		
	}
}
