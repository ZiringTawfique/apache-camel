package net.gcicom.cdr.processor.service;

import static net.gcicom.cdr.processor.common.AppConstants.CDR_PROCESSOR_USER;
import static net.gcicom.cdr.processor.service.EventTypes.FILE_PROCESSING_ERROR;
import static net.gcicom.cdr.processor.service.EventTypes.FILE_PROCESSING_FINISHED;
import static net.gcicom.cdr.processor.service.EventTypes.FILE_PROCESSING_START;
import static net.gcicom.cdr.processor.service.EventTypes.INVALID_CDR;
import static org.apache.camel.Exchange.FILE_NAME_CONSUMED;
import static org.apache.camel.Exchange.EXCEPTION_CAUGHT;

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
		handleEvent(exchange, FILE_PROCESSING_START);

	}
	
	/**
	 * @param exchange
	 */
	public void endEvent(final Exchange exchange) {
		
		LOG.debug("endEvent ");

		if (exchange.getProperty(Exchange.SPLIT_COMPLETE, Boolean.class)) {
			
			handleEvent(exchange, FILE_PROCESSING_FINISHED);

		}


	}
	
	/**
	 * @param exchange
	 */
	public void errorEvent(final Exchange exchange) {
		
		LOG.info(FILE_PROCESSING_ERROR);
		handleEvent(exchange, FILE_PROCESSING_ERROR);

	}
	
	/**
	 * @param exchange
	 * @param eventType
	 */
	private void handleEvent(final Exchange exchange, final String eventType) {
		
		Map<String, String> data = new HashMap<>();
		data.put("file",  exchange.getIn().getHeader(FILE_NAME_CONSUMED, String.class));
		Throwable reason = exchange.getProperty(EXCEPTION_CAUGHT, Throwable.class);
		if (reason != null) {
			
			LOG.error("Handled event has following error \n", reason);
			data.put("reason", reason.getMessage());
			data.put("stacktrace", MessageFormat.format("Detail stack trace {0}", reason));
			if (!(reason instanceof AlreadyProcessedFileException)) {
				
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

		handleEvent(exchange, INVALID_CDR);
		
	}
	
	
}
