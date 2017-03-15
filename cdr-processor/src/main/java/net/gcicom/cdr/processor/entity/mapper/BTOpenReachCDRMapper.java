package net.gcicom.cdr.processor.entity.mapper;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import net.gcicom.cdr.processor.entity.input.BTOpenReachCDR;
import net.gcicom.cdr.processor.entity.output.CDRMapper;
import net.gcicom.cdr.processor.entity.output.GCICDR;
import net.gcicom.cdr.processor.service.ValidationFailedException;
import net.gcicom.cdr.processor.util.DateTimeUtil;
import net.gcicom.cdr.processor.util.EventRecordKeyGenerator;


@Component
public class BTOpenReachCDRMapper implements CDRMapper<BTOpenReachCDR> {
			
	
		private static final Logger LOG = LoggerFactory.getLogger(BTOpenReachCDRMapper.class);

		private static final String DUMMY = "DUMMY";
		private static final Long L_DUMMY = 1L;

		public List<GCICDR> convertToGCICDR(final List<BTOpenReachCDR> input) throws Exception {
			
			List<GCICDR> cdrs = new ArrayList<>();

			for (BTOpenReachCDR source : input) {
				
				LOG.debug("Converting a BTOpenReachCDR to GCICDR" + source.toString());

				GCICDR cdr = new GCICDR();
				
				cdr.setAccountingPeriod(DUMMY);
				cdr.setAccountNumber(DUMMY);
				cdr.setCountry(DUMMY);
				cdr.setCustomerId(L_DUMMY);
				cdr.setDialledCLI(source.getDialedNumber());
				cdr.setEventDurationSecs(source.getDuration());
				cdr.setEventFileId(L_DUMMY);
				cdr.setEventReference(source.getOriginatingNumber());
				cdr.setEventReferenceId(L_DUMMY);
				cdr.setEventTime(Timestamp.valueOf(getDateTime(source.getEventTime())));
				cdr.setEventTypeId(L_DUMMY);
				cdr.setNumberRange(DUMMY);
				cdr.setNumberRangeClassification(DUMMY);
				cdr.setNumberRangeType(DUMMY);
				cdr.setOriginatingCLI(source.getOriginatingNumber());
				cdr.setPreRatedEventFlag(DUMMY);
				cdr.setPresentationCLI(source.getDialedNumber());
				cdr.setSupplierAccountNumber(source.getAccountNumber());
				cdr.setSupplierCost(source.getWholesalePrice());
				cdr.setSupplierId(L_DUMMY);
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
