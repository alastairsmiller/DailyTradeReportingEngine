package com.jpm.dailytrade.model;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import com.jpm.dailytrade.DailyTradeTestBase;
import com.jpm.dailytrade.data.IOrderDataLoader;

public class TradeAmountTest extends DailyTradeTestBase {

	@Test
	public void testTradeAmountCalc() throws Exception {

		Predicate<Order> outgoingOrdersPredicate = Order -> Order.getAction().equals(OrderSide.BUY);

		IOrderDataLoader orderDataLoader = (IOrderDataLoader) appContext.getBean("orderDataLoader");
		Set<Order> orderData = orderDataLoader.getOrderData();

		// Get a collection of all "predicate orders"
		List<Order> outGoingSortedOrder = orderData.stream().filter(outgoingOrdersPredicate)
				.sorted((o1, o2) -> o1.getTradeAmount().compareTo(o2.getTradeAmount())).collect(Collectors.toList());

		outGoingSortedOrder.forEach(System.out::println);

		outGoingSortedOrder = orderData.stream().filter(outgoingOrdersPredicate)
				.sorted(Comparator.comparing(Order::getTradeAmount)).collect(Collectors.toList());
		outGoingSortedOrder.forEach(System.out::println);

		Stream<Order> outGoingStreamedOrder = orderData.stream().filter(outgoingOrdersPredicate);

		Map<String, BigDecimal> entityToTradeAmount = outGoingStreamedOrder.collect(Collectors.groupingBy(
				Order::getEntity,
				Collectors.mapping(Order::getTradeAmount, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
		BigDecimal ubsOrderTradeAmount = entityToTradeAmount.get("UBS");
		
		Assert.assertEquals("Order data Must be 4", 4, entityToTradeAmount.size());
		Assert.assertNotNull("Trade Amount from UBS order sghould not be null", ubsOrderTradeAmount);
		Assert.assertEquals("Trade Amount is incorrect", BigDecimal.valueOf(4828).setScale(2), ubsOrderTradeAmount);

	}

}