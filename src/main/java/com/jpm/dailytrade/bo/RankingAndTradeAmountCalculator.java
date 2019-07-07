package com.jpm.dailytrade.bo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.jpm.dailytrade.model.Order;
import com.jpm.dailytrade.model.OrderSide;

/**
 * Class to work out TradeAmounts and Ranking. These are statics which is always
 * debatable
 */
public class RankingAndTradeAmountCalculator {

	/**
	 * Method to calculate the daily outgoing trade amount in USD
	 */
	public static Map<LocalDate, BigDecimal> calculateDailyOutgoingTradeAmount(Set<Order> orders) {
		Predicate<Order> orderSidePredicate = Order -> Order.getAction().equals(OrderSide.BUY);
		return calculateTradeAmountPerDay(orders, orderSidePredicate);
	}

	/**
	 * Method to calculate the daily incoming trade amount in USD
	 */
	public static Map<LocalDate, BigDecimal> calculateDailyIncomingTradeAmount(Set<Order> orders) {
		Predicate<Order> orderSidePredicate = Order -> Order.getAction().equals(OrderSide.SELL);
		return calculateTradeAmountPerDay(orders, orderSidePredicate);
	}

	/**
	 * Method to calculate the daily outgoing Entity Ranking by trade amount in USD
	 */
	public static List<Ranking> calculateOutgoingEntityRanking(Set<Order> orders) {
		Predicate<Order> orderSidePredicate = Order -> Order.getAction().equals(OrderSide.BUY);
		return calculateEntityRanking(orders, orderSidePredicate);
	}

	/**
	 * Method to calculate the daily incoming Entity Ranking by trade amount in USD
	 */
	public static List<Ranking> calculateIncomingEntityRanking(Set<Order> orders) {
		Predicate<Order> orderSidePredicate = Order -> Order.getAction().equals(OrderSide.SELL);
		return calculateEntityRanking(orders, orderSidePredicate);
	}

	/**
	 * Private Helper to calculate the Rankings
	 */
	private static List<Ranking> calculateEntityRanking(Set<Order> orders, Predicate<Order> predicate) {
		Stream<Order> outGoingStreamedOrder = orders.stream().filter(predicate);

		Map<String, BigDecimal> entityToTradeAmount = outGoingStreamedOrder.collect(Collectors.groupingBy(
				Order::getEntity,
				Collectors.mapping(Order::getTradeAmount, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

		List<Ranking> result = entityToTradeAmount.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue(BigDecimal::compareTo)))
				.map(e -> new Ranking(e.getKey(), e.getValue())).collect(Collectors.toList());

		return (result);
	}

	/**
	 * Private Helper to calculate the Trade Amount
	 */
	private static Map<LocalDate, BigDecimal> calculateTradeAmountPerDay(Set<Order> orders,
			Predicate<Order> predicate) {
		SettlementDateCalculator.calculateSettlementDates(orders);
		Stream<Order> outGoingStreamedOrder = orders.stream().filter(predicate);

		Map<LocalDate, BigDecimal> dateToTradeAmount = outGoingStreamedOrder.collect(Collectors.groupingBy(
				Order::getSettlementDate,
				Collectors.mapping(Order::getTradeAmount, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

		return (dateToTradeAmount);
	}

}
