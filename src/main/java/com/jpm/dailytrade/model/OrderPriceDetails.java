package com.jpm.dailytrade.model;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Represents the price details of an order
 */
public class OrderPriceDetails {

	private final Currency currency;

	private final BigDecimal agreedFx;

	private final int units;

	private final BigDecimal pricePerUnit;

	public OrderPriceDetails(Currency currency, BigDecimal agreedFx, int units, BigDecimal pricePerUnit) {
		this.currency = currency;
		this.agreedFx = agreedFx;
		this.units = units;
		this.pricePerUnit = pricePerUnit;
	}

	public BigDecimal getAgreedFx() {
		return agreedFx;
	}

	public int getUnits() {
		return units;
	}

	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}

	public BigDecimal getTradeAmount() {
		return calculateTradeAmount(this);
	}

	public Currency getCurrency() {
		return currency;
	}

	/**
	 * Work out the trade amount from this Order Price Details
	 */
	private BigDecimal calculateTradeAmount(OrderPriceDetails orderPriceDetails) {
		return orderPriceDetails.getPricePerUnit().multiply(BigDecimal.valueOf(orderPriceDetails.getUnits()))
				.multiply(orderPriceDetails.getAgreedFx());
	}

}
