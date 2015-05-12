package com.cf.markettrade.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.cf.markettrade.domain.Statistic;
import com.cf.markettrade.domain.TradeMessage;
import com.cf.markettrade.repository.TradeMessageRepository;

/**
 * Service Layer. {@link StatisticService} provides an interface to get
 * Statistics for {@link TradeMessage}. Used to build charts/reports.
 * 
 * @author pangio
 */
@Component
public class StatisticService {

    private static final Logger logger = Logger.getLogger(StatisticService.class);

    @Autowired
	TradeMessageRepository repository;

    @Autowired
	private SimpMessagingTemplate template;

	/**
	 * Default constructor
	 */
	public StatisticService() {
	}

	public StatisticService(TradeMessageRepository repository) {
		this.repository = repository;
	}

	/**
	 * Returns the total quantity of {@link TradeMessage}s
	 * 
	 * @return total {@link TradeMessage}s
	 */
	public Long count() {
		return this.repository.count();
	}

	/**
	 * Returns the quantity of {@link TradeMessage}s found by originatingCountry
	 * 
	 * @param originatingCountry
	 * @return the {@link TradeMessage} quantity
	 */
	public Long countByOriginatingCountry(String originatingCountry) {
		return this.repository.countByOriginatingCountry(originatingCountry);
	}

	/**
	 * Returns the quantity of {@link TradeMessage}s found by currencyFrom
	 * 
	 * @param currencyFrom
	 * @return the {@link TradeMessage} quantity
	 */
	public Long countByCurrencyFrom(String currencyFrom) {
		return this.repository.countByCurrencyFrom(currencyFrom);
	}

	/**
	 * Returns the quantity of {@link TradeMessage}s found by currencyTo
	 * 
	 * @param currencyTo
	 * @return the {@link TradeMessage} quantity
	 */
	public Long countByCurrencyTo(String currencyTo) {
		return this.repository.countByCurrencyTo(currencyTo);
	}
	
	/**
	 * Generate {@link Statistic} required to build Charts
	 * 
	 * @return the stats
	 */
	public Statistic generate() {
		logger.info("Generating Statistics...");
		Statistic stats = new Statistic();
		stats.setAudBuy(countByCurrencyTo("AUD"));
		stats.setAudSell(countByCurrencyFrom("AUD"));
		stats.setUsdBuy(countByCurrencyTo("USD"));
		stats.setUsdSell(countByCurrencyFrom("USD"));
		stats.setEurBuy(countByCurrencyTo("EUR"));
		stats.setEurSell(countByCurrencyFrom("EUR"));
		return stats;
	}
	
	/**
	 * Generate {@link Statistic} required to build Charts and 
	 * send them to the front-end via Websockets.
	 */
	public void generateAndSendStats() {
		Statistic stats = this.generate();
		template.convertAndSend("/topic/charts", stats);
	}			
}
