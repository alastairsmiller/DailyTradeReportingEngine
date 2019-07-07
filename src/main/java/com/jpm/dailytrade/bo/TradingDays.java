package com.jpm.dailytrade.bo;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * TradingDays base. Extend this to add more sets if required
 */
public abstract class TradingDays {

	public static ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.xml");

	protected List<DayOfWeek> tradingDayList = new ArrayList<>();

	protected abstract void setupTradingDays();

	protected abstract boolean isTradingDay(DayOfWeek day);

	protected TradingDays() {
		setupTradingDays();
	}

	public List<DayOfWeek> getTradingDayList() {
		return tradingDayList;
	}

	/**
	 * Select specific Trading Days Set based on the Currency
	 * 
	 * @param currency the currency to choose
	 * @return the proper working days strategy
	 */
	public static TradingDays getSetOfTradingDays(Currency ccy) {
		TradingDays tradingDays = null;
		if ((ccy.getCurrencyCode().equals("AED")) || (ccy.getCurrencyCode().equals("SAR"))) {
			tradingDays = (TradingDays) appContext.getBean("middleEastTradingDays");
		} else {
			tradingDays = (TradingDays) appContext.getBean("defaultTradingDays");
		}
		return (tradingDays);
	}

}
