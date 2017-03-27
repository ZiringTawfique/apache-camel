package net.gcicom.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.util.StringUtils;

public abstract class NumberRangeUtils {

	/**Returns an array of given dialed number. However if provided dialed number is non-numeric 
	 * except international number which starts with + will be ignored and an empty list is returned. Its uses responsibility to supply correct input
	 * @param dialedNumber
	 * @return
	 */
	public static List<Long> getNumberRanges(String dialedNumber) {
		
		String r = prefixCountryCode(dialedNumber);

		if (StringUtils.isEmpty(dialedNumber) || !org.apache.commons.lang3.StringUtils.isNumeric(r) ) {
			
			return Collections.EMPTY_LIST;
			
		} else {
			
			List<Long> l = new ArrayList<>();
			
			
			for (int i = 0; i < r.length(); i++) {
				
				
				l.add(Long.parseLong(r.substring(0, r.length() - i)));
				
			}
			
			return l;
		}
		
		
	}
	
	
	private static String prefixCountryCode(final String dialedNumber) {
		
		if (StringUtils.isEmpty(dialedNumber)) {
			
			return dialedNumber;//return as is
			
		} else if (dialedNumber.startsWith("00") ) {
			
			return StringUtils.trimLeadingCharacter(dialedNumber, '0');

		} else if(dialedNumber.startsWith("+")) {
			
			return StringUtils.trimLeadingCharacter(dialedNumber, '+');

		} else if(dialedNumber.startsWith("0")) {
			
			return "44" + StringUtils.trimLeadingCharacter(dialedNumber, '0');

		}
			
		//otherwise return as is
		return dialedNumber;
			
	}
}
