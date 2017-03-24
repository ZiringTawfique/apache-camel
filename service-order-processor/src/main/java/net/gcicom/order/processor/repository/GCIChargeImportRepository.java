package net.gcicom.order.processor.repository;

import org.springframework.data.repository.CrudRepository;

import net.gcicom.order.processor.entity.output.CDRKey;

import net.gcicom.order.processor.entity.output.GCIChargeImport;

public interface GCIChargeImportRepository extends CrudRepository<GCIChargeImport, CDRKey> {


}
