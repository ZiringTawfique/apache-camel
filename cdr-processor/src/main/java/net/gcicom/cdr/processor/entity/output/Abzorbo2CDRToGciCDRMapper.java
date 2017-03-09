package net.gcicom.cdr.processor.entity.output;


import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import net.gcicom.cdr.processor.entity.input.AbzorbO2CDR;


@Component
public class Abzorbo2CDRToGciCDRMapper {
			
	
		Logger logger = LoggerFactory.getLogger(Abzorbo2CDRToGciCDRMapper.class);

	
		public List<GCICDR> convertToGCICDR(final List<AbzorbO2CDR> input) {
			
			List<GCICDR> cdrs = new ArrayList<>();

			for (AbzorbO2CDR source : input) {
				
				logger.debug("Converting AbzorbO2CDR to GCICDR" + source.toString());

				GCICDR cdr = new GCICDR();
				CDRKey id = new CDRKey(source.getOriginatingNumber(), 
							source.getOriginatingNumber(), 
							source.getDialedNumber(), 
							Timestamp.valueOf(source.getDate().atTime(LocalTime.parse(source.getTime(), DateTimeFormatter.ISO_LOCAL_TIME))));
				cdr.setId(id);
				cdr.setDuration(source.getDuration());
				cdr.setSupplierId(source.getSupplier());
				logger.debug("Converted cdr " + cdr.toString());
				cdrs.add(cdr);
			}
			
			

			return cdrs;
			
		}
	
	
}
