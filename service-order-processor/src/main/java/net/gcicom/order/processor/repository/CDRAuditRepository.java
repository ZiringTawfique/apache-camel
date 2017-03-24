package net.gcicom.order.processor.repository;
/**
 * @author Sathish Natarajan
 *
 */
import org.springframework.data.repository.CrudRepository;

import net.gcicom.order.processor.entity.audit.AuditEvent;

public interface CDRAuditRepository extends CrudRepository<AuditEvent, Long> {

}
