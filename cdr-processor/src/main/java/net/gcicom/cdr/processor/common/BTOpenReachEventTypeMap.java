package net.gcicom.cdr.processor.common;

import java.util.HashMap;
import java.util.Map;

/**
 * supplier even type mapping to GCI event type. For details check RatingDB.EventType table
 *
 */
public final class BTOpenReachEventTypeMap {

	public static final Map<Integer, Long> etm = new HashMap<>();
	
	static {		
	
		etm.put(310, new Long(502));
		etm.put(326, new Long(514));
		etm.put(350, new Long(502));
		etm.put(351, new Long(502));
		etm.put(352, new Long(502));
		etm.put(353, new Long(502));
		etm.put(354, new Long(514));
		etm.put(355, new Long(514));
		
	}
	
}
