package net.gcicom.cdr.processor.entity.mapper;

import org.springframework.util.StringUtils;

public abstract class VodafoneMapperHelper {

	/**
	 * @param fileName
	 * @return
	 */
	public static String getSupplierAccountNumber(String fileName) {
		
		if (!StringUtils.isEmpty(fileName)) {
			
			String[] fs = StringUtils.tokenizeToStringArray(fileName, "_");
			
			if (fs.length >= 2) {
			
				return fs[fs.length - 2];
			}
			
		}
		
		throw new InvalidFileException(String.format("File %s does not match configured format", fileName));
	}
}
