package net.gcicom.cdr.processor.service;

public class InvalidCDRException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 366510628674121593L;
	
	public InvalidCDRException(String msg) {
		
		super(msg);
		
	}
	
	public InvalidCDRException(Throwable e, String msg) {
		
		super(msg, e);
		
	}


}
