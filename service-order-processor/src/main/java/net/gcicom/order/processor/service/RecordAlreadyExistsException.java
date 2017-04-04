package net.gcicom.order.processor.service;



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
}
