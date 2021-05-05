package com.ecommerce.api.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ecommerce.api.dao.OrderStatusDao;
import com.ecommerce.api.model.OrderStatus;

@Repository
@Transactional
public class OrderStatusDaoImpl implements OrderStatusDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<OrderStatus> getOrderStatuses() {
		try {
			List<OrderStatus> orderStatuss = new ArrayList<OrderStatus>();
			orderStatuss = sessionFactory.getCurrentSession().createQuery("from OrderStatus").list();
			return orderStatuss;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

	@Override
	public OrderStatus getOrderStatus(Integer id) {
		try {
			 OrderStatus orderStatus = sessionFactory.getCurrentSession().get(OrderStatus.class, id);
			return orderStatus;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

	@Override
	public Boolean add(OrderStatus orderStatus) {
		try {
			sessionFactory.getCurrentSession().save(orderStatus);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

	@Override
	public Boolean update(OrderStatus orderStatus) {
		try {
			sessionFactory.getCurrentSession().update(orderStatus);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

	@Override
	public Boolean delete(Integer id) {
		try {
			OrderStatus orderStatus = getOrderStatus(id);
			sessionFactory.getCurrentSession().delete(orderStatus);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

	@Override
	public Boolean exists(Integer id) {
		try {
			return sessionFactory.getCurrentSession().get(OrderStatus.class, id) != null;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

}
