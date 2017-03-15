package net.gcicom.cdr.processor.entity.mapper;


import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import net.gcicom.cdr.processor.entity.input.AbzorbO2CDR;
import net.gcicom.cdr.processor.entity.output.CDRMapper;
import net.gcicom.cdr.processor.entity.output.GCICDR;
import net.gcicom.cdr.processor.util.DateTimeUtil;
import net.gcicom.cdr.processor.util.EventRecordKeyGenerator;


@Component
public class Abzorbo2CDRToGciCDRMapper implements CDRMapper<AbzorbO2CDR> {
			
	
		private static final Logger LOG = LoggerFactory.getLogger(Abzorbo2CDRToGciCDRMapper.class);
		private static final String DUMMY = "DUMMY";
		private static final Long L_DUMMY = 1L;
	
		/* (non-Javadoc)
		 * @see net.gcicom.cdr.processor.entity.output.CDRMapper#convertToGCICDR(java.util.List)
		 */
		public List<GCICDR> convertToGCICDR(final List<AbzorbO2CDR> input) {
			
			List<GCICDR> cdrs = new ArrayList<>();

			for (AbzorbO2CDR source : input) {
				
				LOG.debug("Converting AbzorbO2CDR to GCICDR" + source.toString());
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
				cdr.setEventTime(Timestamp.valueOf(source.getDate().atTime(LocalTime.parse(source.getTime(), DateTimeFormatter.ISO_LOCAL_TIME))));
				cdr.setEventTypeId(L_DUMMY);
				cdr.setNumberRange(DUMMY);
				cdr.setNumberRangeClassification(DUMMY);
				cdr.setNumberRangeType(DUMMY);
				cdr.setOriginatingCLI(source.getOriginatingNumber());
				cdr.setPreRatedEventFlag(DUMMY);
				cdr.setPresentationCLI(DUMMY);
				cdr.setSupplierAccountNumber(DUMMY);
				cdr.setSupplierCost(source.getSalesprice());
				cdr.setSupplierId(L_DUMMY);
				cdr.setSupplierNumberRangeMap(DUMMY);
				cdr.setSupplierRatingPattern(source.getMobileClass() + "_" + source.getTimeBand());
				cdr.setSupplierRecordReference(DUMMY);
				cdr.setSupplierServiceType(source.getNetwork() + "_" + source.getCallType());
				cdr.setTerminatingCLI(source.getDialedNumber());
				cdr.setTimePeriod(DUMMY);
				cdr.setWeekDayFlag(DateTimeUtil.getWeekDayFlag(source.getDate().atTime(LocalTime.parse(source.getTime(), DateTimeFormatter.ISO_LOCAL_TIME))));
				cdr.setSupplierTariffPlanId(source.getTariff());
				cdr.setCreatedBy("CDR-PROCESSOR");
				
				//generate only after populating all the field in cdrs
				cdr.setEventRecordKey(EventRecordKeyGenerator.getEventRecordHash(cdr));
				LOG.debug("Converted cdr " + cdr.toString());
				cdrs.add(cdr);
			}
			
			

			return cdrs;
			
		}
	
	
}
