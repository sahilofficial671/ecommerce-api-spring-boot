package com.ecommerce.api.service;

import java.util.List;

import com.ecommerce.api.model.Product;

public interface ProductService {
	List<Product> getProducts();
	Product getProduct(Integer id);
	Boolean add(Product product);
	Boolean update(Product product);
	Boolean delete(Integer id);
	Boolean exists(Integer id);
}
