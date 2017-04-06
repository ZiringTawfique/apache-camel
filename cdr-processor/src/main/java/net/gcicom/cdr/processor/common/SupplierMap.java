package net.gcicom.cdr.processor.common;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import net.gcicom.cdr.processor.entity.mapper.InvalidFileException;

@Component
public class SupplierMap {
	
	public static final Logger LOG = LoggerFactory.getLogger(SupplierMap.class);
	
	/*These supplier names are sourced from RefrenceDB.Supplier table*/
	
	public static final String MOB_ABZORB_O2 = "MOB_ABZORB_02";
	
	public static final String PSTN_VODAFONETHUS_DIR = "PSTN_VODAFONETHUS_DIR";

	public static final String PSTN_BTOPENREACH = "PSTN_BTOPENREACH";
	
	public static final String PSTN_VODAFONETHUS_IDA = "PSTN_VODAFONETHUS_IDA";
	
	
	
	
	@Value("${gci.btopenreach.file.name.pattern}")
	private String btoFP;
	
	@Value("${gci.abzorb2cdr.file.name.pattern}")
	private String abzorbo2FP;
	
	@Value("${gci.nts.vodathus.file.name.pattern}")
	private String ntsVodathusFP;
	
	@Value("${gci.pstn.vodathus.file.name.pattern}")
	private String pstnVodathusFP;
	
	private static Map<String, String> s = new HashMap<>();

	@PostConstruct 
	public void init() {
		
		//put all supplier names and file pattern here. This mapping will be used to retrieve supplier name from file name
		s.put(btoFP, PSTN_BTOPENREACH);
		s.put(abzorbo2FP, MOB_ABZORB_O2);
		s.put(ntsVodathusFP,  PSTN_VODAFONETHUS_IDA);
		s.put(pstnVodathusFP, PSTN_VODAFONETHUS_DIR);
		
		LOG.info("Supplier Maps has been initialized with {} supplier elements", s.size());

	}
		
	
	/** Garbage in garbage out. Let user deal with garbage nulls
	 * @param fileName
	 * @return
	 */
	public static String getSupplierName(String fileName) {
		
		if (!StringUtils.isEmpty(fileName) && !s.isEmpty()) {

			for (String p : s.keySet()) {
				
				LOG.debug("File pattern to compare with {} ", p);
				
				if(StringUtils.isEmpty(p)) {
					
					throw new AssertionError("Supplier map has not been properly initialized");
					
				}
				
				if (Pattern.matches(p, fileName)) {
					
					return s.get(p);
					
				}
			}
		}
		
		throw new InvalidFileException(String.format("Filename %s supplied did not match expected format ", fileName));
	}
}
