package com.ecommerce.api.dao;

import java.util.List;

import com.ecommerce.api.model.Order;

public interface OrderDao {
	List<Order> getOrders();
	Order getOrder(Integer id);
	Boolean add(Order order);
	Boolean update(Order order);
	Boolean delete(Integer id);

	Boolean exists(Integer id);
}
