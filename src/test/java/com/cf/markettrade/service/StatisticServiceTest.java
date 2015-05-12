package com.cf.markettrade.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.net.UnknownHostException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cf.markettrade.domain.TradeMessage;
import com.cf.markettrade.repository.TradeMessageRepository;

/**
 * {@link StatisticServiceTest} ensures the proper behavior of the
 * {@link StatisticService} and its methods used to build reports and/or charts.
 * 
 * @author pangio
 */
public class StatisticServiceTest {

	private TradeMessageRepository repositoryMock;
	private StatisticService service;

	@Before
	public void setUp() throws UnknownHostException {
		repositoryMock = mock(TradeMessageRepository.class);
		service = new StatisticService(repositoryMock);

		TradeMessage tradeFromFR = new TradeMessage();
		tradeFromFR.setOriginatingCountry("FR");
		tradeFromFR.setCurrencyFrom("EUR");
		tradeFromFR.setCurrencyTo("USD");

		TradeMessage tradeFromIE = new TradeMessage();
		tradeFromIE.setOriginatingCountry("IE");
		tradeFromIE.setCurrencyFrom("EUR");
		tradeFromIE.setCurrencyTo("AUD");

		TradeMessage tradeFromUS = new TradeMessage();
		tradeFromUS.setOriginatingCountry("US");
		tradeFromUS.setCurrencyFrom("USD");
		tradeFromUS.setCurrencyTo("AUD");
		
		when(service.countByCurrencyFrom("EUR")).thenReturn(2L);
		when(service.countByCurrencyFrom("USD")).thenReturn(1L);

		when(service.countByCurrencyTo("USD")).thenReturn(1L);
		when(service.countByCurrencyTo("AUD")).thenReturn(2L);

		when(service.countByOriginatingCountry("FR")).thenReturn(1L);
		when(service.countByOriginatingCountry("IE")).thenReturn(1L);

		when(service.count()).thenReturn(3L);
	}

	@Test
	public void verifyTradesFromIreland() {
		Assert.assertEquals(new Long(1), service.countByOriginatingCountry("IE"));
		verify(repositoryMock, times(1)).countByOriginatingCountry("IE");
		verifyNoMoreInteractions(repositoryMock);
	}

	@Test
	public void verifyTradesFromFrance() {
		Assert.assertEquals(new Long(1), service.countByOriginatingCountry("FR"));
		verify(repositoryMock, times(1)).countByOriginatingCountry("FR");
		verifyNoMoreInteractions(repositoryMock);
	}

	@Test
	public void verifyTradesSellingCurrencyEUR() {
		Assert.assertEquals(new Long(2), service.countByCurrencyFrom("EUR"));
		verify(repositoryMock, times(1)).countByCurrencyFrom("EUR");
		verifyNoMoreInteractions(repositoryMock);
	}

	@Test
	public void verifyTradesSellingCurrencyUSD() {
		Assert.assertEquals(new Long(1), service.countByCurrencyFrom("USD"));
		verify(repositoryMock, times(1)).countByCurrencyFrom("USD");
		verifyNoMoreInteractions(repositoryMock);
	}
	
	@Test
	public void verifyTradesBuyingCurrencyUSD() {
		Assert.assertEquals(new Long(1), service.countByCurrencyTo("USD"));
		verify(repositoryMock, times(1)).countByCurrencyTo("USD");
		verifyNoMoreInteractions(repositoryMock);
	}

	@Test
	public void verifyTradesBuyingCurrencyAUD() {
		Assert.assertEquals(new Long(2), service.countByCurrencyTo("AUD"));
		verify(repositoryMock, times(1)).countByCurrencyTo("AUD");
		verifyNoMoreInteractions(repositoryMock);
	}

	@Test
	public void verifyTotalTrades() {
		Assert.assertEquals(new Long(3), service.count());
		verify(repositoryMock, times(1)).count();
		verifyNoMoreInteractions(repositoryMock);
	}
	
}