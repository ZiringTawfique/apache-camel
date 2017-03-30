package net.gcicom.cdr.processor.common;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@PropertySource("classpath:application.properties")
public class SupplierMap {
	
	public static final Logger LOG = LoggerFactory.getLogger(SupplierMap.class);
	
	/*These supplier names are sourced from RatingDB.Supplier table*/
	public static final String CONF_INTERCALL = "CONF_INTERCALL";
	
	public static final String MOB_ABZORB =  "MOB_ABZORB";
	public static final String MOB_ABZORB_O2 = "MOB_ABZORB_O2";
	public static final String MOB_FO2 = "MOB_FO2";
	public static final String MOB_TMOBILE = "MOB_TMOBILE";
	public static final String MOB_VODABUSMOB = "MOB_VODABUSMOB";
	public static final String NTS_6DG = "NTS_6DG";
	public static final String NTS_BTAGILEMEDIA = "NTS_BTAGILEMEDIA";
	public static final String NTS_DAISYCW = "NTS_DAISYCW";
	public static final String NTS_GAMMA = "NTS_GAMMA";
	public static final String NTS_OBIT = "NTS_OBIT";
	public static final String NTS_OPAL = "NTS_OPAL";
	public static final String NTS_REDSTONE = "NTS_REDSTONE";
	public static final String NTS_TOTEM = "NTS_TOTEM";
	public static final String NTS_VERIZON = "NTS_VERIZON";
	public static final String NTS_VODA = "NTS_VODA";
	public static final String NTS_VODACW = "NTS_VODACW";
	public static final String NTS_VODAEnergis = "NTS_VODAEnergis";
	public static final String NTS_VODATHUS = "NTS_VODATHUS";
	public static final String NTS_WAVECREST = "NTS_WAVECREST";
	public static final String NTS_YOURCOMMS = "NTS_YOURCOMMS";
	public static final String PSTN_6DG = "PSTN_6DG";
	public static final String PSTN_BTO = "PSTN_BTO";
	public static final String PSTN_BTW = "PSTN_BTW";
	public static final String PSTN_EIRCOM = "PSTN_EIRCOM";
	public static final String PSTN_GAMMA = "PSTN_GAMMA";
	public static final String PSTN_GLOBALCROSS = "PSTN_GLOBALCROSS";
	public static final String PSTN_KCOM = "PSTN_KCOM";
	public static final String PSTN_OBIT = "PSTN_OBIT";
	public static final String PSTN_OPAL = "PSTN_OPAL";
	public static final String PSTN_SKYBLUE = "PSTN_SKYBLUE";
	public static final String PSTN_VIATEL = "PSTN_VIATEL";
	public static final String PSTN_VIRGIN = "PSTN_VIRGIN";
	public static final String PSTN_VODACW = "PSTN_VODACW";
	public static final String PSTN_VODATHUS = "PSTN_VODATHUS";
	public static final String SIP_BPN = "SIP_BPN";
	public static final String SIP_BTIPVS = "SIP_BTIPVS";
	public static final String SIP_BTIPVS_INBOUND = "SIP_BTIPVS_INBOUND";
	public static final String SIP_GAMMA = "SIP_GAMMA";
	public static final String SIP_GAMMA_COM = "SIP_GAMMA_COM";
	public static final String SIP_GAMMA_HZN = "SIP_GAMMA_HZN";
	public static final String SIP_GCI = "SIP_GCI";
	public static final String SIP_HIPCOM = "SIP_HIPCOM";
	public static final String SIP_HIPCOM_INBOUND = "SIP_HIPCOM_INBOUND";
	public static final String SIP_INTECH = "SIP_INTECH";
	public static final String SIP_NTS_GCI = "SIP_NTS_GCI";
	public static final String SIP_TVF = "SIP_TVF";
	public static final String SIP_VIPEX = "SIP_VIPEX";
	public static final String SIP_VIPEX_Inbound = "SIP_VIPEX_Inbound";
	public static final String SIP_VODASTORM = "SIP_VODASTORM";
	
	
	
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
	private void populateSuppliers() {
		
		//put all supplier names and file pattern here. This mapping wiil be used to retrieve supplier name from file name
		s.put(btoFP, PSTN_BTO);
		s.put(abzorbo2FP, MOB_ABZORB_O2);
		s.put(ntsVodathusFP, NTS_VODATHUS);
		s.put(pstnVodathusFP, PSTN_VODATHUS);
		
		LOG.info("Supplier Maps has been initialized with {} supplier elements", s.size());
	}
	
	/** Garbabge in garbage out. Let user deal with garbage nulls
	 * @param fileName
	 * @return
	 */
	public static String getSupplierName(String fileName) {
		
		if (StringUtils.isEmpty(fileName)) {
			
			return null;
		}
		
		for (String p : s.keySet()) {
			
			LOG.info("File pattern to compare {} ", p);
			
			if(StringUtils.isEmpty(p)) {
				
				throw new AssertionError("Supplier map has not been properly initialized");
				
			}
			
			if (Pattern.matches(p, fileName)) {
				
				return s.get(p);
				
			}
		}
		return null;
	}
}
