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

import com.ecommerce.api.model.Role;
import com.ecommerce.api.model.User;
import com.ecommerce.api.service.RoleService;
import com.ecommerce.api.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers(){
		List<User> users = userService.getUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") Integer id){
		User user = userService.getUser(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}/roles")
	public ResponseEntity<List<Role>> getRoles(@PathVariable("id") Integer id){
		List<Role> roles = userService.getUser(id).getRoles();
		return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
	}
	
	@PostMapping("/user/submit")
	public ResponseEntity<String> addUser(@Valid @RequestBody User user)
	{
		// Validate Roles
		for(Role role : user.getRoles()) {
			if(role.getId() == null) {
				return new ResponseEntity<String>("Role not found.", HttpStatus.NOT_FOUND);
			}
			
			if(!roleService.exists(role.getId())) {
				return new ResponseEntity<String>("Role not found.", HttpStatus.NOT_FOUND);
			}
		}
		
		if(userService.ifUserNameIsTakenAlready(user.getUserName())) {
			return new ResponseEntity<String>("Username is taken already", HttpStatus.BAD_REQUEST);
		}
		
		if(userService.add(user)) {
			return new ResponseEntity<String>("User Added", HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Something went wrong! Unable to add user.", HttpStatus.NOT_IMPLEMENTED);
	}
	
	@PutMapping("/user/update")
	public ResponseEntity<String> updateUser(@Valid @RequestBody User user)
	{
		// If User exists
		if(! userService.exists(user.getId())) {
			return new ResponseEntity<String>("User not found.", HttpStatus.NOT_FOUND);
		}
			
		// Validate Roles
		for(Role role : user.getRoles()) {
			if(role.getId() == null) {
				return new ResponseEntity<String>("Role not found.", HttpStatus.NOT_FOUND);
			}
			
			if(!roleService.exists(role.getId())) {
				return new ResponseEntity<String>("Role not found.", HttpStatus.NOT_FOUND);
			}
		}
		
		if(!userService.ifUserNameIsOnlyTakenByOrItsNew(user)) {
			return new ResponseEntity<String>("Username is taken already", HttpStatus.BAD_REQUEST);
		}
			
		if(userService.update(user)) {
			return new ResponseEntity<String>("User Updated", HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Something went wrong! Unable to update user.", HttpStatus.NOT_IMPLEMENTED);
	}
	
	@DeleteMapping("/user/{id}/delete")
	public ResponseEntity<String> updateUser(@PathVariable("id") Integer id)
	{	
		// If User exists
		if(! userService.exists(id)) {
			return new ResponseEntity<String>("User not found.", HttpStatus.NOT_FOUND);
		}
		
		if(userService.delete(id)) {
			return new ResponseEntity<String>("User Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Something went wrong!", HttpStatus.NOT_IMPLEMENTED);
	}
}
