package net.gcicom.cdr.processor.repository.rating;


import org.springframework.data.jpa.repository.JpaRepository;

import net.gcicom.domain.rating.Supplier;
import java.lang.String;
import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

	public List<Supplier> findBySupplierName(String suppliername);

}
