package net.gcicom.cdr.processor.repository.imported.events;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.gcicom.domain.imported.events.EventFile;

public interface EventFileRepository extends CrudRepository<EventFile, Long> {

	List<EventFile> findByEventFileChecksum(String hex);

}
