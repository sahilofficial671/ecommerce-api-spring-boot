package com.ecommerce.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.api.dao.RoleDao;
import com.ecommerce.api.model.Role;
import com.ecommerce.api.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public List<Role> getRoles() {
		return roleDao.getRoles();
	}

	@Override
	public Role getRole(Integer id) {
		return roleDao.getRole(id);
	}

	@Override
	public Boolean add(Role role) {
		return roleDao.add(role);
	}

	@Override
	public Boolean update(Role role) {
		return roleDao.update(role);
	}

	@Override
	public Boolean delete(Integer id) {
		return roleDao.delete(id);
	}

	@Override
	public Boolean exists(Integer id) {
		return roleDao.exists(id);
	}

}
