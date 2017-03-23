package net.gcicom.cdr.processor.entity.mapper;

import static net.gcicom.cdr.processor.common.AppConstants.CDR_PROCESSOR_USER;
import static net.gcicom.cdr.processor.util.EventRecordKeyGenerator.getEventRecordHash;
import static net.gcicom.cdr.processor.util.DateTimeUtil.getWeekDayFlag;
import static net.gcicom.cdr.processor.util.DateTimeUtil.getDurationInSeconds;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import net.gcicom.cdr.processor.entity.input.AbzorbO2CDR;
import net.gcicom.domain.imported.events.ImportedEvent;


@Component
public class Abzorbo2CDRToGciCDRMapper implements CDRMapper<AbzorbO2CDR> {
			
	
		private static final Logger LOG = LoggerFactory.getLogger(Abzorbo2CDRToGciCDRMapper.class);
		private static final String DUMMY = "DUMMY";
		private static final Long L_DUMMY = 1L;
	
		/* (non-Javadoc)
		 * @see net.gcicom.cdr.processor.entity.output.CDRMapper#convertToGCICDR(java.util.List)
		 */
		public List<ImportedEvent> convertToGCICDR(final List<AbzorbO2CDR> input, final Long eventFileId, final String fileName) {
			
			List<ImportedEvent> cdrs = new ArrayList<>();

			for (AbzorbO2CDR source : input) {
				
				LOG.debug("Converting AbzorbO2CDR to GCICDR" + source.toString());
				ImportedEvent cdr = new ImportedEvent();
				
				String dialedNumber = StringUtils.isEmpty(source.getDialedNumber()) ? source.getDescription() : source.getDialedNumber();
				
				cdr.setAccountingPeriod(DUMMY);
				cdr.setAccountNumber(DUMMY);
				cdr.setCountry(DUMMY);
				cdr.setCustomerID(L_DUMMY);
				cdr.setDialledCLI(dialedNumber);
				cdr.setEventDurationSecs(getDurationInSeconds(source.getDuration()));
				cdr.setEventFileID(L_DUMMY);
				cdr.setEventReference(source.getOriginatingNumber());
				cdr.setEventReferenceID(L_DUMMY);
				LocalDateTime dt = source.getDate().atTime(LocalTime.parse(source.getTime(), DateTimeFormatter.ISO_LOCAL_TIME));
				cdr.setEventTime(Date.from(dt.toInstant(ZoneOffset.UTC)));
				cdr.setEventTypeID(L_DUMMY);
				cdr.setNumberRange(DUMMY);
				cdr.setNumberRangeClassification(DUMMY);
				cdr.setNumberRangeType(DUMMY);
				cdr.setOriginatingCLI(source.getOriginatingNumber());
				cdr.setPreRatedEventFlag(DUMMY);
				cdr.setPresentationCLI(DUMMY);
				cdr.setSupplierAccountNumber(DUMMY);
				cdr.setSupplierCost(source.getSalesprice());
				cdr.setSupplierID(L_DUMMY);
				cdr.setSupplierNumberRangeMap(DUMMY);
				cdr.setSupplierRatingPattern(source.getMobileClass() + "_" + source.getTimeBand());
				cdr.setSupplierRecordReference(DUMMY);
				cdr.setSupplierServiceType(source.getNetwork() + "_" + source.getCallType());
				cdr.setTerminatingCLI(dialedNumber);
				cdr.setTimePeriod(DUMMY);
				cdr.setWeekDayFlag(getWeekDayFlag(source.getDate().atTime(LocalTime.parse(source.getTime(), DateTimeFormatter.ISO_LOCAL_TIME))));
				cdr.setSupplierTariffPlanID(source.getTariff());
				cdr.setCreatedBy(CDR_PROCESSOR_USER);
				
				//generate only after populating all the field in cdrs
				cdr.setEventRecordKey(getEventRecordHash(cdr));
				LOG.debug("Converted cdr " + cdr.toString());
				cdrs.add(cdr);
			}
			
			

			return cdrs;
			
		}
	
	
}
