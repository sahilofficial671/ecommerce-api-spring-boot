package com.ecommerce.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.api.dao.OrderDao;
import com.ecommerce.api.model.Order;
import com.ecommerce.api.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDao orderDao;
	
	@Override
	public List<Order> getOrders() {
		return orderDao.getOrders();
	}

	@Override
	public Order getOrder(Integer id) {
		return orderDao.getOrder(id);
	}

	@Override
	public Boolean add(Order order) {
		return orderDao.add(order);
	}

	@Override
	public Boolean update(Order order) {
		return orderDao.update(order);
	}

	@Override
	public Boolean delete(Integer id) {
		return orderDao.delete(id);
	}

	@Override
	public Boolean exists(Integer id) {
		return orderDao.exists(id);
	}

}
