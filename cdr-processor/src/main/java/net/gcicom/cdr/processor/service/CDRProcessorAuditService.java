package net.gcicom.cdr.processor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.gcicom.cdr.processor.repository.imported.events.CDRAuditRepository;
import net.gcicom.domain.imported.events.AuditEvent;

/**
 * Audit service to handle auditing. 
 *
 */
@Component
public class CDRProcessorAuditService {
	
	@Autowired
	CDRAuditRepository repo;
	
	/**
	 * @param event
	 */
	public void audit(AuditEvent event) {
		
		repo.save(event);
		
	}
	
	

}
