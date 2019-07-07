package com.jpm.dailytrade.bo;

import java.time.DayOfWeek;

/**
 * Holds the default Trading days, if you need other sets then extend more from
 * TradingDays
 *
 */
public class DefaultTradingDays extends TradingDays {

	private static DefaultTradingDays instance = null;

	private DefaultTradingDays() {
		super();
	};

	protected static TradingDays getInstance() {
		if (instance == null) {
			instance = new DefaultTradingDays();
		}
		return instance;
	}

	public boolean isTradingDay(DayOfWeek dayOfWeek) {
		return (tradingDayList.contains(dayOfWeek));
	}

	@Override
	protected void setupTradingDays() {
		this.tradingDayList.add(DayOfWeek.MONDAY);
		this.tradingDayList.add(DayOfWeek.MONDAY);
		this.tradingDayList.add(DayOfWeek.TUESDAY);
		this.tradingDayList.add(DayOfWeek.WEDNESDAY);
		this.tradingDayList.add(DayOfWeek.THURSDAY);
		this.tradingDayList.add(DayOfWeek.FRIDAY);
	}
}
