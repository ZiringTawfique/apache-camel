package net.gcicom.cdr.processor.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import net.gcicom.cdr.processor.repository.allspark.BillingReferenceRepository;
import net.gcicom.cdr.processor.repository.imported.events.EventFileRepository;
import net.gcicom.cdr.processor.repository.imported.events.GCICDRRepository;
import net.gcicom.cdr.processor.repository.rating.NumberRangeMapRepository;
import net.gcicom.cdr.processor.repository.rating.TimePeriodMapRepository;
import net.gcicom.domain.allspark.BillingReference;
import net.gcicom.domain.imported.events.EventFile;
import net.gcicom.domain.imported.events.ImportedEvent;
import net.gcicom.domain.rating.NumberRangeMap;
import net.gcicom.domain.rating.TimePeriodMap;

/**
 * Service to add all {@link GCICDR} to database. Preferably db insert should be
 * batched It needs more work as in business validation etc
 *
 */
@Component("gciCDRService")
public class GCICDRService {

	private static final Logger logger = LoggerFactory.getLogger(GCICDRService.class);

	@Autowired
	private GCICDRRepository gciCDR;

	@Autowired
	private BillingReferenceRepository billRefRepo;
	
	@Autowired
	private NumberRangeMapRepository nrRepo;
	
	@Autowired
	private TimePeriodMapRepository tpmRepo;
	
	@Autowired
	private EventFileRepository eRepo;


	/**
	 * Adds valid call details records to {@link ImportedEvent} table in
	 * database
	 * 
	 * @param cdrs
	 */
	public void addCDR(List<ImportedEvent> cdrs) {

		for (ImportedEvent cdr : cdrs) {

			
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
	public List<BillingReference> getBillingReference(String billRef, LocalDateTime eventTime) {

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
	public List<NumberRangeMap> getNumberRanges(List<Long> dialedNumbers, LocalDateTime eventTime) {
		
		return nrRepo.findNumberRangeMap(dialedNumbers, eventTime);
		
	}
	
	/**
	 * @param day
	 * @param t
	 * @return
	 */
	public List<TimePeriodMap> getTimePeriodMap(int day, LocalTime t) {
		
		return tpmRepo.findTimePeriod(day, t);
		
	}
	
	/**
	 * @param eventFileId
	 * @return {@link EventFile}
	 */
	public EventFile getEventFile(Long eventFileId) {
		
		return eRepo.findOne(eventFileId);
	}
	
	
	
}