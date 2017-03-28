package net.gcicom.cdr.processor.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.gcicom.cdr.processor.repository.allspark.BillingReferenceRepository;
import net.gcicom.cdr.processor.repository.imported.events.GCICDRRepository;
import net.gcicom.cdr.processor.repository.rating.NumberRangeMapRepository;
import net.gcicom.domain.allspark.BillingReference;
import net.gcicom.domain.imported.events.ImportedEvent;
import net.gcicom.domain.rating.NumberRangeMap;

/**
 * Service to add all {@link GCICDR} to database. Preferably db insert should be
 * batched It needs more work as in business validation etc
 *
 */
@Component("gciCDRService")
@Transactional
public class GCICDRService {

	private static final Logger logger = LoggerFactory.getLogger(GCICDRService.class);

	@Autowired
	private GCICDRRepository gciCDR;



	@Autowired
	private BillingReferenceRepository billRefRepo;
	

	
	@Autowired
	private NumberRangeMapRepository nrRepo;


	/**
	 * Adds valid call details records to {@link ImportedEvent} table in
	 * database
	 * 
	 * @param cdrs
	 */
	public void addCDR(List<ImportedEvent> cdrs) {

		for (ImportedEvent cdr : cdrs) {

			// business validation here then just batch insert or insert in
			// invalid cdr
			logger.debug("Adding to db" + cdr.toString());

			ImportedEvent result = gciCDR.save(cdr);

			logger.debug("Saved CDR " + result.toString());

		}

	}

	/**Retrieves billing reference details from AllSpark.BillingReference table
	 * @param billRef
	 * @param startDt
	 * @param endDt
	 * @return
	 */
	public List<BillingReference> getBillingReference(String billRef, Date eventTime) {

		return billRefRepo
				.findBillingReferenceDetails(
						billRef, eventTime);
	}
	
	
	/** Gets number range for given dialed number range and start date and end date
	 * @param dialedNumbers
	 * @param startDt
	 * @param endDt
	 * @return
	 */
	public List<NumberRangeMap> getNumberRanges(List<Long> dialedNumbers, Date eventTime) {
		
		return nrRepo.findNumberRangeMap(dialedNumbers, eventTime);
		
	}
	
	
	
}