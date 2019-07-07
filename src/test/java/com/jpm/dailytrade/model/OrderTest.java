package com.jpm.dailytrade.model;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.jpm.dailytrade.DailyTradeTestBase;
import com.jpm.dailytrade.data.IOrderDataLoader;

public class OrderTest extends DailyTradeTestBase {

	@Test
	public void testTradeAmountCalc() throws Exception {

		IOrderDataLoader orderDataLoader = (IOrderDataLoader) appContext.getBean("orderDataLoader");
		Set<Order> orderData = orderDataLoader.getOrderSmallData();

		Assert.assertEquals("Order data Must be 2", 2, orderData.size());

		Optional<Order> optionalOrder = orderData.stream().filter(o -> o.getEntity().equals("foo")).findFirst();

		Assert.assertEquals("Optional Order should be from Entity foo", true, optionalOrder.isPresent());

		Order testOrder = optionalOrder.get();
		Assert.assertEquals("This should be Entity foo order", "foo", testOrder.getEntity());

		Assert.assertEquals("Trade Amount is incorrect", BigDecimal.valueOf(10025.00).setScale(2), testOrder.getTradeAmount());
	}
}