package com.clases.springboot.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clases.springboot.app.models.dao.IRoleDao;
import com.clases.springboot.app.models.entity.Role;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleDao roleDao;
	
	@Transactional (readOnly=true)
	@Override
	public List<Role> findAll() {
		List<Role> findAll = (List<Role>)roleDao.findAll();
		return findAll;
	}

	@Transactional (readOnly=true)
	@Override
	public Role findById(Long id) {
		return roleDao.findById(id).orElse(null);
	}

	@Transactional 
	@Override
	public void save(Role role) {
		roleDao.save(role);
		
	}

	@Transactional 
	@Override
	public void deleteById(Long id) {
		roleDao.deleteById(id);
		
	}

}
