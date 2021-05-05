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

import com.ecommerce.api.model.Order;
import com.ecommerce.api.model.OrderStatus;
import com.ecommerce.api.service.OrderStatusService;

@RestController
@RequestMapping("/api")
public class OrderStatusController {
	
	@Autowired
	private OrderStatusService orderStatusService;
	
	@GetMapping("/order_statuses")
	public ResponseEntity<List<OrderStatus>> getOrderStatuses(){
		List<OrderStatus> orderStatuses = orderStatusService.getOrderStatuses();
		return new ResponseEntity<List<OrderStatus>>(orderStatuses, HttpStatus.OK);
	}
	
	@GetMapping("/order_status/{id}")
	public ResponseEntity<OrderStatus> getOrderStatus(@PathVariable("id") Integer id){
		OrderStatus orderStatus = orderStatusService.getOrderStatus(id);
		return new ResponseEntity<OrderStatus>(orderStatus, HttpStatus.OK);
	}
	
	@GetMapping("/order_status/{id}/orders")
	public ResponseEntity<List<Order>> getOrders(@PathVariable("id") Integer id){
		List<Order> orders = orderStatusService.getOrderStatus(id).getOrders();
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}
	
	@PostMapping("/order_status/submit")
	public ResponseEntity<String> addOrderStatus(@Valid @RequestBody OrderStatus orderStatus){
		if(orderStatusService.add(orderStatus)) {
			return new ResponseEntity<String>("Order Status Added.", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Something went wrong!", HttpStatus.NOT_IMPLEMENTED);
	}
	
	@PutMapping("/order_status/update")
	public ResponseEntity<String> updateOrderStatus(@Valid @RequestBody OrderStatus orderStatus){
		if(! orderStatusService.exists(orderStatus.getId())) {
			return new ResponseEntity<String>("Order Status not found.", HttpStatus.NOT_FOUND);
		}
		
		if(orderStatusService.update(orderStatus)) {
			return new ResponseEntity<String>("Order Status Updated.", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Something went wrong!", HttpStatus.NOT_IMPLEMENTED);
	}
	
	@DeleteMapping("/order_status/{id}/delete")
	public ResponseEntity<String> updateOrderStatus(@PathVariable("id") Integer id){
		if(! orderStatusService.exists(id)) {
			return new ResponseEntity<String>("Order Status not found.", HttpStatus.NOT_FOUND);
		}
		
		if(orderStatusService.getOrderStatus(id).getOrders().size() > 0) {
			return new ResponseEntity<String>("Order Status is assigned to user.", HttpStatus.BAD_REQUEST);
		}
		
		if(orderStatusService.delete(id)) {
			return new ResponseEntity<String>("Order Status Deleted", HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Something went wrong!", HttpStatus.NOT_IMPLEMENTED);
	}
}
