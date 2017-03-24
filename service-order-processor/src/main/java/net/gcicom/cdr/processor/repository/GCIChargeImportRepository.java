package net.gcicom.cdr.processor.repository;

import org.springframework.data.repository.CrudRepository;

import net.gcicom.cdr.processor.entity.output.CDRKey;

import net.gcicom.cdr.processor.entity.output.GCIChargeImport;

public interface GCIChargeImportRepository extends CrudRepository<GCIChargeImport, CDRKey> {


}
