package net.gcicom.cdr.processor.repository;

import org.springframework.data.repository.CrudRepository;

import net.gcicom.cdr.processor.entity.output.GCICDR;

public interface GCICDRRepository extends CrudRepository<GCICDR, String> {


}
