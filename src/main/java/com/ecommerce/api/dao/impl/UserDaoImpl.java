package com.ecommerce.api.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ecommerce.api.dao.UserDao;
import com.ecommerce.api.model.Role;
import com.ecommerce.api.model.User;

import ch.qos.logback.classic.net.SyslogAppender;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<User> getUsers() {
		try {
			List<User> users = new ArrayList<User>();
			users = sessionFactory.getCurrentSession().createQuery("from User").list();
			return users;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

	@Override
	public User getUser(Integer id) {
		try {
			 User user = sessionFactory.getCurrentSession().get(User.class, id);
			return user;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

	@Override
	public Boolean add(User user) {
		try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

	@Override
	public Boolean update(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

	@Override
	public Boolean delete(Integer id) {
		try {
			User user = getUser(id);
			sessionFactory.getCurrentSession().delete(user);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Boolean ifAnyUserHasThisRole(Role role) {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from User where roleId=:roleId");
			query.setParameter("roleId", role.getId());
			List<User> users = query.list();
			return users.size() > 0;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Boolean exists(Integer id) {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from User where id=:id");
			query.setParameter("id", id);
			List<User> users = query.list();
			return users.size() > 0;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean ifUserNameIsTakenAlready(String userName) {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from User where user_name =:userName");
			query.setParameter("userName", userName);
			List<User> users = query.list();
			System.out.println("List:");
			System.out.println(users.toString());
			System.out.println("Size: "+users.size());
			return users.size() > 0;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean ifUserNameIsOnlyTakenByOrItsNew(User user) {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from User where user_name=:userName");
			query.setParameter("userName", user.getUserName());
			List<User> users = query.list();
			System.out.println("List from updation:");
			System.out.println(users);
			System.out.println("Size: "+users.size());
			
			// If any user found it should be given user only
			// If no user found
			if((users.size() == 1 && users.get(0).getId() == user.getId()) || users.size() == 0) {
				return true;
			}
			
			return false;
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from User where email=:email and password=:password");
			query.setParameter("email", email);
			query.setParameter("password", password);
			List<User> users = query.list();
			return users.size() == 1 ?  users.get(0) : null;
		}catch(Exception e) {
			e.printStackTrace();
			String message = "Error From: "+ this.getClass().getSimpleName() +" ["+Thread.currentThread().getStackTrace()[1].getMethodName()+"], Error Class: " + e.getClass().getSimpleName() + ", Message: "+ e.getMessage();
			return null;
		}
	}

}
