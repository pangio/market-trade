package com.cf.markettrade.domain;

/**
 * Statistic for {@link TradeMessage}.
 * 
 * @author pangio
 */
public class Statistic {

	private Long eurSell;
	private Long eurBuy;
	private Long usdSell;
	private Long usdBuy;
	private Long audSell;
	private Long audBuy;
	private Long totalTrades;

	/**
	 * Default constructor
	 */
	public Statistic() {
	}

	public Long getEurSell() {
		return eurSell;
	}

	public void setEurSell(Long eurSell) {
		this.eurSell = eurSell;
	}

	public Long getEurBuy() {
		return eurBuy;
	}

	public void setEurBuy(Long eurBuy) {
		this.eurBuy = eurBuy;
	}

	public Long getUsdSell() {
		return usdSell;
	}

	public void setUsdSell(Long usdSell) {
		this.usdSell = usdSell;
	}

	public Long getUsdBuy() {
		return usdBuy;
	}

	public void setUsdBuy(Long usdBuy) {
		this.usdBuy = usdBuy;
	}

	public Long getAudSell() {
		return audSell;
	}

	public void setAudSell(Long audSell) {
		this.audSell = audSell;
	}

	public Long getAudBuy() {
		return audBuy;
	}

	public void setAudBuy(Long audBuy) {
		this.audBuy = audBuy;
	}

	public Long getTotalTrades() {
		return totalTrades;
	}

	public void setTotalTrades(Long totalTrades) {
		this.totalTrades = totalTrades;
	}
}
