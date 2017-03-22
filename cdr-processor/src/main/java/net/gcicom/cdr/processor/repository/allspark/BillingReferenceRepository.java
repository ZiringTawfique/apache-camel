package net.gcicom.cdr.processor.repository.allspark;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import net.gcicom.domain.allspark.BillingReference;

public interface BillingReferenceRepository extends JpaRepository<BillingReference, Long> {

	List<BillingReference> findByBillingReferenceAndBillingReferenceStartDateLessThanEqualAndBillingReferenceEndDateGreaterThanEqual(
			String billingRef, Date startDt, Date endDt);

}
