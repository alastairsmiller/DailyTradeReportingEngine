package com.jpm.dailytrade.bo;

import java.time.LocalDate;
import java.util.Currency;

public class TradingDaysHandler {

	/**
	 * Method to get the next trading date for this currency
	 */
	public LocalDate findFirstTradingDate(Currency ccy, LocalDate date) {
		TradingDays tradingDays = TradingDays.getSetOfTradingDays(ccy);
		if (tradingDays.isTradingDay(date.getDayOfWeek())) {
			return (date);
		} else {
			return findFirstTradingDateRec(tradingDays, date);
		}
	}

	/**
	 * Private helper to recursively loop until we get to the next trading day
	 */
	private LocalDate findFirstTradingDateRec(TradingDays tradingDays, LocalDate date) {
		if (tradingDays.isTradingDay(date.getDayOfWeek())) {
			return (date);
		} else {
			return findFirstTradingDateRec(tradingDays, date.plusDays((long) 1));
		}
	}
}
