package com.jpm.dailytrade.bo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Currency;

import org.junit.Test;

import com.jpm.dailytrade.DailyTradeTestBase;

public class TradingDaysHandlerTest extends DailyTradeTestBase {

	TradingDaysHandler tradingDaysHandler = (TradingDaysHandler) appContext.getBean("tradingDaysHandler");

	@Test
	public void testFindFirstWorkingDateForMiddleEast() {
		// Use a Friday and get this migrated to Sunday
		LocalDate tradingDay = LocalDate.of(2019, 7, 5);
		Currency ccy = Currency.getInstance("AED");
		LocalDate result = tradingDaysHandler.findFirstTradingDate(ccy, tradingDay);
		assertEquals(DayOfWeek.SUNDAY, result.getDayOfWeek());
		// Use a Sunday and it should stay as Sunday
		tradingDay = LocalDate.of(2019, 7, 7);
		result = tradingDaysHandler.findFirstTradingDate(ccy, tradingDay);
		assertEquals(DayOfWeek.SUNDAY, result.getDayOfWeek());
	}

	@Test
	public void testFindFirstWorkingDateForDefaultSet() {
		// Use a Saturday and get this migrated to Monday
		LocalDate tradingDay = LocalDate.of(2019, 7, 6);
		Currency ccy = Currency.getInstance("EUR");
		LocalDate result = tradingDaysHandler.findFirstTradingDate(ccy, tradingDay);
		assertEquals(DayOfWeek.MONDAY, result.getDayOfWeek());
		// Use a Friday and it should stay as a Friday
		tradingDay = LocalDate.of(2019, 7, 5);
		result = tradingDaysHandler.findFirstTradingDate(ccy, tradingDay);
		assertEquals(DayOfWeek.FRIDAY, result.getDayOfWeek());
	}

}