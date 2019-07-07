package com.jpm.dailytrade.bo;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.jpm.dailytrade.DailyTradeTestBase;
import com.jpm.dailytrade.data.IOrderDataLoader;
import com.jpm.dailytrade.model.Order;


/**
 * This test would very normally check each and every element at least once to make sure the results were good
 */
public class RankingAndTradeAmountCalculatorTest extends DailyTradeTestBase {

	@Test
	public void testCalculateDailyOutgoingTradeAmount() throws Exception {

		IOrderDataLoader orderDataLoader = (IOrderDataLoader) appContext.getBean("orderDataLoader");
		Set<Order> orderData = orderDataLoader.getOrderData();

		Map<LocalDate, BigDecimal> result = RankingAndTradeAmountCalculator
				.calculateDailyOutgoingTradeAmount(orderData);
		// Check there are 4 entries here, should also check each entry
		assertEquals(4, result.size());

	}

	@Test
	public void testCalculateDailyIncomingTradeAmount() throws Exception {

		IOrderDataLoader orderDataLoader = (IOrderDataLoader) appContext.getBean("orderDataLoader");
		Set<Order> orderData = orderDataLoader.getOrderData();

		Map<LocalDate, BigDecimal> result = RankingAndTradeAmountCalculator
				.calculateDailyIncomingTradeAmount(orderData);

		assertEquals(4, result.size());

	}

	@Test
	public void testCalculateIncomingEntityRanking() throws Exception {

		IOrderDataLoader orderDataLoader = (IOrderDataLoader) appContext.getBean("orderDataLoader");
		Set<Order> orderData = orderDataLoader.getOrderData();

		List<Ranking> result = RankingAndTradeAmountCalculator.calculateIncomingEntityRanking(orderData);

		assertEquals(3, result.size());
	}

	@Test
	public void testCalculateOutgoingEntityRanking() throws Exception {

		IOrderDataLoader orderDataLoader = (IOrderDataLoader) appContext.getBean("orderDataLoader");
		Set<Order> orderData = orderDataLoader.getOrderData();

		List<Ranking> result = RankingAndTradeAmountCalculator.calculateOutgoingEntityRanking(orderData);

		assertEquals(4, result.size());
	}
}
