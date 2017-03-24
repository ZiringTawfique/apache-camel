package net.gcicom.cdr.processor.entity.mapper;

import static net.gcicom.cdr.processor.common.SupplierMap.getSupplierName;
import static net.gcicom.cdr.processor.util.NumberRangeUtils.getNumberRanges;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import net.gcicom.cdr.processor.service.GCICDRService;
import net.gcicom.cdr.processor.service.ValidationFailedException;
import net.gcicom.domain.allspark.BillingReference;
import net.gcicom.domain.imported.events.ImportedEvent;
import net.gcicom.domain.rating.NumberRangeMap;
import net.gcicom.domain.rating.Supplier;

@Component
public final class CDRMapperHelper {
	
	private static final Logger LOG = LoggerFactory.getLogger(CDRMapperHelper.class);
	
	@Autowired
	private GCICDRService service;

	/**
	 * @param fileName
	 * @return
	 * @throws ValidationFailedException
	 */
	public Long getSupplierId(String fileName) throws ValidationFailedException {

		final String sName = getSupplierName(fileName);
		
		LOG.debug("For file {} supplier is {}", fileName, sName);
		
		List<Supplier> ss = service.getSupplier(sName);
		
		for (Supplier s : ss) {
			
			if (!ObjectUtils.isEmpty(s.getSupplierID())) {
				
				return s.getSupplierID();
			}
		}
		
		throw new ValidationFailedException(String.format("No supplier exist for given %s file", fileName));
	}
	
	/**Populates billing reference details from AllSpark.BillingReference table
	 * for given event time and origin number as billing reference
	 * @param cdr
	 * @param eventTime
	 * @param originNbr
	 * @return
	 * @throws ValidationFailedException
	 */
	public ImportedEvent populateBillingReferenceDetails(ImportedEvent cdr, Date eventTime, String originNbr)
			throws ValidationFailedException {

		LOG.debug(String.format(
				"Populating billing reference details for %s billing reference and for %s event date time", originNbr,
				eventTime));
		// get billing reference number if not found mark it error record
		List<BillingReference> bfs = service.getBillingReference(originNbr, eventTime);
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
	
	public NumberRangeMap getNumberRange(final String dialedNumber, Date eventTime) {
		
		List<Long> l = getNumberRanges(dialedNumber);
		
		if (l.isEmpty()) {
			
			return null;
			
		}
		List<NumberRangeMap> r = service.getNumberRanges(l, eventTime);
		
		for (Long range : l) {
			
			for (NumberRangeMap nrm : r) {
				
				if(range.equals(nrm.getNumberRange())) {
					
					return nrm;
				}
			}
		}
		
		return null;
		
	}

	public ImportedEvent populateNumberRangDetails(ImportedEvent cdr, String dialedNumber, Date eventTime) {
		
		NumberRangeMap nrm = getNumberRange(dialedNumber, eventTime);
		
		if (!ObjectUtils.isEmpty(nrm)) {
			
			cdr.setNumberRange(nrm.getNumberRange());
			cdr.setNumberRangeClassification(nrm.getNumberRangeClassification());
			cdr.setNumberRangeType(nrm.getNumberRangeType());
			
		}

		return cdr;
	}
	

}
