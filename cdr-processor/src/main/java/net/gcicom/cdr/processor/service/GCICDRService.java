package net.gcicom.cdr.processor.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.gcicom.cdr.processor.entity.output.GCICDR;
import net.gcicom.cdr.processor.repository.GCICDRRepository;
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
}
