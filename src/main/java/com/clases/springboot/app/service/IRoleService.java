package com.clases.springboot.app.service;

import java.util.List;

import com.clases.springboot.app.models.entity.Role;

public interface IRoleService {

	public List<Role> findAll();

	Role findById(Long id);

	void save(Role role);

	void deleteById(Long id);
}
