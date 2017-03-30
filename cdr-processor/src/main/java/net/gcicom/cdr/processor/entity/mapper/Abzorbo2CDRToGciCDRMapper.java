package net.gcicom.cdr.processor.entity.mapper;

import static net.gcicom.cdr.processor.common.AppConstants.CDR_PROCESSOR_USER;
import static net.gcicom.cdr.processor.util.EventRecordKeyGenerator.getEventRecordHash;
import static net.gcicom.common.util.DateTimeUtil.formatYYYYMM;
import static net.gcicom.common.util.DateTimeUtil.getDurationInSeconds;
import static net.gcicom.common.util.DateTimeUtil.getWeekDayFlag;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import net.gcicom.cdr.processor.entity.input.AbzorbO2CDR;
import net.gcicom.cdr.processor.service.GCICDRService;
import net.gcicom.cdr.processor.service.SupplierDetailsService;
import net.gcicom.cdr.processor.service.ValidationFailedException;
import net.gcicom.domain.imported.events.ImportedEvent;

@Component
public class Abzorbo2CDRToGciCDRMapper implements CDRMapper<AbzorbO2CDR> {

	private static final Logger LOG = LoggerFactory.getLogger(Abzorbo2CDRToGciCDRMapper.class);

	@Autowired
	private CDRMapperHelper h;
	
	@Autowired
	private GCICDRService s;
	
	@Autowired
	private SupplierDetailsService sSupplierDetails;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.gcicom.cdr.processor.entity.output.CDRMapper#convertToGCICDR(java.
	 * util.List)
	 */
	public List<ImportedEvent> convertToGCICDR(final List<AbzorbO2CDR> input, final Long eventFileId,
			final String fileName) throws ValidationFailedException {

		List<ImportedEvent> cdrs = new ArrayList<>();

		for (AbzorbO2CDR source : input) {

			LOG.debug("Converting AbzorbO2CDR to GCICDR" + source.toString());
			ImportedEvent cdr = new ImportedEvent();

			cdr.setOriginatingCLI(source.getOriginatingNumber());

			LocalDateTime et = source.getDate()
					.atTime(LocalTime.parse(source.getTime(), DateTimeFormatter.ISO_LOCAL_TIME));
			cdr.setEventTime(et);

			String dialedNumber = StringUtils.isEmpty(source.getDialedNumber()) ? source.getDescription()
					: source.getDialedNumber();
			
			cdr.setDialledCLI(dialedNumber);

			// populate billing reference details
			cdr = h.populateBillingReferenceDetails(cdr, et, source.getOriginatingNumber());

			cdr.setAccountingPeriod(formatYYYYMM(et));
			cdr.setCountry(source.getCountryOfOrigin());
			cdr.setEventDurationSecs(getDurationInSeconds(source.getDuration()));
			cdr.setEventFileID(eventFileId);
			cdr.setEventDurationSecs(getDurationInSeconds(source.getDuration()));

			cdr.setEventTypeID(getEventTypeID(source));
			
			// populates number range details from RatingDB.NumberRangeMap table
			cdr = h.populateNumberRangDetails(cdr, source.getDialedNumber(), et);		
			cdr.setPreRatedEventFlag(0);
			
			
			cdr.setSupplierAccountNumber(getSupplierAccountNumber(fileName));
			cdr.setSupplierCost(source.getSalesprice());
			cdr.setSupplierID(sSupplierDetails.getSupplierId(fileName));
			cdr.setSupplierNumberRangeMap(source.getDescription() + "_" + source.getChargeCode());
			cdr.setSupplierRatingPattern(source.getMobileClass() + "_" + source.getTimeBand());
			cdr.setSupplierTariffPlanID(source.getTariff());
			cdr.setSupplierServiceType(source.getNetwork() + "_" + source.getCallType());
			cdr.setTerminatingCLI(dialedNumber);
			Integer wdf = getWeekDayFlag(et);
			
			cdr.setWeekDayFlag(wdf);
			
			cdr.setTimePeriodID(h.getTimePeriodMapId(wdf, et.toLocalTime()));

			
			cdr.setSupplierTariffPlanID(source.getTariff());
			cdr.setCreatedBy(CDR_PROCESSOR_USER);
			cdr.setFileChecksum(s.getEventFile(eventFileId).getEventFileChecksum());

			// generate only after populating all the field in cdr
			cdr.setEventRecordKey(getEventRecordHash(cdr));
			LOG.debug("Converted cdr " + cdr.toString());
			cdrs.add(cdr);
		}

		return cdrs;

	}
	
	private String getSupplierAccountNumber(String fileName) {
		
		if (!StringUtils.isEmpty(fileName) && fileName.lastIndexOf("_") > 5) {
			
			return fileName.substring(fileName.lastIndexOf("_") - 4, fileName.lastIndexOf("_"));
		}
		return "3758";
	}

	/**If CallType = M
		 if "Country Of Origin" like ignoreCase "United Kingdom" or ""
		  if "Description" like "%SMS%" -> 506
		  else -> 504
		 else
		  if "Description" like "%SMS%" -> 509
		  else -> 508
		else if CallType = R
		 if "Country Of Origin" like ignoreCase "United Kingdom" or "" -> 507
		 else -> 510
	 * @return
	 * @throws ValidationFailedException 
	 */
	private Long getEventTypeID(AbzorbO2CDR s) throws ValidationFailedException {
		
		final String UK = "United Kingdom";
		
		String ct = s.getCallType();
		
		char callType = !StringUtils.isEmpty(ct) && (ct.equals("R") || ct.equals("M")) ? s.getCallType().charAt(0) : ' ';
		
		switch (callType) {
		
		case 'M':
			
			if (StringUtils.isEmpty(s.getCountryOfOrigin()) || s.getCountryOfOrigin().equalsIgnoreCase(UK)) {
				
				if (!StringUtils.isEmpty(s.getDescription()) && s.getDescription().matches("%SMS%")) {
					
					return new Long(506);
					
				} else {
					
					return new Long(504);
					
				}
				
			} else if (!StringUtils.isEmpty(s.getDescription()) && s.getDescription().matches("%SMS%")) {
				
				return new Long(509);
				
			}  else {
				
				return new Long(508);
			}

		case 'R':
			
			if (StringUtils.isEmpty(s.getCountryOfOrigin()) || s.getCountryOfOrigin().equalsIgnoreCase(UK)) {
				
				return new Long(507);
					
			} else {

				return new Long(510);
					
			}
			
		default:
			throw new ValidationFailedException("Invalid record, could not find any event type id");
		}
	}
}
