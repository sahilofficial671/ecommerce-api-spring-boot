package com.ecommerce.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {
	
	@GetMapping("/")
	public ResponseEntity<String> index(){
		return new ResponseEntity<String>("Hii Ecommerce API Spring Boot here", HttpStatus.OK);
	}
}
