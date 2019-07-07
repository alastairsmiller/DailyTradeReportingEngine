package com.jpm.dailytrade.bo;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jpm.dailytrade.model.Order;

/**
 * A settlement date calculator
 */
public class SettlementDateCalculator {

	public static ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.xml");

	/**
	 * Method to calculate settlement date for every given Order in the set
	 */
	public static void calculateSettlementDates(Set<Order> order) {
		order.forEach(SettlementDateCalculator::calculateSettlementDate);
	}

	/**
	 * Private helper method to calculate the settlementDate for each order supplied
	 */
	private static void calculateSettlementDate(Order order) {

		TradingDaysHandler tradingDaysHandler = (TradingDaysHandler) appContext.getBean("tradingDaysHandler");

		final LocalDate newSettlementDate = tradingDaysHandler.findFirstTradingDate(order.getCurrency(),
				order.getSettlementDate());

		if (newSettlementDate != null) {
			order.setSettlementDate(newSettlementDate);
		}
	}
}
