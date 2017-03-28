package net.gcicom.cdr.processor.repository.rating;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import net.gcicom.domain.rating.TimePeriodMap;

public interface TimePeriodMapRepository extends CrudRepository<TimePeriodMap, Long> {

	@Query("select tpm from TimePeriodMap tpm where tpm.startDay <= :day "
			+ "and tpm.endDay >= :day and tpm.startTime <= :t and tpm.endTime >= :t")
	List<TimePeriodMap> findTimePeriod(@Param(value = "day") int day, @Param(value = "t") LocalTime time);


}
