package com.ecommerce.api.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ecommerce.api.dao.ProductDao;
import com.ecommerce.api.model.Product;
import com.ecommerce.api.model.ProductImage;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Product> getProducts() {
		try {
			List<Product> products = new ArrayList<Product>();
			products = sessionFactory.getCurrentSession().createQuery("from Product").list();
			return products;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

	@Override
	public Product getProduct(Integer id) {
		try {
			 Product product = sessionFactory.getCurrentSession().get(Product.class, id);
			return product;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

	@Override
	public Boolean add(Product product) {
		try {
			sessionFactory.getCurrentSession().save(product);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

	@Override
	public Boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
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
			Product product = getProduct(id);
			sessionFactory.getCurrentSession().delete(product);
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
			return sessionFactory.getCurrentSession().get(Product.class, id) != null;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean deleteExistingImages(Integer id) {
		try {
			List<ProductImage> images = new ArrayList<ProductImage>();
			Query query = sessionFactory.getCurrentSession().createQuery("from ProductImage where product_id = :id");
			query.setParameter("id", id);
			images = query.list();
			
			for(ProductImage productImage : images) {
				sessionFactory.getCurrentSession().delete(productImage);
			}
			
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public Product getBySlug(String slug) {
		try {
			List<Product> products = new ArrayList<Product>();

			Query query = sessionFactory.getCurrentSession().createQuery("from Product where slug = :slug");
			query.setString("slug", slug);
			products = query.list();
			
			Product product = null;
			
			if(products.size() == 1) {
				product =  products.get(0);
			}
			
			return product;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

}
