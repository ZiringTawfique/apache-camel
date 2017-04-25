package net.gcicom.cdr.processor.repository.rating;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import net.gcicom.domain.rating.NumberRangeMap;

public interface NumberRangeMapRepository extends CrudRepository<NumberRangeMap, Long> {

	@Query("select nrm from NumberRangeMap nrm where nrm.numberRange IN :dns and nrm.numberRangeStartDate<= :et "
			+ "and (nrm.numberRangeEndDate>= :et or nrm.numberRangeEndDate is null)")
	List<NumberRangeMap> findNumberRangeMap(@Param(value = "dns") List<Long> dialedNumbers,
			@Param(value = "et") LocalDateTime eventTime);


}
