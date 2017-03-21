package net.gcicom.cdr.processor.entity.output;

import java.util.List;

public interface CDRMapper<T> {

	
	/**
	 * @param Vendor specific CDR as source which basically maps to valid csv row in cdr file converted to {@link GCICDR}
	 * @return
	 * @throws Exception any exception which occurred due to business validation
	 */
	public List<GCICDR> convertToGCICDR(final List<T> source) throws Exception;
}
