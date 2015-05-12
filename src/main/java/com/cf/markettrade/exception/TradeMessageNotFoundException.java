package com.cf.markettrade.exception;

import com.cf.markettrade.domain.TradeMessage;

/**
 * This exception is thrown when an {@link TradeMessage} is not found.
 * 
 * @author pangio
 */
public class TradeMessageNotFoundException extends RuntimeException {

	/**
	 * Unique ID for Serialized object
	 */
	private static final long serialVersionUID = -8643399267345293905L;

	/**
	 * Constructor of the exception
	 * 
	 * @param id of the {@link TradeMessage}
	 */
	public TradeMessageNotFoundException(String tradeMessageId) {
		super("The TradeMessage with id " + tradeMessageId + " doesn't exist");
	}
}
