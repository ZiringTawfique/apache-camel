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

/**
 * Class to handle auditing. 
 * TODO This needs more work
 *
 */
@Component
public final class Auditor implements Processor {

	private Logger logger = LoggerFactory.getLogger(Auditor.class);
	
	@Autowired
	private CDRProcessorAuditService service;
	
	@Override
	public void process(final Exchange exchange) throws Exception {
		
		logger.info("hi there ");
		
	}
	
	/**
	 * @param exchange
	 */
	public void startEvent(final Exchange exchange) {
		
		logger.info("startEvent ");
			handleEvent(exchange, "FILE_PROCESSING_START");

	}
	
	/**
	 * @param exchange
	 */
	public void endEvent(final Exchange exchange) {
		
		logger.info("endEvent ");
		handleEvent(exchange, "FILE_PROCESSING_FINISHED");


	}
	
	/**
	 * @param exchange
	 */
	public void errorEvent(final Exchange exchange) {
		
		logger.info("errorEvent ");
		handleEvent(exchange, "FILE_PROCESSING_ERROR");

	}
	
	/**
	 * @param exchange
	 * @param eventType
	 */
	private void handleEvent(final Exchange exchange, final String eventType) {
		
		Map<String, String> data = new HashMap<>();
		data.put("file",  exchange.getIn().getHeader("CamelFileNameConsumed", String.class));
		AuditEvent event = getAuditEvent(eventType);
		event.setData(data);
		service.audit(event);
	}
	
	/**
	 * @param eventType
	 * @return
	 */
	private AuditEvent getAuditEvent(final String eventType) {
		
		AuditEvent event = new AuditEvent();
		event.setAuditEventType(eventType);
		return event;
		
	}
	
	
	

	public void handleEventInvalidCdr(Exchange exchange) throws Exception {

		
		AuditEvent event = getAuditEvent("INVALID_CDR");
		Map<String, String> data = new HashMap<>();
		data.put("file",  exchange.getIn().getHeader("CamelFileNameConsumed", String.class));
		Throwable reason = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);
		logger.debug("Reason of failure : " + reason.getMessage());

		data.put("cdr", exchange.getIn().getBody(String.class));
		data.put("reason", reason.getMessage());
		
		event.setData(data);
		
		service.audit(event);
		
		
		
	}
}
