package com.ecommerce.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.model.Category;
import com.ecommerce.api.model.Order;
import com.ecommerce.api.model.Product;
import com.ecommerce.api.service.CategoryService;
import com.ecommerce.api.service.OrderService;
import com.ecommerce.api.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts(){
		List<Product> products = productService.getProducts();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id){
		Product product = productService.getProduct(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@PostMapping("/product/submit")
	public ResponseEntity<String> addProduct(@Valid @RequestBody Product product)
	{
		for(Category category : product.getCategories()) {
			// if no category id
			if(category.getId() == null) {
				return new ResponseEntity<String>("Category not found.", HttpStatus.NOT_FOUND);
			}
			
			// If category id
			if(!categoryService.exists(category.getId())) {
				return new ResponseEntity<String>("Category not found.", HttpStatus.NOT_FOUND);
			}
		}
		if(productService.add(product)) {
			return new ResponseEntity<String>("Product Added.", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Something went wrong!", HttpStatus.NOT_IMPLEMENTED);
	}
	
	@PutMapping("/product/update")
	public ResponseEntity<String> updateProduct(@Valid @RequestBody Product product){
		if(! productService.exists(product.getId())) {
			return new ResponseEntity<String>("Product not found.", HttpStatus.NOT_FOUND);
		}
		
		for(Category category : product.getCategories()) {
			// if no category id
			if(category.getId() == null) {
				return new ResponseEntity<String>("Category not found.", HttpStatus.NOT_FOUND);
			}
			
			// If category id
			if(!categoryService.exists(category.getId())) {
				return new ResponseEntity<String>("Category not found.", HttpStatus.NOT_FOUND);
			}
		}
		
		if(productService.update(product)) {
			return new ResponseEntity<String>("Product Updated.", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Something went wrong!", HttpStatus.NOT_IMPLEMENTED);
	}
	
	@DeleteMapping("/product/delete/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable("id") Integer id){
		if(! productService.exists(id)) {
			return new ResponseEntity<String>("Product not found.", HttpStatus.NOT_FOUND);
		}
		
		if(productService.delete(id)) {
			return new ResponseEntity<String>("Product Deleted.", HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Something went wrong!", HttpStatus.NOT_IMPLEMENTED);
	}
	
	
}
