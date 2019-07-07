package com.jpm.dailytrade.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

import com.jpm.dailytrade.model.Order;
import com.jpm.dailytrade.model.OrderPriceDetails;
import com.jpm.dailytrade.model.OrderSide;

public class TestOrderDataLoader implements IOrderDataLoader {

	public Set<Order> getOrderSmallData() {
		return new HashSet<>(Arrays.asList(
				new Order("foo", OrderSide.BUY, LocalDate.of(2019, 7, 5), LocalDate.of(2019, 7, 6),
						new OrderPriceDetails(Currency.getInstance("SGD"), BigDecimal.valueOf(0.50), 200,
								BigDecimal.valueOf(100.25))),

				new Order("barr", OrderSide.SELL, LocalDate.of(2019, 7, 4), LocalDate.of(2019, 7, 7),
						new OrderPriceDetails(Currency.getInstance("AED"), BigDecimal.valueOf(0.22), 450,
								BigDecimal.valueOf(150.5)))

		));
	}

	public Set<Order> getOrderData() {
		return new HashSet<>(Arrays.asList(
				new Order("UBS", OrderSide.BUY, LocalDate.of(2019, 7, 7), LocalDate.of(2019, 7, 14),
						new OrderPriceDetails(Currency.getInstance("SGD"), BigDecimal.valueOf(0.50), 200,
								BigDecimal.valueOf(48.28))),
				new Order("CS", OrderSide.BUY, LocalDate.of(2019, 7, 7), LocalDate.of(2019, 7, 19),
						new OrderPriceDetails(Currency.getInstance("AED"), BigDecimal.valueOf(0.22), 450,
								BigDecimal.valueOf(120.00))),
				new Order("JPM", OrderSide.SELL, LocalDate.of(2019, 7, 7), LocalDate.of(2019, 7, 12),
						new OrderPriceDetails(Currency.getInstance("SAR"), BigDecimal.valueOf(0.35), 450,
								BigDecimal.valueOf(300.54))),
				new Order("MS", OrderSide.SELL, LocalDate.of(2019, 7, 7), LocalDate.of(2019, 7, 15),
						new OrderPriceDetails(Currency.getInstance("EUR"), BigDecimal.valueOf(0.44), 80,
								BigDecimal.valueOf(160.4))),
				new Order("RBS", OrderSide.BUY, LocalDate.of(2019, 7, 7), LocalDate.of(2019, 7, 14),
						new OrderPriceDetails(Currency.getInstance("EUR"), BigDecimal.valueOf(0.44), 90,
								BigDecimal.valueOf(20.6))),
				new Order("Lloyds", OrderSide.BUY, LocalDate.of(2019, 7, 7), LocalDate.of(2019, 7, 21),
						new OrderPriceDetails(Currency.getInstance("GBP"), BigDecimal.valueOf(0.21), 120,
								BigDecimal.valueOf(38.8))),
				new Order("Lloyds", OrderSide.BUY, LocalDate.of(2019, 7, 7), LocalDate.of(2019, 7, 11),
						new OrderPriceDetails(Currency.getInstance("EUR"), BigDecimal.valueOf(0.44), 170,
								BigDecimal.valueOf(89))),
				new Order("MS", OrderSide.SELL, LocalDate.of(2019, 7, 7), LocalDate.of(2019, 7, 11),
						new OrderPriceDetails(Currency.getInstance("GBP"), BigDecimal.valueOf(0.21), 5000,
								BigDecimal.valueOf(180.70))),
				new Order("UBS", OrderSide.SELL, LocalDate.of(2019, 7, 8), LocalDate.of(2019, 7, 12),
						new OrderPriceDetails(Currency.getInstance("EUR"), BigDecimal.valueOf(0.44), 10,
								BigDecimal.valueOf(600.55)))));
	}
}
