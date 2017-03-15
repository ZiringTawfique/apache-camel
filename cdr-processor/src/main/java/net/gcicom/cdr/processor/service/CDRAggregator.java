package net.gcicom.cdr.processor.service;

import java.util.ArrayList;

import org.apache.camel.Exchange;
import org.apache.camel.dataformat.bindy.BindyCsvFactory;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import net.gcicom.cdr.processor.entity.output.GCICDR;

/**
 * This class support {@link GCICDR} object aggregation while consuming 
 * {@link BindyCsvFactory} messages after mapping to {@link GCICDR} so that db insert can be done in batches

 *
 */
@Component
public class CDRAggregator implements AggregationStrategy  {

	private Logger logger = LoggerFactory.getLogger(CDRAggregator.class); 
	
	
	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

		GCICDR cdr = newExchange.getIn().getBody(GCICDR.class);
		logger.debug("Aggregating " + cdr);

		ArrayList<GCICDR> cdrs = null;
	
		if (oldExchange == null) {
			
			cdrs = new ArrayList<GCICDR>();
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
