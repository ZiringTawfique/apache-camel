package net.gcicom.cdr.processor.service;

import static net.gcicom.cdr.processor.common.AppConstants.CDR_PROCESSOR_USER;
import static net.gcicom.cdr.processor.util.DateTimeUtil.getTodaysDate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.camel.Body;
import org.apache.camel.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import net.gcicom.cdr.processor.repository.allspark.BillingReferenceRepository;
import net.gcicom.cdr.processor.repository.imported.events.EventFileRepository;
import net.gcicom.cdr.processor.repository.imported.events.GCICDRRepository;
import net.gcicom.domain.allspark.BillingReference;
import net.gcicom.domain.imported.events.EventFile;
import net.gcicom.domain.imported.events.ImportedEvent;

/**
 * Service to add all {@link GCICDR} to database. Preferably db insert should be
 * batched It needs more work as in business validation etc
 *
 */
@Component("gciCDRService")
@Transactional
public class GCICDRService {

	Logger logger = LoggerFactory.getLogger(GCICDRService.class);

	@Autowired
	GCICDRRepository gciCDR;

	@Autowired
	EventFileRepository eventRepo;

	@Autowired
	BillingReferenceRepository billRefRepo;

	@Autowired
	Auditor auditor;

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
	public void validateMd5(final @Header("CamelFileNameConsumed") String fileName, final @Body InputStream is)
			throws IOException, AlreadyProcessedFileException {

		String eventFileChecksum = DigestUtils.md5DigestAsHex(is);
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
			eventRepo.save(ef);

		} else {

			throw new AlreadyProcessedFileException(
					String.format("File %s, with Hex %s already processed", fileName, eventFileChecksum));
		}

	}

	public List<BillingReference> getBillingReference(String billRef, Date startDt, Date endDt) {

		return billRefRepo
				.findByBillingReferenceAndBillingReferenceStartDateLessThanEqualAndBillingReferenceEndDateGreaterThanEqual(
						billRef, startDt, endDt);
	}
}