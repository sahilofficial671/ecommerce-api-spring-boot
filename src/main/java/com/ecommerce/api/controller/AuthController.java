package com.ecommerce.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.model.Role;
import com.ecommerce.api.model.User;
import com.ecommerce.api.service.RoleService;
import com.ecommerce.api.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	
	@PostMapping("/admin/login")
	public ResponseEntity<Map<String, Object>> adminLogin(@RequestBody Map<String, String> data)
	{	
		Map<String, Object> response = new HashMap<String, Object>();
		User user = userService.getUserByEmailAndPassword(data.get("email"), data.get("password"));
		System.out.println(user);
		
		if(user == null) {
			response.put("status", "error");
			response.put("code", HttpStatus.NOT_FOUND.value());
			response.put("message", "User not found");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		for(Role role : user.getRoles()) {
			if(role.getName().equals("Admin")) {
				response.put("status", "success");
				response.put("code", HttpStatus.OK.value());
				response.put("message", "Logged in");
				response.put("user", user);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		}
		
		response.put("status", "error");
		response.put("code", HttpStatus.NOT_FOUND.value());
		response.put("message", "User not found");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/customer/login")
	public ResponseEntity<Map<String, Object>> customerLogin(@RequestBody Map<String, String> data)
	{	
		Map<String, Object> response = new HashMap<String, Object>();
		User user = userService.getUserByEmailAndPassword(data.get("email"), data.get("password"));
		
		if(user == null) {
			response.put("status", "error");
			response.put("code", HttpStatus.NOT_FOUND.value());
			response.put("message", "User not found");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		for(Role role : user.getRoles()) {
			if(role.getName().equals("Customer")) {
				response.put("status", "success");
				response.put("code", HttpStatus.OK.value());
				response.put("message", "Logged in");
				response.put("user", user);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		}
		
		response.put("status", "error");
		response.put("code", HttpStatus.NOT_FOUND.value());
		response.put("message", "User not matched found");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}
}
