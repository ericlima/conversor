package cotacao;

import java.math.BigDecimal;

public class Quotation {

	private String currency;
	private BigDecimal buy;
	private BigDecimal sell;
	private BigDecimal parityBuy;
	private BigDecimal paritySell;
	private char currencyType;

	public Quotation(String currency, char currencyType, BigDecimal buy, BigDecimal sell, BigDecimal parityBuy, BigDecimal paritySell) {
		this.currency = currency;
		this.buy = buy;
		this.sell = sell;
		this.parityBuy = parityBuy;
		this.paritySell = paritySell;
		this.currencyType = currencyType;
	}

	public char getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(char currencyType) {
		this.currencyType = currencyType;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getBuy() {
		return buy;
	}

	public void setBuy(BigDecimal buy) {
		this.buy = buy;
	}

	public BigDecimal getSell() {
		return sell;
	}

	public void setSell(BigDecimal sell) {
		this.sell = sell;
	}

	public BigDecimal getParityBuy() {
		return parityBuy;
	}

	public void setParityBuy(BigDecimal parityBuy) {
		this.parityBuy = parityBuy;
	}

	public BigDecimal getParitySell() {
		return paritySell;
	}

	public void setParitySell(BigDecimal paritySell) {
		this.paritySell = paritySell;
	}

}
