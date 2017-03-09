package net.gcicom.cdr.processor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.gcicom.cdr.processor.entity.output.InvalidCDR;
import net.gcicom.cdr.processor.repository.InvalidCDRRepository;
/**
 * Invalid CDR service 
 *
 */
@Component
public class InvalidCDRService {
	
	Logger logger = LoggerFactory.getLogger(InvalidCDRService.class);
	
	@Autowired
	private InvalidCDRRepository repo;
			
	/**
	 * @param invalid
	 */
	public void handleInvalidCDR(InvalidCDR invalid) {
		
		logger.debug("Csv supplied has a invalid record : " + invalid);
		InvalidCDR result = repo.save(invalid);
		logger.debug("Stored invalid record : " + result);

	}

}
