package com.ecommerce.api.service;

import java.util.List;

import com.ecommerce.api.model.Role;
import com.ecommerce.api.model.User;

public interface UserService {
	List<User> getUsers();
	User getUser(Integer id);
	Boolean add(User user);
	Boolean update(User user);
	Boolean delete(Integer id);
	
	Boolean ifAnyUserHasThisRole(Role role);
	Boolean exists(Integer id);
	Boolean ifUserNameIsTakenAlready(String username);
	Boolean ifUserNameIsOnlyTakenByOrItsNew(User user);
	User getUserByEmailAndPassword(String email, String password);
}
