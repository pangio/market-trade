package com.cf.markettrade.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.cf.markettrade.domain.TradeMessage;
import com.cf.markettrade.exception.TradeMessageNotFoundException;
import com.cf.markettrade.repository.TradeMessageRepository;

/**
 * {@link TradeMessageServiceTest} ensures the proper behavior of the
 * {@link TradeMessageService} and its methods.
 * 
 * @author pangio
 */
public class TradeMessageServiceTest {

	private TradeMessageRepository repositoryMock;
	private TradeMessageService service;

	@Before
	public void setUp() {
		repositoryMock = mock(TradeMessageRepository.class);
		service = new TradeMessageService(repositoryMock);
	}

	/**
	 * Verifies that a new {@link TradeMessage} was created and saved
	 */
	@Test
	public void verifyCreateAndSaveOneTrade() {

		when(repositoryMock.save(any(TradeMessage.class))).thenReturn(new TradeMessage());
		service.create(new TradeMessage());

		// verify that the TM repository has performed a save action just once
		verify(repositoryMock, times(1)).save(any(TradeMessage.class));
		verifyNoMoreInteractions(repositoryMock);

	}
	/**
	 * Verifies that two new {@link TradeMessage}s were created and saved
	 */
	@Test
	public void verifyCreateAndSaveManyTrades() {

		when(repositoryMock.save(any(TradeMessage.class))).thenReturn(new TradeMessage());

		// Create 4 trades
		service.create(new TradeMessage());
		service.create(new TradeMessage());
		service.create(new TradeMessage());
		service.create(new TradeMessage());

		// verify that the TM repository performed the save action 4 times 
		verify(repositoryMock, times(4)).save(any(TradeMessage.class));
		verifyNoMoreInteractions(repositoryMock);
	}
	
	/**
	 * Verifies that a {@link TradeMessage} is found by id
	 */
	@Test
	public void verifyFindTradeById() {

		when(repositoryMock.findById("123456789")).thenReturn(new TradeMessage());

		service.findById("123456789");

		// verify that the TM repository performed the findById with argument '123456789'
		verify(repositoryMock, times(1)).findById("123456789");
		verifyNoMoreInteractions(repositoryMock);
	}

	/**
	 * Verifies that the method findById throws {@link TradeMessageNotFoundException}
	 */
	@Test(expected = TradeMessageNotFoundException.class) 
	public void verifyTradeNotFound() {
		service.findById("123");
		// verify that the TM repository performed the findById with argument '123'
		verify(repositoryMock, times(1)).findById("123");
		verifyNoMoreInteractions(repositoryMock);
	}

}