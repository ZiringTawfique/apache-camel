package net.gcicom.cdr.processor.repository;

import org.springframework.data.repository.CrudRepository;

import net.gcicom.cdr.processor.entity.output.InvalidCDR;

public interface InvalidCDRRepository extends CrudRepository<InvalidCDR, Long> {

}
