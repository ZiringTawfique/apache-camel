package net.gcicom.cdr.processor.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.gcicom.cdr.processor.entity.audit.AuditEvent;

@Component
public class Auditor implements Processor {

	Logger logger = LoggerFactory.getLogger(Auditor.class);
	
	@Autowired
	private CDRProcessorAuditService service;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		logger.info("hi there ");
		
	}
	
	public void startEvent(Exchange exchange) {
		
		logger.info("startEvent ");
			handleEvent(exchange, "FILE_PROCESSING_START");

	}
	
	public void endEvent(Exchange exchange) {
		
		logger.info("endEvent ");
		handleEvent(exchange, "FILE_PROCESSING_FINISHED");


	}
	
	public void errorEvent(Exchange exchange) {
		
		logger.info("errorEvent ");
		handleEvent(exchange, "FILE_PROCESSING_ERROR");

	}
	
	private void handleEvent(Exchange exchange, String eventType) {
		
		Map<String, String> data = new HashMap<>();
		data.put("file",  exchange.getIn().getHeader("CamelFileNameConsumed", String.class));
		AuditEvent event = getAuditEvent(eventType);
		event.setData(data);
		service.audit(event);
	}
	
	private AuditEvent getAuditEvent(String eventType) {
		
		AuditEvent event = new AuditEvent();
		event.setAuditEventType(eventType);
		return event;
		
	}

}
