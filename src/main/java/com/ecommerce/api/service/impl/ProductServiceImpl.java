package com.ecommerce.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.api.dao.ProductDao;
import com.ecommerce.api.model.Product;
import com.ecommerce.api.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao productDao;
	
	@Override
	public List<Product> getProducts() {
		return productDao.getProducts();
	}

	@Override
	public Product getProduct(Integer id) {
		return productDao.getProduct(id);
	}

	@Override
	public Boolean add(Product product) {
		return productDao.add(product);
	}

	@Override
	public Boolean update(Product product) {
		return productDao.update(product);
	}

	@Override
	public Boolean delete(Integer id) {
		return productDao.delete(id);
	}

	@Override
	public Boolean exists(Integer id) {
		return productDao.exists(id);
	}

	@Override
	public Boolean deleteExistingImages(Integer id) {
		return productDao.deleteExistingImages(id);
	}

	@Override
	public Product getBySlug(String slug) {
		return productDao.getBySlug(slug);
	}

}
