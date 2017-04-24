package net.gcicom.order.processor.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import net.gcicom.domain.allspark.BillingReference;
import net.gcicom.domain.allspark.CustomerProductCharge;
//import net.gcicom.order.processor.entity.output.GCIChargeImport;
//import net.gcicom.order.processor.entity.output.MD5;
import net.gcicom.order.processor.repository.BillingReferenceRepository;
import net.gcicom.order.processor.repository.CustomerProductChargeRepository;
import static org.apache.camel.Exchange.FILE_NAME_CONSUMED;
import static net.gcicom.order.processor.common.AppConstants.TOTAL_RECORD_COUNT;
//import net.gcicom.order.processor.repository.GCIChargeImportRepository;
//import net.gcicom.order.processor.repository.Md5Repository;
/**
 * Service to add all {@link GCIChargeImport} to database. Preferably db insert should be batched
 * It needs more work as in business validation etc  
 *
 */
@Component("gciChargeImportService")
@Transactional
public class GCIChargeImportService {

	Logger logger = LoggerFactory.getLogger(GCIChargeImportService.class);
	
//	@Autowired
//	GCIChargeImportRepository gciChargeImportRepo;
	
	@Autowired
	BillingReferenceRepository billingReferenceRepo;
	
	
	@Autowired
	CustomerProductChargeRepository customerProductChargeRepo;
	
//	@Autowired
//  Md5Repository md5Repo;
	
//@Autowired
//	Auditor auditor;
	
	/**
	 * @param cdrs
	 */
	/*public void addChargeImport(List<GCIChargeImport> cdrs) {
		
		for (GCIChargeImport cdr : cdrs) {
			
			//business validation here then just batch insert or insert in invalid cdr
			logger.debug("Adding to db" + cdr.toString() );
			
			GCIChargeImport result = gciChargeImportRepo.save(cdr);
			
			logger.debug("Saved CDR " + result.toString() );

		}
		
		
	}
	*/
	public void addBillingReferences(List<BillingReference> cdrs) {
		
		for (BillingReference cdr : cdrs) {
			
			//business validation here then just batch insert or insert in invalid cdr
			logger.debug("Adding to db" + cdr.toString() );
			
			BillingReference result = billingReferenceRepo.save(cdr);
			
			logger.debug("Saved CDR " + result.toString() );

		}
	}
	
	public void addBillingReference(BillingReference cdr) {
		
	
			//business validation here then just batch insert or insert in invalid cdr
			logger.debug("Adding to db" + cdr.toString() );
			
			BillingReference result = billingReferenceRepo.save(cdr);
			
			logger.debug("Saved CDR " + result.toString() );

		}
	
	
	/*public void validateMd5(final @Header("CamelFileNameConsumed") String fileName, final @Body InputStream is) throws IOException, AlreadyProcessedFileException {
		String METHOD_NAME="validateMd5";
		logger.info("Entering ++++++++++ " + METHOD_NAME );
		String hex = DigestUtils.md5DigestAsHex(is);
		logger.info("Hex -----------------------" + hex + "and file " + fileName );

		List<MD5> md5s= md5Repo.findByHex(hex);
		is.close();//require for smooth file handling later once they processed by camel consumer
		if (md5s.size() == 0) {
			
			MD5 md5 = new MD5();
			md5.setHex(hex);
			md5.setFile(fileName);
			
			md5Repo.save(md5);
			
			
		} else {

			throw new AlreadyProcessedFileException(String.format("File %s, with Hex %s already processed", hex, fileName));
		}
		
		logger.info("Exit**********  " + METHOD_NAME );	
		
	}
	*/
	
	public void setRecordCount(final Exchange ex)
			throws IOException, AlreadyProcessedFileException {

		String eventFileChecksum = DigestUtils.md5DigestAsHex(ex.getIn().getBody(InputStream.class));
		String fileName = ex.getIn().getHeader(FILE_NAME_CONSUMED, String.class);
		
			
		ex.getIn().setHeader(TOTAL_RECORD_COUNT, getNoOfRecordsInFile(ex.getIn().getBody(InputStream.class)));
		

		} 
	
	
	
	/**Count no of lines in CSV file
	 * @param is
	 * @return
	 * @throws IOException
	 */
	private Integer getNoOfRecordsInFile(InputStream is) throws IOException {
		
		int noOfLines = 0;
		
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
		logger.info("SETTING THE TOTAL COUNT  -----------------------" + noOfLines);		
		return noOfLines;

	}
}
