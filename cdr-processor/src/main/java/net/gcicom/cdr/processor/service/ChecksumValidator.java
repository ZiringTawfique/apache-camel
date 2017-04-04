package net.gcicom.cdr.processor.service;

import static net.gcicom.cdr.processor.common.AppConstants.CDR_EVENT_FILE_CHECKSUM;
import static net.gcicom.cdr.processor.common.AppConstants.CDR_EVENT_FILE_ID;
import static net.gcicom.cdr.processor.common.AppConstants.CDR_PROCESSOR_USER;
import static net.gcicom.common.util.DateTimeUtil.getTodaysDate;
import static org.apache.camel.Exchange.FILE_NAME_CONSUMED;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import net.gcicom.cdr.processor.repository.imported.events.EventFileRepository;
import net.gcicom.domain.imported.events.EventFile;
import net.gcicom.domain.imported.events.EventFileDetail;

@Component
public class ChecksumValidator {
	
	private static final Logger LOG = LoggerFactory.getLogger(ChecksumValidator.class);

	@Autowired
	private EventFileRepository eventRepo;
	
	@Autowired
	private SupplierDetailsService s;

	/**
	 * Validates file checksum
	 * 
	 * @param fileName
	 * @param is
	 * @throws IOException
	 * @throws AlreadyProcessedFileException
	 * @throws ValidationFailedException 
	 */
	public void validateMd5(final Exchange ex)
			throws IOException, AlreadyProcessedFileException, ValidationFailedException {

		String eventFileChecksum = DigestUtils.md5DigestAsHex(ex.getIn().getBody(InputStream.class));
		String fileName = ex.getIn().getHeader(FILE_NAME_CONSUMED, String.class);
		LOG.info("eventFileChecksum -----------------------" + eventFileChecksum + "and file " + fileName);

		List<EventFile> md5s = eventRepo.findByEventFileChecksum(eventFileChecksum);
		if (md5s.size() == 0) {

			EventFile ef = new EventFile();
			ef.setEventFileChecksum(eventFileChecksum);
			ef.setEventFileName(fileName);
			ef.setCreatedBy(CDR_PROCESSOR_USER);
			ef.setDateProcessed(getTodaysDate());
			ef.setCreatedDate(getTodaysDate());
			ef.setSupplierID(s.getSupplierId(fileName));
			//need the count in csv file.
			
			
			
			EventFileDetail efd = new EventFileDetail();
			efd.setNumberOfRecords(getNoOfRecordsInFile(ex.getIn().getBody(InputStream.class)));
			ef.addEventFileDetail(efd);
			ef.setEventFileDetails(ef.getEventFileDetails());
			
			EventFile result = eventRepo.save(ef);
			//these headers are being used for further processing.
			//always get in message and set required headers do not meddle using out message
			ex.getIn().setHeader(CDR_EVENT_FILE_CHECKSUM, result.getEventFileChecksum());
			ex.getIn().setHeader(CDR_EVENT_FILE_ID, result.getEventFileID());

		} else {

			throw new AlreadyProcessedFileException(
					String.format("File %s, with Hex %s already processed", fileName, eventFileChecksum));
		}

	}
	
	
	
	/**Count no of lines in CSV file
	 * @param is
	 * @return
	 * @throws IOException
	 */
	private Integer getNoOfRecordsInFile(InputStream is) throws IOException {
		
		int noOfLines = 0;
		
		/*byte[] b = new byte[1024];
		
		int read = 0;
		
		boolean isEmpty = true;
		try {
			
			while((read = is.read(b)) != -1) {
				
				isEmpty = false;
				
				for (int i = 0; i < read; i++) {
					
					if (b[i] == '\n') {
						
						++noOfLines;
						
					}
				}
			}
		} finally {
			
			is.close();
		}*/
		
		Scanner s = new Scanner(is);
		try {


			s.useDelimiter(System.lineSeparator());
			while(s.hasNext()) {
				
				s.next();
				++noOfLines;
			}
			
		} finally {

			s.close();
		}
		
		
		return noOfLines;
				
		//return noOfLines = !isEmpty && noOfLines == 0 ? 1 : noOfLines;
	}
}
