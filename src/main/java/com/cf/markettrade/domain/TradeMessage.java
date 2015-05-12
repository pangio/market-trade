package com.cf.markettrade.domain;

import java.math.BigDecimal;
import java.text.ParseException;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a {@link TradeMessage}.
 * 
 * @author pangio
 */
@Document
public class TradeMessage {

	@Id
	private String id;
	private String userId;
	private String originatingCountry;
	private String currencyFrom;
	private String currencyTo;
	private BigDecimal amountBuy;
	private BigDecimal amountSell;
	private BigDecimal rate;
	private String timePlaced;

	/**
	 * {@link TradeMessage} default constructor
	 */
	public TradeMessage() {
	}

	public TradeMessage(String userId, String currencyFrom, String currencyTo,
			BigDecimal amountSell, BigDecimal amountBuy, BigDecimal rate,
			String originatingCountry, String timePlaced)
			throws ParseException {

		this.userId = userId;
		this.originatingCountry = originatingCountry;
		this.currencyFrom = currencyFrom;
		this.currencyTo = currencyTo;
		this.amountBuy = amountBuy;
		this.amountSell = amountSell;
		this.rate = rate;
		this.timePlaced = timePlaced;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCurrencyFrom() {
		return currencyFrom;
	}

	public void setCurrencyFrom(String currencyFrom) {
		this.currencyFrom = currencyFrom;
	}

	public String getCurrencyTo() {
		return currencyTo;
	}

	public void setCurrencyTo(String currencyTo) {
		this.currencyTo = currencyTo;
	}

	public BigDecimal getAmountSell() {
		return amountSell;
	}

	public void setAmountSell(BigDecimal amountSell) {
		this.amountSell = amountSell;
	}

	public BigDecimal getAmountBuy() {
		return amountBuy;
	}

	public void setAmountBuy(BigDecimal amountBuy) {
		this.amountBuy = amountBuy;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public String getTimePlaced() {
		return timePlaced;
	}

	public void setTimePlaced(String timePlaced) {
		this.timePlaced = timePlaced;
	}

	public String getOriginatingCountry() {
		return originatingCountry;
	}

	public void setOriginatingCountry(String originatingCountry) {
		this.originatingCountry = originatingCountry;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
