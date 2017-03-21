package net.gcicom.cdr.processor.entity.mapper;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import net.gcicom.cdr.processor.entity.input.BTOpenReachCDR;
import net.gcicom.cdr.processor.service.ValidationFailedException;
import net.gcicom.cdr.processor.util.DateTimeUtil;
import net.gcicom.cdr.processor.util.EventRecordKeyGenerator;
import net.gcicom.domain.imported.events.ImportedEvent;


@Component
public class BTOpenReachCDRMapper implements CDRMapper<BTOpenReachCDR> {
			
	
		private static final Logger LOG = LoggerFactory.getLogger(BTOpenReachCDRMapper.class);

		private static final String DUMMY = "DUMMY";
		private static final Long L_DUMMY = 1L;

		public List<ImportedEvent> convertToGCICDR(final List<BTOpenReachCDR> input) throws Exception {
			
			List<ImportedEvent> cdrs = new ArrayList<>();

			for (BTOpenReachCDR source : input) {
				
				LOG.debug("Converting a BTOpenReachCDR to GCICDR" + source.toString());

				ImportedEvent cdr = new ImportedEvent();
				
				cdr.setAccountingPeriod(DUMMY);
				cdr.setAccountNumber(DUMMY);
				cdr.setCountry(DUMMY);
				cdr.setCustomerID(L_DUMMY);
				cdr.setDialledCLI(source.getDialedNumber());
				cdr.setEventDurationSecs(DateTimeUtil.getDurationInSeconds(source.getDuration()));
				cdr.setEventFileID(L_DUMMY);
				cdr.setEventReference(source.getOriginatingNumber());
				cdr.setEventReferenceID(L_DUMMY);
				cdr.setEventTime(Date.from(getDateTime(source.getEventTime()).toInstant(ZoneOffset.UTC)));
				cdr.setEventTypeID(L_DUMMY);
				cdr.setNumberRange(DUMMY);
				cdr.setNumberRangeClassification(DUMMY);
				cdr.setNumberRangeType(DUMMY);
				cdr.setOriginatingCLI(source.getOriginatingNumber());
				cdr.setPreRatedEventFlag(DUMMY);
				cdr.setPresentationCLI(source.getDialedNumber());
				cdr.setSupplierAccountNumber(source.getAccountNumber());
				cdr.setSupplierCost(source.getWholesalePrice());
				cdr.setSupplierID(L_DUMMY);
				cdr.setSupplierNumberRangeMap(source.getPhoneBookCode());
				cdr.setSupplierRatingPattern(source.getPhoneBookCode() + "_" + source.getReRateIndicator());
				cdr.setSupplierRecordReference(source.getDunsId());
				cdr.setSupplierServiceType(source.getEventType() + "_" + source.getBearerTypeGroup());
				cdr.setTerminatingCLI(source.getDialedNumber());
				cdr.setTimePeriod(DUMMY);
				cdr.setWeekDayFlag(DateTimeUtil.getWeekDayFlag(getDateTime(source.getEventTime())));
				cdr.setCreatedBy("CDR-PROCESSOR");
				
				//generate only after populating all the field in cdrs
				cdr.setEventRecordKey(EventRecordKeyGenerator.getEventRecordHash(cdr));
				
				LOG.debug("Converted cdr " + cdr.toString());
				cdrs.add(cdr);
			}
			
			

			return cdrs;
			
		}
		


		/**Date time is in CCYYMMDDhhmmsstt format
		 * @param dateTime
		 * @return
		 * @throws ValidationFailedException 
		 */
		private LocalDateTime getDateTime(String dateTime) throws ValidationFailedException {
			
			//strip trailing 00 character
			if (!StringUtils.isEmpty(dateTime) && dateTime.length() > 14) {
				
				String dt = dateTime.substring(0, 14);
				DateTimeFormatter ft = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
				
				return LocalDateTime.parse(dt, ft);
				
			} else {
				
				throw new ValidationFailedException(String.format("Datetime %s is not required format", dateTime));
			}
			
		}
		
	
	
}
