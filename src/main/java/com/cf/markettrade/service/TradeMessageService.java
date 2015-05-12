package com.cf.markettrade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cf.markettrade.domain.TradeMessage;
import com.cf.markettrade.exception.TradeMessageNotFoundException;
import com.cf.markettrade.repository.TradeMessageRepository;

/**
 * Service Layer. {@link TradeMessageService} provides an interface to do CRUD
 * operations with {@link TradeMessage}
 * 
 * @author pangio
 */
@Component
public class TradeMessageService {

	@Autowired
	TradeMessageRepository repository;

	/**
	 * Default constructor
	 */
	public TradeMessageService() {
	}

	public TradeMessageService(TradeMessageRepository repository) {
		this.repository = repository;
	}

	/**
	 * Creates a new {@link TradeMessage} and saves it into Mongodb
	 * 
	 * @param trade
	 */
	public TradeMessage create(TradeMessage trade) {
		this.repository.save(trade);
		return trade;
	}

	/**
	 * Finds the list of all {@link TradeMessage}
	 * 
	 * @return the {@link TradeMessage} list
	 */
	public List<TradeMessage> list() {
		return this.repository.findAll();
	}

	/**
	 * Finds a {@link TradeMessage} by id
	 * 
	 * @param id
	 *            of the {@link TradeMessage}
	 * @return the {@link trade}
	 */
	public TradeMessage findById(String id) throws TradeMessageNotFoundException{
		TradeMessage tradeMessage = this.repository.findById(id);
		if (tradeMessage == null) throw new TradeMessageNotFoundException(id);
		return tradeMessage;
	}
}
