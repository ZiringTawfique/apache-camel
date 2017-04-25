package net.gcicom.order.processor.exception;

import static net.gcicom.order.processor.common.AppConstants.TOTAL_RECORD_PROCESSED_COUNT;

import org.apache.camel.Exchange;

public class RecordAlreadyExistsException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 366510628674121593L;
	
	public RecordAlreadyExistsException(String msg) {
		super(msg);
		
	}

	public RecordAlreadyExistsException(Throwable e, String msg) {
		
		super(msg, e);
		
	}
	
	public RecordAlreadyExistsException(String msg,int loopCount,Exchange ex) {
		super(msg);
		ex.getIn().setHeader(TOTAL_RECORD_PROCESSED_COUNT, String.valueOf(loopCount));
		
	}
}
