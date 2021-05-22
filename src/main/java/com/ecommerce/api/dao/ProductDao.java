package com.ecommerce.api.dao;

import java.util.List;

import com.ecommerce.api.model.Product;

public interface ProductDao {
	List<Product> getProducts();
	Product getProduct(Integer id);
	Boolean add(Product product);
	Boolean update(Product product);
	Boolean delete(Integer id);
	Boolean exists(Integer id);
	
	Boolean deleteExistingImages(Integer id);
	Product getBySlug(String slug);
}
