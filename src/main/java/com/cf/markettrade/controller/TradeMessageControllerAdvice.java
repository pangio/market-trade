package com.cf.markettrade.controller;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cf.markettrade.exception.TradeMessageNotFoundException;

/**
 * Exception handler for the {@link TradeMessageController}.
 * 
 * @author pangio
 */
@ControllerAdvice
public class TradeMessageControllerAdvice {

	/**
	 * Exception handler of {@link TradeMessageException}
	 */
	@ResponseBody
	@ExceptionHandler(TradeMessageNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	VndErrors tradeMessageExceptionHandler(TradeMessageNotFoundException ex) {
		return new VndErrors("error", ex.getMessage());
	}
}
