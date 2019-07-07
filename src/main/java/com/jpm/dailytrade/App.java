package com.jpm.dailytrade;

import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jpm.dailytrade.data.IOrderDataLoader;
import com.jpm.dailytrade.model.Order;
import com.jpm.dailytrade.reports.ReportGenerator;

/**
 * Main Class, gets the data, generates the report and the prints it out to
 * stdout
 *
 */
public class App {
	public static ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.xml");

	public static void main(String[] args) {
		IOrderDataLoader orderDataLoader = (IOrderDataLoader) appContext.getBean("orderDataLoader");
		Set<Order> orderData = orderDataLoader.getOrderData();

		ReportGenerator reportGenerator = (ReportGenerator) appContext.getBean("reportGenerator");
		String theReport = reportGenerator.generateOrderReport(orderData);

		// Print this to StdOutput for now
		System.out.println(theReport);
	}
}
