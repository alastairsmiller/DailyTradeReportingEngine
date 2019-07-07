package com.jpm.dailytrade;

import java.util.Set;

import org.junit.Test;

import com.jpm.dailytrade.data.IOrderDataLoader;
import com.jpm.dailytrade.model.Order;
import com.jpm.dailytrade.reports.ReportGenerator;

public class AppTest extends DailyTradeTestBase {

	@Test
	/**
	 * Test to run throw the system which proves nothing except that the bean wiring
	 * is intact Does also check for out output at the end
	 * 
	 * @throws Exception
	 */
	public void testReportWithTestData() throws Exception {

		IOrderDataLoader orderDataLoader = (IOrderDataLoader) appContext.getBean("orderDataLoader");
		Set<Order> orderData = orderDataLoader.getOrderData();

		ReportGenerator reportGenerator = (ReportGenerator) appContext.getBean("reportGenerator");
		String theReport = reportGenerator.generateOrderReport(orderData);

		assertTrue(theReport.contains("Incoming Daily Ranking"));
		assertTrue(theReport.contains("Outgoing Daily Ranking"));
		assertTrue(theReport.contains("Incoming Daily Amount"));
		assertTrue(theReport.contains("Outgoing Daily Ranking"));

		// Print this to StdOutput for now
		System.out.println(theReport);
	}
}
