package com.jpm.dailytrade.bo;

import java.math.BigDecimal;

/**
 * This is purely a holder for the specific Entity to Ranking data
 */
public class Ranking {

	private String entity;
	private BigDecimal tradeAmount;

	public Ranking(String entity, BigDecimal tradeAmount) {
		super();
		this.entity = entity;
		this.tradeAmount = tradeAmount;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public BigDecimal getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(BigDecimal tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

}
