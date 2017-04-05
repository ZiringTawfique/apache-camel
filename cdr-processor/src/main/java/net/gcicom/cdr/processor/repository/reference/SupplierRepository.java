package net.gcicom.cdr.processor.repository.reference;


import org.springframework.data.jpa.repository.JpaRepository;

import net.gcicom.domain.reference.Supplier;
import java.lang.String;
import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

	public List<Supplier> findBySupplierNameAndHasEventFlag(String suppliername, int hasEventFlag);

}
