 	package com.ecommerce.api.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ecommerce.api.dao.OrderDao;
import com.ecommerce.api.model.Item;
import com.ecommerce.api.model.Order;
import com.ecommerce.api.model.Product;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Order> getOrders() {
		try {
			List<Order> orders = new ArrayList<Order>();
			orders = sessionFactory.getCurrentSession().createQuery("from Order").list();
			return orders;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

	@Override
	public Order getOrder(Integer id) {
		try {
			 Order order = sessionFactory.getCurrentSession().get(Order.class, id);
			return order;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

	@Override
	public Boolean add(Order order) {
		try {
			sessionFactory.getCurrentSession().save(order);
			
			// Update product quantity after order
			for(Item item : order.getItems()) {
				Product product = item.getProduct();
				product.setQuantity(product.getQuantity()-item.getQuantity());
				sessionFactory.getCurrentSession().update(product);
			}
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

	@Override
	public Boolean update(Order order) {
		try {
			sessionFactory.getCurrentSession().update(order);
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
			Order order = getOrder(id);
			sessionFactory.getCurrentSession().delete(order);
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
			return sessionFactory.getCurrentSession().get(Order.class, id) != null;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

}
