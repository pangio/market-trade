package com.cf.markettrade.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cf.markettrade.domain.TradeMessage;
import com.cf.markettrade.exception.TradeMessageNotFoundException;

/**
 * Persistence Layer. {@link TradeMessageRepository} is where all
 * {@link TradeMessage}s will be stored. Also see {@link MongoRepository}
 * 
 * @author pangio
 */
public interface TradeMessageRepository extends MongoRepository<TradeMessage, String> {

	/**
	 * Finds a {@link TradeMessage} by id
	 * 
	 * @param id
	 *            of the {@link TradeMessage}
	 * @return the {@link TradeMessage}
	 */
	TradeMessage findById(String id) throws TradeMessageNotFoundException;

	/**
	 * Returns the quantity of {@link TradeMessage}s found by originatingCountry
	 * 
	 * @param originatingCountry
	 * @return the {@link TradeMessage} quantity
	 */
	Long countByOriginatingCountry(String originatingCountry);

	/**
	 * Returns the quantity of {@link TradeMessage}s found by currencyFrom
	 * 
	 * @param currencyFrom
	 * @return the {@link TradeMessage} quantity
	 */
	Long countByCurrencyFrom(String currencyFrom);

	/**
	 * Returns the quantity of {@link TradeMessage}s found by currencyTo
	 * 
	 * @param currencyTo
	 * @return the {@link TradeMessage} quantity
	 */
	Long countByCurrencyTo(String currencyTo);

	/**
	 * Returns the total quantity of {@link TradeMessage}s
	 * 
	 * @return total {@link TradeMessage}s
	 */
	long count();

}
