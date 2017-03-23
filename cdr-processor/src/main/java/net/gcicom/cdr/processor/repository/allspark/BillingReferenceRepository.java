package net.gcicom.cdr.processor.repository.allspark;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.gcicom.domain.allspark.BillingReference;

public interface BillingReferenceRepository extends JpaRepository<BillingReference, Long> {

	@Query("select b from BillingReference b where b.billingReference= :bf and b.billingReferenceStartDate<= :sd "
			+ "and (b.billingReferenceEndDate>= :ed or b.billingReferenceEndDate is null)")
	List<BillingReference> findBillingReferenceDetails(@Param(value = "bf") String billingRef,
			@Param(value = "sd") Date startDt, @Param(value = "ed") Date endDt);

}
