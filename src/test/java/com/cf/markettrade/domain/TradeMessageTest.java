package com.cf.markettrade.domain;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

public class TradeMessageTest {

	TradeMessage trade;

	@Before
	public void setup() throws ParseException {
		trade = new TradeMessage();
		trade.setUserId("123456");
		trade.setCurrencyFrom("EUR");
		trade.setCurrencyTo("GBP");
		trade.setAmountSell(new BigDecimal(88.5));
		trade.setAmountBuy(new BigDecimal(77.3));
		trade.setRate(new BigDecimal(55.1));
		trade.setOriginatingCountry("FR");
		trade.setTimePlaced("14-JAN-15 10:27:44");
	}

	@Test
	public void verifyCreateTradeMessage() {
		assertEquals("123456", trade.getUserId());
		assertEquals("EUR", trade.getCurrencyFrom());
		assertEquals("GBP", trade.getCurrencyTo());
		assertEquals(new BigDecimal(88.5), trade.getAmountSell());
		assertEquals(new BigDecimal(77.3), trade.getAmountBuy());
		assertEquals(new BigDecimal(55.1), trade.getRate());
		assertEquals("FR", trade.getOriginatingCountry());
		assertEquals("14-JAN-15 10:27:44", trade.getTimePlaced());
	}
}
