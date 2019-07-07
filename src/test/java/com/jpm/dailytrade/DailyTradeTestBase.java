package com.jpm.dailytrade;

import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jpm.dailytrade.data.IOrderDataLoader;
import com.jpm.dailytrade.model.Order;
import com.jpm.dailytrade.reports.ReportGenerator;

import junit.framework.TestCase;

/**
 * Base for all Tests. It is *always* a debate about these
 */
public class DailyTradeTestBase extends TestCase {

	public static ApplicationContext appContext = new ClassPathXmlApplicationContext("testbeans.xml");

	@Override
	protected void setUp() throws Exception {
		System.out.println("Starting Test Run");
	}

	@Override
	protected void tearDown() throws Exception {
		System.out.println("Finished Test Run");
	}
	
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
