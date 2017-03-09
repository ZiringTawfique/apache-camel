package net.gcicom.cdr.processor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.gcicom.cdr.processor.entity.audit.AuditEvent;
import net.gcicom.cdr.processor.repository.CDRAuditRepository;

@Component
public class CDRProcessorAuditService {
	
	@Autowired
	CDRAuditRepository repo;
	
	public void audit(AuditEvent event) {
		
		repo.save(event);
		
	}
	
	

}
