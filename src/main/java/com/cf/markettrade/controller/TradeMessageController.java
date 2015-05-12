package com.cf.markettrade.controller;

import java.util.List;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cf.markettrade.domain.Statistic;
import com.cf.markettrade.domain.TradeMessage;
import com.cf.markettrade.exception.TradeMessageNotFoundException;
import com.cf.markettrade.service.StatisticService;
import com.cf.markettrade.service.TradeMessageService;

/**
 * Controller of the {@link TradeMessage} entity. 
 * Handles all requests under the URI '/trades' See {@link HttpMethod}
 * 
 * @author pangio
 */
@RestController
@ComponentScan(basePackages = "com.cf.markettrade")
@RequestMapping(value = "/trades")
public class TradeMessageController {

    private static final Logger logger = Logger.getLogger(TradeMessageController.class);

    @Autowired
	private SimpMessagingTemplate template;

	@Autowired
	private TradeMessageService tradeMessageService;

	@Autowired
	private StatisticService statisticService;
		
	public TradeMessageController( TradeMessageService tradeMessageService, StatisticService statisticService) {
		this.tradeMessageService = tradeMessageService;
		this.statisticService = statisticService;
	}

	/**
	 * Default constructor
	 */
	public TradeMessageController() {
	}
	
	/**
	 * Async HTTP POST request. 
	 * See also {@link Callable}
	 * 
	 * @param JSON of the {@link TradeMessage}
	 * @return created {@link TradeMessage}
	 * @throws Exception
	 */	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Callable<TradeMessage> asyncPost(@RequestBody final TradeMessage message) throws Exception {
		
		logger.info("Processing Trade Message: " + message);
		return new Callable<TradeMessage>() {
			
			@Override
			public TradeMessage call() throws Exception {
				// saves into MongoDB
				TradeMessageController.this.tradeMessageService.create(message);
				// push Statistics to the front-end
				TradeMessageController.this.statisticService.generateAndSendStats();
				return message;
			}
		};
	}

	/**
	 * Retrieves a {@link TradeMessage}
	 * 
	 * @param tradeMessageId.
	 * @return the {@link TradeMessage}
	 */
	@RequestMapping(value = "/{tradeMessageId}", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public TradeMessage get(@PathVariable String tradeMessageId) throws TradeMessageNotFoundException{
		return tradeMessageService.findById(tradeMessageId);
	}

	/**
	 * Provides the list of all {@link TradeMessage}s
	 * 
	 * @return the list of all {@link TradeMessage}s
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public List<TradeMessage> list() {
		return tradeMessageService.list();
	}

	/**
	 * Provides Statistics of {@link TradeMessage}
	 * Used to send initial data to the client.
	 * 
	 * @return the stats
	 */
	@SubscribeMapping("/init")
	public Statistic init() {
		return statisticService.generate();				
	}
}
