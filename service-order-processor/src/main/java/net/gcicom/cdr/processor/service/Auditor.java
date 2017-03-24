package net.gcicom.cdr.processor.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
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
public final class Auditor {

	private Logger logger = LoggerFactory.getLogger(Auditor.class);
	
	@Autowired
	private CDRProcessorAuditService service;
	
	
	/**
	 * @param exchange
	 */
	public void startEvent(final Exchange exchange) {
		
		logger.debug("startEvent ");
		handleEvent(exchange, "FILE_PROCESSING_START");

	}
	
	/**
	 * @param exchange
	 */
	public void endEvent(final Exchange exchange) {
		
		logger.debug("endEvent ");

		if (exchange.getProperty(Exchange.SPLIT_COMPLETE, Boolean.class)) {
			
			handleEvent(exchange, "FILE_PROCESSING_FINISHED");

		}


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
		Throwable reason = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);
		if (reason != null) {
			
			data.put("exception", reason.getMessage());

		} 
		
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
	
	
	

	/**
	 * @param exchange
	 * @throws Exception
	 */
	public void handleEventInvalidCdr(final Exchange exchange) throws Exception {

		
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
	
	public void handleLoading(final String fileName, final String hex) throws Exception {

		
		AuditEvent event = getAuditEvent("MD5_HEX");
		Map<String, String> data = new HashMap<>();
		data.put("file",  fileName);
		data.put("hex", hex);
		
		event.setData(data);
		
		service.audit(event);
		
		
		
	}
	
	
}
