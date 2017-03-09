package net.gcicom.cdr.processor.service;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CDRProcessorErrorHandler {

	private Logger logger = LoggerFactory.getLogger(CDRProcessorErrorHandler.class);
	
	public void handleError(Exchange e) {
		
		logger.info("Handle Event" + e);
		
		
	}
}
