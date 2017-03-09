package net.gcicom.cdr.processor.service;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.gcicom.cdr.processor.entity.output.InvalidCDR;

@Component
public class InvalidCDRProcessor implements Processor {

	Logger logger = LoggerFactory.getLogger(InvalidCDRProcessor.class);
			
	@Autowired
	InvalidCDRService service;
	
	@Override
	public void process(Exchange exchange) throws Exception {

		logger.debug("Invalid record file : " + exchange.getProperty(Exchange.FILE_NAME_CONSUMED, String.class));

		Throwable reason = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);
		logger.debug("Reason of failure : " + reason.getMessage());

		InvalidCDR invalid = new InvalidCDR();
		invalid.setCdr(exchange.getIn().getBody(String.class));
		invalid.setReason(reason.getMessage());
		invalid.setCdrFile(exchange.getProperty(Exchange.FILE_NAME_CONSUMED, String.class));
		service.handleInvalidCDR(invalid);
		
	}

}
