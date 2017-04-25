package net.gcicom.cdr.processor.repository.allspark;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.gcicom.domain.allspark.BillingReference;

public interface BillingReferenceRepository extends JpaRepository<BillingReference, Long> {

	@Query("select b from BillingReference b where b.billingReference= :bf and b.billingReferenceStartDate<= :et "
			+ "and (b.billingReferenceEndDate>= :et or b.billingReferenceEndDate is null)")
	List<BillingReference> findBillingReferenceDetails(@Param(value = "bf") String billingRef, @Param(value = "et") LocalDateTime eventTime);

}
