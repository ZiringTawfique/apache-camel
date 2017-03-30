package net.gcicom.cdr.processor.entity.mapper;

import static net.gcicom.cdr.processor.common.AppConstants.CDR_PROCESSOR_USER;
import static net.gcicom.cdr.processor.common.AppConstants.CDRMapperConstants.UK;
import static net.gcicom.cdr.processor.util.EventRecordKeyGenerator.getEventRecordHash;
import static net.gcicom.common.util.DateTimeUtil.formatYYYYMM;
import static net.gcicom.common.util.DateTimeUtil.getWeekDayFlag;
import static net.gcicom.cdr.processor.entity.mapper.VodafoneMapperHelper.getSupplierAccountNumber;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.gcicom.cdr.processor.entity.input.VodafoneThusNTS;
import net.gcicom.cdr.processor.service.GCICDRService;
import net.gcicom.cdr.processor.service.SupplierDetailsService;
import net.gcicom.cdr.processor.service.ValidationFailedException;
import net.gcicom.domain.imported.events.ImportedEvent;

@Component
public class VodafoneThusNTSMapper implements CDRMapper<VodafoneThusNTS> {

	private static final Logger LOG = LoggerFactory.getLogger(VodafoneThusNTSMapper.class);

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
	public List<ImportedEvent> convertToGCICDR(final List<VodafoneThusNTS> input, final Long eventFileId,
			final String fileName) throws ValidationFailedException {

		List<ImportedEvent> cdrs = new ArrayList<>();

		for (VodafoneThusNTS source : input) {

			LOG.debug("Converting VodafoneThusIDA to GCICDR" + s.toString());
			ImportedEvent cdr = new ImportedEvent();

			cdr.setOriginatingCLI(source.getServiceOrigination());

			LocalDateTime et = source.getDateTime();
			cdr.setEventTime(et);

			
			cdr.setDialledCLI(source.getDialedNumber());

			// populate billing reference details
			cdr = h.populateBillingReferenceDetails(cdr, et, source.getDialedNumber());

			cdr.setAccountingPeriod(formatYYYYMM(et));
			cdr.setCountry(UK);
			cdr.setEventDurationSecs(source.getDuration());
			cdr.setEventFileID(eventFileId);

			cdr.setEventTypeID(515L);//fixed as per CDRFormatSpecifciation spread sheet
			
			// populates number range details from RatingDB.NumberRangeMap table
			cdr = h.populateNumberRangDetails(cdr, source.getDialedNumber(), et);		
			cdr.setPreRatedEventFlag(0);
			
			
			cdr.setSupplierAccountNumber(getSupplierAccountNumber(fileName));
			cdr.setSupplierCost(source.getWholesalePrice());
			cdr.setSupplierID(sSupplierDetails.getSupplierId(fileName));
			cdr.setSupplierNumberRange(source.getDestinationCode());
			cdr.setSupplierNumberRangeMap(source.getDestOrServiceDescription());
			cdr.setSupplierRatingPattern(source.getChargePoint() + "_" + source.getBand());
			cdr.setSupplierServiceType(source.getCallType());
			cdr.setTerminatingCLI(source.getTerminationNumber());
			
			Integer wdf = getWeekDayFlag(et);
			
			cdr.setWeekDayFlag(wdf);
			
			cdr.setTimePeriodID(h.getTimePeriodMapId(wdf, et.toLocalTime()));

			
			cdr.setCreatedBy(CDR_PROCESSOR_USER);
			cdr.setFileChecksum(s.getEventFile(eventFileId).getEventFileChecksum());

			// generate only after populating all the field in cdr
			cdr.setEventRecordKey(getEventRecordHash(cdr));
			LOG.debug("Converted cdr " + cdr.toString());
			cdrs.add(cdr);
		}

		return cdrs;

	}
}
