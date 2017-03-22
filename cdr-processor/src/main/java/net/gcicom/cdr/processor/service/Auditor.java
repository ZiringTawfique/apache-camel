package net.gcicom.cdr.processor.service;

import static net.gcicom.cdr.processor.common.AppConstants.CDR_PROCESSOR_USER;
import static net.gcicom.cdr.processor.util.DateTimeUtil.getTodaysDate;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.gcicom.domain.imported.events.AuditEvent;

/**
 * Class to handle auditing. 
 * TODO This needs more work
 *
 */
@Component
public final class Auditor {

	private static final Logger LOG = LoggerFactory.getLogger(Auditor.class);


	@Autowired
	private CDRProcessorAuditService service;
	
	
	/**
	 * @param exchange
	 */
	public void startEvent(final Exchange exchange) {
		
		LOG.debug("startEvent ");
		handleEvent(exchange, EventTypes.FILE_PROCESSING_START);

	}
	
	/**
	 * @param exchange
	 */
	public void endEvent(final Exchange exchange) {
		
		LOG.debug("endEvent ");

		if (exchange.getProperty(Exchange.SPLIT_COMPLETE, Boolean.class)) {
			
			handleEvent(exchange, EventTypes.FILE_PROCESSING_FINISHED);

		}


	}
	
	/**
	 * @param exchange
	 */
	public void errorEvent(final Exchange exchange) {
		
		LOG.info(EventTypes.FILE_PROCESSING_ERROR);
		handleEvent(exchange, EventTypes.FILE_PROCESSING_ERROR);

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
			
			LOG.error("Handled event has following error \n", reason);
			data.put("reason", reason.getMessage());
			data.put("stacktrace", MessageFormat.format("Detail stack trace {0}", reason));
			if (!reason.getClass().equals(AlreadyProcessedFileException.class)) {
				
				data.put("cdr", exchange.getIn().getBody(String.class));
			}
			

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
		event.setAuditEventDate(getTodaysDate());
		event.setCreatedBy(CDR_PROCESSOR_USER);
		return event;
		
	}
	
	
	

	/**
	 * @param exchange
	 * @throws Exception
	 */
	public void handleEventInvalidCdr(final Exchange exchange) throws Exception {

		handleEvent(exchange, EventTypes.INVALID_CDR);
		
	}
	
	
}
