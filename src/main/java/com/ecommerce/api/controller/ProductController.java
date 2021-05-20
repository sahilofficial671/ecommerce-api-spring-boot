package com.ecommerce.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.model.Category;
import com.ecommerce.api.model.Item;
import com.ecommerce.api.model.Product;
import com.ecommerce.api.service.CategoryService;
import com.ecommerce.api.service.ProductService;

@RestController
@RequestMapping("/api")
@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/products")
	public ResponseEntity<Map<String, Object>> getProducts(){
		Map<String, Object> response = new HashMap<String, Object>();
		List<Product> products = productService.getProducts();
		response.put("status", "success");
		response.put("code", HttpStatus.OK.value());
		response.put("products", products);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Map<String, Object>> getProduct(@PathVariable("id") Integer id){
		Map<String, Object> response = new HashMap<String, Object>();
		Product product = productService.getProduct(id);
		
		if(product == null) {
			response.put("status", "error");
			response.put("code", HttpStatus.NOT_FOUND.value());
			response.put("message", "Product not found.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		response.put("status", "success");
		response.put("code", HttpStatus.OK.value());
		response.put("product", product);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/product/{id}/items")
	public ResponseEntity<List<Item>> getItems(@PathVariable("id") Integer id){
		List<Item> items = productService.getProduct(id).getItems();
		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
	}
	
	@PostMapping("/product/submit")
	public ResponseEntity<Map<String, Object>> addProduct(@Valid @RequestBody Product product)
	{
		Map<String, Object> response = new HashMap<String, Object>();
		for(Category category : product.getCategories()) {
			// if no category id
			if(category.getId() == null) {
				response.put("status", "error");
				response.put("message", "Category not found.");
				response.put("code", HttpStatus.NOT_FOUND.value());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			// If category id
			if(!categoryService.exists(category.getId())) {
				response.put("status", "error");
				response.put("message", "Category not found.");
				response.put("code", HttpStatus.NOT_FOUND.value());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
		}
		
		if(productService.add(product)) {
			response.put("status", "success");
			response.put("message", "Product Added.");
			response.put("code", HttpStatus.OK.value());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
		
		response.put("status", "error");
		response.put("message", "Something went wrong!");
		response.put("code", HttpStatus.NOT_IMPLEMENTED.value());
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_IMPLEMENTED);
	}
	
	@PutMapping("/product/update")
	public ResponseEntity<Map<String, Object>> updateProduct(@Valid @RequestBody Product product){
		Map<String, Object> response = new HashMap<String, Object>();
		if(! productService.exists(product.getId())) {
			response.put("status", "error");
			response.put("message", "Product not found.");
			response.put("code", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		for(Category category : product.getCategories()) {
			// if no category id
			if(category.getId() == null) {
				response.put("status", "error");
				response.put("message", "Category not found.");
				response.put("code", HttpStatus.NOT_FOUND.value());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			// If category id
			if(!categoryService.exists(category.getId())) {
				response.put("status", "error");
				response.put("message", "Category not found.");
				response.put("code", HttpStatus.NOT_FOUND.value());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
		}
		
		if(productService.update(product)) {
			response.put("status", "success");
			response.put("message", "Product updated.");
			response.put("code", HttpStatus.OK.value());
			response.put("product", productService.getProduct(product.getId()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
		
		response.put("status", "error");
		response.put("message", "Something went wrong!");
		response.put("code", HttpStatus.NOT_IMPLEMENTED.value());
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_IMPLEMENTED);
	}
	
	@DeleteMapping("/product/{id}/delete")
	public ResponseEntity<Map<String, Object>> updateProduct(@PathVariable("id") Integer id){
		Map<String, Object> response = new HashMap<String, Object>();
		
		if(! productService.exists(id)) {
			response.put("status", "error");
			response.put("message", "Product not found.");
			response.put("code", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		if(productService.getProduct(id).getItems().size() > 0) {
			response.put("status", "error");
			response.put("message", "Product is assigned to orders.");
			response.put("code", HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(productService.delete(id)) {
			response.put("status", "success");
			response.put("message", "Product Deleted.");
			response.put("code", HttpStatus.OK.value());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
		
		response.put("status", "error");
		response.put("message", "Something went wrong!");
		response.put("code", HttpStatus.NOT_IMPLEMENTED.value());
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_IMPLEMENTED);
	}
	
	
}
