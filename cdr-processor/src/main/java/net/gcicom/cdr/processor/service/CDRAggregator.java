package net.gcicom.cdr.processor.service;

import java.util.ArrayList;

import org.apache.camel.Exchange;
import org.apache.camel.dataformat.bindy.BindyCsvFactory;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import net.gcicom.domain.imported.events.ImportedEvent;

/**
 * This class support {@link ImportedEvent} object aggregation while consuming 
 * {@link BindyCsvFactory} messages after mapping to {@link ImportedEvent} so that db insert can be done in batches

 *
 */
@Component
public class CDRAggregator implements AggregationStrategy  {

	private Logger logger = LoggerFactory.getLogger(CDRAggregator.class); 
	
	
	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

		ImportedEvent cdr = newExchange.getIn().getBody(ImportedEvent.class);
		logger.debug("Aggregating " + cdr);

		ArrayList<ImportedEvent> cdrs = null;
	
		if (oldExchange == null) {
			
			cdrs = new ArrayList<ImportedEvent>();
			cdrs.add(cdr);
			newExchange.getIn().setBody(cdrs);
			return newExchange;
			
		} else {
			
			cdrs = oldExchange.getIn().getBody(ArrayList.class);
			cdrs.add(cdr);
			return oldExchange;
		}
	}
	
}
