package com.ecommerce.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.api.dao.OrderStatusDao;
import com.ecommerce.api.model.OrderStatus;
import com.ecommerce.api.service.OrderStatusService;

@Service
public class OrderStatusServiceImpl implements OrderStatusService{

	@Autowired
	private OrderStatusDao orderStatusDao;
	
	@Override
	public List<OrderStatus> getOrderStatuses() {
		return orderStatusDao.getOrderStatuses();
	}

	@Override
	public OrderStatus getOrderStatus(Integer id) {
		return orderStatusDao.getOrderStatus(id);
	}

	@Override
	public Boolean add(OrderStatus orderStatus) {
		return orderStatusDao.add(orderStatus);
	}

	@Override
	public Boolean update(OrderStatus orderStatus) {
		return orderStatusDao.update(orderStatus);
	}

	@Override
	public Boolean delete(Integer id) {
		return orderStatusDao.delete(id);
	}

	@Override
	public Boolean exists(Integer id) {
		return orderStatusDao.exists(id);
	}

}
