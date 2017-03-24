package net.gcicom.order.processor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.gcicom.order.processor.entity.audit.AuditEvent;
import net.gcicom.order.processor.repository.CDRAuditRepository;

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
