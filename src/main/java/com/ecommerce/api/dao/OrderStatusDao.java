package com.ecommerce.api.dao;

import java.util.List;

import com.ecommerce.api.model.OrderStatus;

public interface OrderStatusDao {
	List<OrderStatus> getOrderStatuses();
	OrderStatus getOrderStatus(Integer id);
	Boolean add(OrderStatus orderStatus);
	Boolean update(OrderStatus orderStatus);
	Boolean delete(Integer id);
	Boolean exists(Integer id);
}
