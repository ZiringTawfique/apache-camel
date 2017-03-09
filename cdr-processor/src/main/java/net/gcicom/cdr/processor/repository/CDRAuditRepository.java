package net.gcicom.cdr.processor.repository;

import org.springframework.data.repository.CrudRepository;

import net.gcicom.cdr.processor.entity.audit.AuditEvent;

public interface CDRAuditRepository extends CrudRepository<AuditEvent, Long> {

	
}
