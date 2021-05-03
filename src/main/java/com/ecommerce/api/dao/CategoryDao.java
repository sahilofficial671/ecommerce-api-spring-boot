package com.ecommerce.api.dao;

import java.util.List;

import com.ecommerce.api.model.Category;
import com.ecommerce.api.model.Product;

public interface CategoryDao {
	List<Category> getCategories();
	Category getCategory(Integer id);
	Boolean add(Category category);
	Boolean update(Category category);
	Boolean delete(Integer id);
	Boolean exists(Integer id);
	
	List<Product> getProducts(Integer categoryId);
}
