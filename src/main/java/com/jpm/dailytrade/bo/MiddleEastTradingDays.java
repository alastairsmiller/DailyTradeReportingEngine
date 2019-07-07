package com.jpm.dailytrade.bo;

import java.time.DayOfWeek;

/**
 * Holds the a specialisation of Trading days to hold the middle east trading
 * days Consider renaming this when we add more markets
 *
 */
public class MiddleEastTradingDays extends TradingDays {

	private static MiddleEastTradingDays instance = null;

	private MiddleEastTradingDays() {
		super();
	};

	protected static TradingDays getInstance() {
		if (instance == null) {
			instance = new MiddleEastTradingDays();
		}
		return instance;
	}

	@Override
	public boolean isTradingDay(DayOfWeek dayOfWeek) {
		return (tradingDayList.contains(dayOfWeek));
	}

	@Override
	protected void setupTradingDays() {
		this.tradingDayList.add(DayOfWeek.SUNDAY);
		this.tradingDayList.add(DayOfWeek.MONDAY);
		this.tradingDayList.add(DayOfWeek.MONDAY);
		this.tradingDayList.add(DayOfWeek.TUESDAY);
		this.tradingDayList.add(DayOfWeek.WEDNESDAY);
		this.tradingDayList.add(DayOfWeek.THURSDAY);
	}

}
