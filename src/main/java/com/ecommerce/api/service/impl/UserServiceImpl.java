package com.ecommerce.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.api.dao.UserDao;
import com.ecommerce.api.model.Role;
import com.ecommerce.api.model.User;
import com.ecommerce.api.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public List<User> getUsers() {
		return userDao.getUsers();
	}

	@Override
	public User getUser(Integer id) {
		return userDao.getUser(id);
	}

	@Override
	public Boolean add(User user) {
		return userDao.add(user);
	}

	@Override
	public Boolean update(User user) {
		return userDao.update(user);
	}

	@Override
	public Boolean delete(Integer id) {
		return userDao.delete(id);
	}

	@Override
	public Boolean ifAnyUserHasThisRole(Role role) {
		return userDao.ifAnyUserHasThisRole(role);
	}

	@Override
	public Boolean exists(Integer id) {
		return userDao.exists(id);
	}

	@Override
	public Boolean ifUserNameIsTakenAlready(String username) {
		return userDao.ifUserNameIsTakenAlready(username);
	}

	@Override
	public Boolean ifUserNameIsOnlyTakenByOrItsNew(User user) {
		return userDao.ifUserNameIsOnlyTakenByOrItsNew(user);
	}

	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		return userDao.getUserByEmailAndPassword(email, password);
	}

}
