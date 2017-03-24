package net.gcicom.cdr.processor.service;

import java.util.ArrayList;

import org.apache.camel.Exchange;
import org.apache.camel.dataformat.bindy.BindyCsvFactory;
import org.apache.camel.processor.aggregate.CompletionAwareAggregationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import net.gcicom.cdr.processor.entity.output.GCIChargeImport;

/**
 * This class support {@link GCIChargeImport} object aggregation while consuming 
 * {@link BindyCsvFactory} messages after mapping to {@link GCIChargeImport} so that db insert can be done in batches

 *
 */
@Component
public class ChargeImportAggregator implements CompletionAwareAggregationStrategy  {

	private Logger logger = LoggerFactory.getLogger(ChargeImportAggregator.class); 
	
	
	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

		GCIChargeImport cdr = newExchange.getIn().getBody(GCIChargeImport.class);
		logger.debug("Aggregating " + cdr);

		ArrayList<GCIChargeImport> cdrs = null;
	
		if (oldExchange == null) {
			
			cdrs = new ArrayList<GCIChargeImport>();
			cdrs.add(cdr);
			newExchange.getIn().setBody(cdrs);
			return newExchange;
			
		} else {
			
			cdrs = oldExchange.getIn().getBody(ArrayList.class);
			cdrs.add(cdr);
			return oldExchange;
		}
	}
	
	@Override
	public void onCompletion(Exchange exchange) {

		logger.info("Aggregation completed but is split  completed?, " + exchange.getProperty(Exchange.SPLIT_COMPLETE, Boolean.class));
		//auditor.endEvent(exchange);
		
	}
}
