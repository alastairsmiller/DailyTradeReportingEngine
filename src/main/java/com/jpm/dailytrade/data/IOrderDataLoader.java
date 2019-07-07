package com.jpm.dailytrade.data;

import java.util.Set;

import com.jpm.dailytrade.model.Order;

public interface IOrderDataLoader {

	public Set<Order> getOrderSmallData();

	public Set<Order> getOrderData();

}
