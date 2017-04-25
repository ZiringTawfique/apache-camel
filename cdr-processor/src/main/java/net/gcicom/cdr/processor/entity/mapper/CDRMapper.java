package net.gcicom.cdr.processor.entity.mapper;

import java.util.List;

import net.gcicom.domain.imported.events.ImportedEvent;

public interface CDRMapper<T> {

	
	/**
	 * @param Vendor specific CDR as source which basically maps to valid csv row in cdr file converted to {@link ImportedEvent}
	 * @return
	 * @throws Exception any exception which occurred due to business validation
	 */
	public List<ImportedEvent> convertToGCICDR(final List<T> source, final Long eventFileId, final String fileName) throws Exception;
}
