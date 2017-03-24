package net.gcicom.cdr.processor.service;

import static net.gcicom.cdr.processor.common.AppConstants.CDR_EVENT_FILE_CHECKSUM;
import static net.gcicom.cdr.processor.common.AppConstants.CDR_EVENT_FILE_ID;
import static net.gcicom.cdr.processor.common.AppConstants.CDR_PROCESSOR_USER;
import static net.gcicom.cdr.processor.util.DateTimeUtil.getTodaysDate;
import static org.apache.camel.Exchange.FILE_NAME_CONSUMED;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import net.gcicom.cdr.processor.repository.allspark.BillingReferenceRepository;
import net.gcicom.cdr.processor.repository.imported.events.EventFileRepository;
import net.gcicom.cdr.processor.repository.imported.events.GCICDRRepository;
import net.gcicom.cdr.processor.repository.rating.NumberRangeMapRepository;
import net.gcicom.cdr.processor.repository.rating.SupplierRepository;
import net.gcicom.domain.allspark.BillingReference;
import net.gcicom.domain.imported.events.EventFile;
import net.gcicom.domain.imported.events.ImportedEvent;
import net.gcicom.domain.rating.NumberRangeMap;
import net.gcicom.domain.rating.Supplier;

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
	private EventFileRepository eventRepo;

	@Autowired
	private BillingReferenceRepository billRefRepo;
	
	@Autowired
	private SupplierRepository sRepo;
	
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

	/**
	 * Validates file checksum
	 * 
	 * @param fileName
	 * @param is
	 * @throws IOException
	 * @throws AlreadyProcessedFileException
	 */
	public void validateMd5(final Exchange ex, final @Body InputStream is)
			throws IOException, AlreadyProcessedFileException {

		String eventFileChecksum = DigestUtils.md5DigestAsHex(is);
		String fileName = ex.getIn().getHeader(FILE_NAME_CONSUMED, String.class);
		logger.info("eventFileChecksum -----------------------" + eventFileChecksum + "and file " + fileName);

		List<EventFile> md5s = eventRepo.findByEventFileChecksum(eventFileChecksum);
		is.close();// require for smooth file handling later
		if (md5s.size() == 0) {

			EventFile ef = new EventFile();
			ef.setEventFileChecksum(eventFileChecksum);
			ef.setEventFileName(fileName);
			ef.setCreatedBy(CDR_PROCESSOR_USER);
			ef.setDateProcessed(getTodaysDate());
			ef.setCreatedDate(getTodaysDate());
			EventFile result = eventRepo.save(ef);
			//these headers are being used for further processing
			ex.getIn().setHeader(CDR_EVENT_FILE_CHECKSUM, result.getEventFileChecksum());
			ex.getIn().setHeader(CDR_EVENT_FILE_ID, result.getEventFileID());

		} else {

			throw new AlreadyProcessedFileException(
					String.format("File %s, with Hex %s already processed", fileName, eventFileChecksum));
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
	
	/**
	 * @param supplierName
	 * @return
	 */
	public List<Supplier> getSupplier(final String supplierName) {
		
		return sRepo.findBySupplierName(supplierName);
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