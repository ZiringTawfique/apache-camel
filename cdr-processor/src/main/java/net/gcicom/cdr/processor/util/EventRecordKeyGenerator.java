package net.gcicom.cdr.processor.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import net.gcicom.domain.imported.events.ImportedEvent;

@Component
public abstract class EventRecordKeyGenerator {
	
	private static final Logger LOG = LoggerFactory.getLogger(EventRecordKeyGenerator.class);
	
	/** Generate digest as hex string based on an input combination of
	 * supplierId; customerId; originatingCLI; dialledCLI; terminatingCLI; eventTime;
  	 * eventTypeId; eventDurationSecs; preRatedEventFlag; eventFileId;supplierAccountNumber;
	 * @param input
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static String getEventRecordHash(final Object ...input) throws IllegalArgumentException {

		StringBuilder sb = new StringBuilder();
		if (input != null && input.length > 0) {
			
			for (int i = 0; i < input.length; i++) {
				
				if (!ObjectUtils.isEmpty(input[i])) {
					
					sb.append(input[i]);
					
				} else {
					
					String msg = String.format("Null or empty input [ %s at index %s] is not allowed in hash generation ", input[i], i+1);//programmer message
					LOG.error(msg);
					throw new IllegalArgumentException("Mandatory records are missing");
				}
			}
			
			String digest = DigestUtils.md5DigestAsHex(sb.toString().getBytes());
			
			return digest;
			
		} else {
			
			String msg = String.format("Null or empty input [ %s ] is not allowed in hash generation", input);//programmer message
			LOG.error(msg);
			throw new IllegalArgumentException("Mandatory records are missing");
			
		}
		
	}
	
	/**
	 * @param cdr
	 */
	public static String getEventRecordHash(final ImportedEvent cdr) {
		
		if (cdr == null) {
			
			String msg = String.format("Null or empty input [ %s ] is not allowed in hash generation", cdr);
			LOG.error(msg);
			throw new IllegalArgumentException("Mandatory records are missing");
		}
		
		return EventRecordKeyGenerator.getEventRecordHash(
				cdr.getSupplierID(),
				cdr.getCustomerID(),
				cdr.getOriginatingCLI(),
				cdr.getDialledCLI(),
				cdr.getTerminatingCLI(),
				cdr.getEventTime(),
				cdr.getEventTypeID(),
				cdr.getEventDurationSecs(),
				cdr.getPreRatedEventFlag(),
				cdr.getFileChecksum(),
				cdr.getSupplierAccountNumber());
	}

}
