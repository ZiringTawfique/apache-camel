package net.gcicom.cdr.processor.transformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class BTOpenReachCDRTransformer {
	
	private static Logger logger = LoggerFactory.getLogger(BTOpenReachCDRTransformer.class);
	
	/**Remove TAG so that a csv row can be mapped to pojo
	 * @param cdr
	 * @return
	 */
	public String transform(String cdr) {
		
		if (!StringUtils.isEmpty(cdr) && cdr.indexOf("Event: ") != -1) {
			
			cdr = cdr.substring(cdr.indexOf("Event: ") + 7);
			
			
		}

		logger.debug("Transformed CDR " + cdr);
		return cdr;
	}

}
