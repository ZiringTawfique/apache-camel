package net.gcicom.cdr.processor.repository;

import org.springframework.data.repository.CrudRepository;

import net.gcicom.domain.imported.events.ImportedEvent;

public interface GCICDRRepository extends CrudRepository<ImportedEvent, String> {


}
