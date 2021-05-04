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

import com.ecommerce.api.model.Item;
import com.ecommerce.api.model.Order;
import com.ecommerce.api.model.Product;
import com.ecommerce.api.service.OrderService;
import com.ecommerce.api.service.ProductService;
import com.ecommerce.api.service.UserService;

@RestController
@RequestMapping("/api")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/orders")
	public ResponseEntity<List<Order>> getOrders(){
		List<Order> orders = orderService.getOrders();
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}
	
	@GetMapping("/order/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable("id") Integer id){
		Order order = orderService.getOrder(id);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
	
	@GetMapping("/order/{id}/items")
	public ResponseEntity<List<Item>> getItems(@PathVariable("id") Integer id){
		List<Item> items = orderService.getOrder(id).getItems();
		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
	}
	
	@PostMapping("/order/submit")
	public ResponseEntity<String> addOrder(@Valid @RequestBody Order order){
		
		for(Item item : order.getItems()) {
			item.setOrder(order);
			
			// If any product with no id
			if(item.getProduct().getId() == null) {
				return new ResponseEntity<String>("Product not found.", HttpStatus.NOT_FOUND);
			}
			
			// If any product with id
			if(!productService.exists(item.getProduct().getId())) {
				return new ResponseEntity<String>("Product not found.", HttpStatus.NOT_FOUND);
			}
			
			if(item.getQuantity() > item.getProduct().getQuantity()) {
				return new ResponseEntity<String>("Order quantity not available.", HttpStatus.BAD_REQUEST);
			}
		}
		
		if(orderService.add(order)) {
			return new ResponseEntity<String>("Order Added", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Something went wrong!", HttpStatus.NOT_IMPLEMENTED);
	}
	
	@PutMapping("/order/update")
	public ResponseEntity<String> updateOrder(@Valid @RequestBody Order order){
		if(! orderService.exists(order.getId())) {
			return new ResponseEntity<String>("Order not found.", HttpStatus.NOT_FOUND);
		}
		
		for(Item item : order.getItems()) {
			item.setOrder(order);
			
			// If any product with no id
			if(item.getProduct().getId() == null) {
				return new ResponseEntity<String>("Product not found.", HttpStatus.NOT_FOUND);
			}
			
			// If any product with id
			if(!productService.exists(item.getProduct().getId())) {
				return new ResponseEntity<String>("Product not found.", HttpStatus.NOT_FOUND);
			}
		}
		
		if(orderService.update(order)) {
			return new ResponseEntity<String>("Order Updated", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Something went wrong!", HttpStatus.NOT_IMPLEMENTED);
	}
	
	@DeleteMapping("/order/delete/{id}")
	public ResponseEntity<String> updateOrder(@PathVariable("id") Integer id){
		if(! orderService.exists(id)) {
			return new ResponseEntity<String>("Order not found.", HttpStatus.NOT_FOUND);
		}
		
		if(orderService.delete(id)) {
			return new ResponseEntity<String>("Order Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Something went wrong!", HttpStatus.NOT_IMPLEMENTED);
	}
}
