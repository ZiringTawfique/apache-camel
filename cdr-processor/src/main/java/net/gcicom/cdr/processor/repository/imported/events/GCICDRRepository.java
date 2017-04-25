package net.gcicom.cdr.processor.repository.imported.events;

import org.springframework.data.repository.CrudRepository;

import net.gcicom.domain.imported.events.ImportedEvent;

public interface GCICDRRepository extends CrudRepository<ImportedEvent, String> {


}
