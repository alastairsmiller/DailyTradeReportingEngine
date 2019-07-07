package com.jpm.dailytrade.reports;

import java.util.Set;

import org.junit.Test;

import com.jpm.dailytrade.DailyTradeTestBase;
import com.jpm.dailytrade.data.IOrderDataLoader;
import com.jpm.dailytrade.model.Order;

public class ReportGeneratorTest extends DailyTradeTestBase {

	@Test
	public void testReportGeneration() throws Exception {

		ReportGenerator rp = new ReportGenerator();

		IOrderDataLoader orderDataLoader = (IOrderDataLoader) appContext.getBean("orderDataLoader");
		Set<Order> orderData = orderDataLoader.getOrderData();

		String theReport = rp.generateOrderReport(orderData);

		System.out.println(theReport);

	}
}