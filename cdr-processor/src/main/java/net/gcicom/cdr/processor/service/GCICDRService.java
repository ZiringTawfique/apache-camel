package net.gcicom.cdr.processor.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.camel.Body;
import org.apache.camel.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import net.gcicom.cdr.processor.entity.output.GCICDR;
import net.gcicom.cdr.processor.entity.output.MD5;
import net.gcicom.cdr.processor.repository.GCICDRRepository;
import net.gcicom.cdr.processor.repository.Md5Repository;
/**
 * Service to add all {@link GCICDR} to database. Preferably db insert should be batched
 * It needs more work as in business validation etc  
 *
 */
@Component("gciCDRService")
@Transactional
public class GCICDRService {

	Logger logger = LoggerFactory.getLogger(GCICDRService.class);
	
	@Autowired
	GCICDRRepository gciCDR;
	
	@Autowired
	Md5Repository md5Repo;
	
	@Autowired
	Auditor auditor;
	
	/**
	 * @param cdrs
	 */
	public void addCDR(List<GCICDR> cdrs) {
		
		for (GCICDR cdr : cdrs) {
			
			//business validation here then just batch insert or insert in invalid cdr
			logger.debug("Adding to db" + cdr.toString() );
			
			GCICDR result = gciCDR.save(cdr);
			
			logger.debug("Saved CDR " + result.toString() );

		}
		
		
	}
	
	public void validateMd5(final @Header("CamelFileNameConsumed") String fileName, final @Body InputStream is) throws IOException, AlreadyProcessedFileException {
		
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
		
		
		
	}
}
