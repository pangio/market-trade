package com.cf.markettrade.controller;



import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.cf.markettrade.Application;
import com.cf.markettrade.domain.TradeMessage;
import com.cf.markettrade.repository.TradeMessageRepository;

/**
 * {@link TradeMessageControllerTest} Tests all the the {@link HttpMethod}s
 * under the URI '/trades'. Includes GET and POST http methods
 * 
 * @author pangio
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class TradeMessageControllerTest {

	private MediaType contentType = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	@SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	private List<TradeMessage> tradesList = new ArrayList<TradeMessage>();

	@Autowired
	private TradeMessageRepository repository;

	@Autowired
	private WebApplicationContext webApplicationContext;

	TradeMessageController asyncController;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays
				.asList(converters)
				.stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
				.findAny().get();

		Assert.assertNotNull("the JSON message converter must not be null",
				this.mappingJackson2HttpMessageConverter);
	}

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
		emptyRepositories();
		createTradeMessages();
	}

	@Test
	public void verifyCeateTradeMessage() throws Exception {
		TradeMessage trade = new TradeMessage();
		trade.setUserId("12345");
		trade.setOriginatingCountry("US");
		trade.setCurrencyFrom("EUR");
		trade.setCurrencyTo("USD");
		trade.setAmountBuy(new BigDecimal(20));
		trade.setAmountSell(new BigDecimal(200));
		trade.setRate(new BigDecimal(10));
		trade.setTimePlaced("14-JAN-15 10:27:44");

		String tradeJson = json(trade);
		
		this.mockMvc.perform(
				post("/trades").content(tradeJson).contentType(contentType))
				.andExpect(status().isCreated());
	}

	@Test
	public void verifyShouldNotDeleteTrade() throws Exception {
		mockMvc.perform(delete("/trades/799879899").contentType(contentType))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void verifyFindTradeById() throws Exception {

		TradeMessage trade = this.tradesList.get(0);
		mockMvc.perform(
				get("/trades/" + trade.getId()).contentType(contentType))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.id", is(trade.getId())))
				.andExpect(jsonPath("$.userId", is("12345")))
				.andExpect(jsonPath("$.currencyFrom", is("EUR")))
				.andExpect(jsonPath("$.currencyTo", is("USD")))
				.andExpect(jsonPath("$.amountBuy", comparesEqualTo(1000)))
				.andExpect(jsonPath("$.amountSell", comparesEqualTo(500)))
				.andExpect(jsonPath("$.rate", comparesEqualTo(2)));
	}

	@Test
	public void verifyFindAllTrades() throws Exception {
		mockMvc.perform(get("/trades").contentType(contentType))
				.andExpect(status().isOk())

				// first trade
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(this.tradesList.get(0).getId())))
				.andExpect(jsonPath("$[0].userId", is("12345")))
				.andExpect(jsonPath("$[0].currencyFrom", is("EUR")))
				.andExpect(jsonPath("$[0].currencyTo", is("USD")))
				.andExpect(jsonPath("$[0].amountBuy", comparesEqualTo(1000)))
				.andExpect(jsonPath("$[0].amountSell", comparesEqualTo(500)))
				.andExpect(jsonPath("$[0].rate", comparesEqualTo(2)))

				// second trade
				.andExpect(jsonPath("$[1].id", is(this.tradesList.get(1).getId())))
				.andExpect(jsonPath("$[1].userId", is("12346")))
				.andExpect(jsonPath("$[1].currencyFrom", is("USD")))
				.andExpect(jsonPath("$[1].currencyTo", is("EUR")))
				.andExpect(jsonPath("$[1].amountBuy", comparesEqualTo(20)))
				.andExpect(jsonPath("$[1].amountSell", comparesEqualTo(200)))
				.andExpect(jsonPath("$[1].rate", comparesEqualTo(10)));
	}

	@Test
	public void verifyTradeNotFound() throws Exception {
		mockMvc.perform(get("/trades/799879899"))
				.andExpect(status().isNotFound())
				.andExpect(content().contentType(contentType));
	}

	@SuppressWarnings("unchecked")
	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(o,
				MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

	private void createTradeMessages() {
		// first trade
		TradeMessage trade = new TradeMessage();
		trade.setCurrencyFrom("EUR");
		trade.setCurrencyTo("USD");
		trade.setUserId("12345");
		trade.setAmountBuy(new BigDecimal(1000));
		trade.setAmountSell(new BigDecimal(500));
		trade.setRate(new BigDecimal(2));
		this.repository.save(trade);
		this.tradesList.add(this.repository.save(trade));

		// second trade
		trade = new TradeMessage();
		trade.setCurrencyFrom("USD");
		trade.setCurrencyTo("EUR");
		trade.setUserId("12346");
		trade.setAmountBuy(new BigDecimal(20));
		trade.setAmountSell(new BigDecimal(200));
		trade.setRate(new BigDecimal(10));
		this.tradesList.add(this.repository.save(trade));
		this.repository.save(trade);
	}

	private void emptyRepositories() {
		this.repository.deleteAll();
	}

}
