package com.ecommerce.api.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ecommerce.api.dao.CategoryDao;
import com.ecommerce.api.model.Category;
import com.ecommerce.api.model.Product;
import com.ecommerce.api.model.User;

@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Category> getCategories() {
		try {
			List<Category> categorys = new ArrayList<Category>();
			categorys = sessionFactory.getCurrentSession().createQuery("from Category").list();
			return categorys;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

	@Override
	public Category getCategory(Integer id) {
		try {
			 Category category = sessionFactory.getCurrentSession().get(Category.class, id);
			return category;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

	@Override
	public Boolean add(Category category) {
		try {
			sessionFactory.getCurrentSession().save(category);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

	@Override
	public Boolean update(Category category) {
		try {
			sessionFactory.getCurrentSession().update(category);
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
			Category category = getCategory(id);
			sessionFactory.getCurrentSession().delete(category);
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
			return sessionFactory.getCurrentSession().get(Category.class, id) != null;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProducts(Integer categoryId) {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from Product where category_id=:categoryId");
			return (List<Product>) query.setParameter("categoryId", categoryId).list();
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

}
