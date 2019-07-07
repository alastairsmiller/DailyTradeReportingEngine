package com.jpm.dailytrade.data;

import java.util.HashSet;
import java.util.Set;

import com.jpm.dailytrade.model.Order;

public class OrderDataLoader implements IOrderDataLoader {

	public Set<Order> getOrderSmallData() {
		return (new HashSet<Order>());
	}

	public Set<Order> getOrderData() {
		// Use this class to abstract the test and real data so the system doesn't know.
		// This would be the live db load so presumable would go and get the records
		// from a DB or where ever
		return (new HashSet<Order>());
	}
}
