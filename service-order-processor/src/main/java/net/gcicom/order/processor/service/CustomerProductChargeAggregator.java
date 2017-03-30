package net.gcicom.order.processor.service;

import java.util.ArrayList;

import org.apache.camel.Exchange;
import org.apache.camel.dataformat.bindy.BindyCsvFactory;
import org.apache.camel.processor.aggregate.CompletionAwareAggregationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import net.gcicom.domain.allspark.BillingReference;
import net.gcicom.domain.allspark.CustomerProductCharge;
//import net.gcicom.order.processor.entity.output.GCIChargeImport;

/**
 * This class support {@link GCIChargeImport} object aggregation while consuming 
 * {@link BindyCsvFactory} messages after mapping to {@link GCIChargeImport} so that db insert can be done in batches

 *
 */
@Component
public class CustomerProductChargeAggregator implements CompletionAwareAggregationStrategy  {

	private Logger logger = LoggerFactory.getLogger(CustomerProductChargeAggregator.class); 
	
	
	
	
	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

		CustomerProductCharge cdr = newExchange.getIn().getBody(CustomerProductCharge.class);
		logger.debug("Aggregating " + cdr);

		ArrayList<CustomerProductCharge> cdrs = null;
	
		if (oldExchange == null) {
			
			cdrs = new ArrayList<CustomerProductCharge>();
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
