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
import com.ecommerce.api.service.RoleService;
import com.ecommerce.api.service.UserService;

@RestController
@RequestMapping("/api")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	@GetMapping("/roles")
	public ResponseEntity<List<Role>> getRoles(){
		List<Role> roles = roleService.getRoles();
		return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
	}
	
	@GetMapping("/role/{id}")
	public ResponseEntity<Role> getRole(@PathVariable("id") Integer id){
		Role role = roleService.getRole(id);
		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}
	
	@PostMapping("/role/submit")
	public ResponseEntity<String> addRole(@Valid @RequestBody Role role){
		if(roleService.add(role)) {
			return new ResponseEntity<String>("Role Added", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Something went wrong!", HttpStatus.NOT_IMPLEMENTED);
	}
	
	@PutMapping("/role/update")
	public ResponseEntity<String> updateRole(@Valid @RequestBody Role role){
		if(! roleService.exists(role.getId())) {
			return new ResponseEntity<String>("Role not found.", HttpStatus.NOT_FOUND);
		}
		
		if(roleService.update(role)) {
			return new ResponseEntity<String>("Role Updated", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Something went wrong!", HttpStatus.NOT_IMPLEMENTED);
	}
	
	@DeleteMapping("/role/delete/{id}")
	public ResponseEntity<String> updateRole(@PathVariable("id") Integer id){
		if(! roleService.exists(id)) {
			return new ResponseEntity<String>("Role not found.", HttpStatus.NOT_FOUND);
		}
		if(userService.ifAnyUserHasThisRole(roleService.getRole(id))) {
			return new ResponseEntity<String>("Role is assigned to user.", HttpStatus.BAD_REQUEST);
		}
		
		if(roleService.delete(id)) {
			return new ResponseEntity<String>("Role Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Something went wrong!", HttpStatus.NOT_IMPLEMENTED);
	}
}
