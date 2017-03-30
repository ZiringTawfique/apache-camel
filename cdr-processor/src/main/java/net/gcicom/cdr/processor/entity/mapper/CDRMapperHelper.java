package net.gcicom.cdr.processor.entity.mapper;

import static net.gcicom.common.util.NumberRangeUtils.getNumberRanges;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import net.gcicom.cdr.processor.service.GCICDRService;
import net.gcicom.cdr.processor.service.ValidationFailedException;
import net.gcicom.domain.allspark.BillingReference;
import net.gcicom.domain.imported.events.ImportedEvent;
import net.gcicom.domain.rating.NumberRangeMap;
import net.gcicom.domain.rating.TimePeriodMap;

/**
 * Container class for common reusable helper methods to support mapping of 
 * supplied specific CDR POJO to {@link ImportedEvent} 
 *
 */
@Component
final class CDRMapperHelper {
	
	private static final Logger LOG = LoggerFactory.getLogger(CDRMapperHelper.class);
	
	@Autowired
	private GCICDRService s;

	
	/**Populates billing reference details from AllSpark.BillingReference table
	 * for given event time and origin number as billing reference
	 * @param cdr
	 * @param eventTime
	 * @param originNbr
	 * @return
	 * @throws ValidationFailedException
	 */
	public ImportedEvent populateBillingReferenceDetails(@NotNull ImportedEvent cdr, @NotNull LocalDateTime eventTime, @NotNull String originNbr)
			throws ValidationFailedException {

		LOG.debug(String.format(
				"Populating billing reference details for %s billing reference and for %s event date time", originNbr,
				eventTime));
		// get billing reference number if not found mark it error record
		List<BillingReference> bfs = s.getBillingReference(originNbr, eventTime);
		if (bfs.size() == 0) {

			throw new ValidationFailedException(
					String.format("Missing billing reference details for %s originating number and %s event time ",
							originNbr, eventTime));
		}

		for (BillingReference bf : bfs) {

			String accountNbr = bf.getAccountNumber();
			Long custId = bf.getCustomerID();
			Long billRefId = bf.getBillingReferenceID();
			String eventReference = bf.getBillingReference();
			
			if (ObjectUtils.isEmpty(accountNbr) || ObjectUtils.isEmpty(custId) || ObjectUtils.isEmpty(billRefId)) {

				throw new ValidationFailedException(
						String.format("Missing billing reference details for %s originating number and %s event time ",
								originNbr, eventTime));
			}
			LOG.debug(String.format(
					"Billing reference details from db %s a/c number, %s customer id and for %s event ref id", accountNbr, custId,
					billRefId));
			
			cdr.setAccountNumber(accountNbr);
			cdr.setCustomerID(custId);
			cdr.setEventReferenceID(billRefId);
			cdr.setEventReference(eventReference);
			break;
		}
		
		return cdr;

	}
	
	/** Retrieves {@link NumberRangeMap} for given dialed number and event time combination
	 * @param dialedNumber
	 * @param eventTime
	 * @return
	 */
	private NumberRangeMap getNumberRange(@NotNull final String dialedNumber, @NotNull final LocalDateTime eventTime) {
		
		List<Long> l = getNumberRanges(dialedNumber);
		
		if (l.isEmpty()) {
			
			return null;
			
		}
		List<NumberRangeMap> r = s.getNumberRanges(l, eventTime);
		
		for (Long range : l) {
			
			for (NumberRangeMap nrm : r) {
				
				if(range.equals(nrm.getNumberRange())) {
					
					return nrm;
				}
			}
		}
		
		return null;
		
	}

	/**Populates {@link ImportedEvent#setNumberRange(Long)}, {@link ImportedEvent#setNumberRangeClassification(String)}
	 * and {@link ImportedEvent#setNumberRangeType(String)} if available for given search criteria
	 * Note - Internet (not dial up) may not have {@link NumberRangeMap}
	 * @param cdr semi populated {@link ImportedEvent}
	 * @param dialedNumber 
	 * @param eventTime
	 * @return
	 * @throws ValidationFailedException 
	 */
	public ImportedEvent populateNumberRangDetails(@NotNull ImportedEvent cdr, @NotNull String dialedNumber, @NotNull LocalDateTime eventTime) throws ValidationFailedException {
		
		NumberRangeMap nrm = getNumberRange(dialedNumber, eventTime);
		
		if (!ObjectUtils.isEmpty(nrm) && !ObjectUtils.isEmpty(nrm.getNumberRange()) 
				&& !StringUtils.isEmpty(nrm.getNumberRangeClassification())
				&& !StringUtils.isEmpty(nrm.getNumberRangeType())) {
			
			cdr.setNumberRange(nrm.getNumberRange());
			cdr.setNumberRangeClassification(nrm.getNumberRangeClassification());
			cdr.setNumberRangeType(nrm.getNumberRangeType());
			return cdr;

		} else {
			
			throw new ValidationFailedException(String.format("Mandatory number range map details are missing for %s and %s ", dialedNumber, eventTime));
		}

	}
	
	/**
	 * @param weekDayFlag
	 * @param time
	 * @return
	 * @throws ValidationFailedException 
	 */
	public Integer getTimePeriodMapId(@NotNull Integer weekDayFlag, @NotNull LocalTime time) throws ValidationFailedException {
		
		List<TimePeriodMap> tpms = s.getTimePeriodMap(weekDayFlag, time);
		if (!tpms.isEmpty() && !ObjectUtils.isEmpty(tpms.get(0).getId())) {
			
			return tpms.get(0).getId();

		} else {
			
			throw new ValidationFailedException(String.format("Mandatory time period map details not available for give event time %s and weekday %s", time, weekDayFlag));
		}
	}
	

	

}
