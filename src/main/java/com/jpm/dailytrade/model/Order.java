package com.jpm.dailytrade.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Currency;

/**
 * Describes an order sent by various clients (entities) in order to buy or sell
 */
public class Order {

	private final String entity;

	private final OrderSide action;

	private final LocalDate OrderDate;

	private LocalDate settlementDate;

	private final OrderPriceDetails details;

	public Order(String entity, OrderSide action, LocalDate OrderDate, LocalDate settlementDate,
			OrderPriceDetails details) {
		this.entity = entity;
		this.action = action;
		this.OrderDate = OrderDate;
		this.settlementDate = settlementDate;
		this.details = details;
	}

	public String getEntity() {
		return entity;
	}

	public OrderSide getAction() {
		return action;
	}

	public LocalDate getOrderDate() {
		return OrderDate;
	}

	public void setSettlementDate(LocalDate newDate) {
		settlementDate = newDate;
	}

	public LocalDate getSettlementDate() {
		return settlementDate;
	}

	public OrderPriceDetails getDetails() {
		return details;
	}

	public Currency getCurrency() {
		return getDetails().getCurrency();
	}

	public BigDecimal getAgreedFx() {
		return getDetails().getAgreedFx();
	}

	public int getUnits() {
		return getDetails().getUnits();
	}

	public BigDecimal getPricePerUnit() {
		return getDetails().getPricePerUnit();
	}

	public BigDecimal getTradeAmount() {
		return getDetails().getTradeAmount().setScale(2, RoundingMode.HALF_EVEN);
	}

	@Override
	public String toString() {
		return entity + " : " + getTradeAmount();
	}
}
