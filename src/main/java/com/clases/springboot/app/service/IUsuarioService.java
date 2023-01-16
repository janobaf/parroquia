package com.clases.springboot.app.service;

import java.util.List;

import com.clases.springboot.app.models.entity.Usuario;

public interface IUsuarioService {

	public List<Usuario> findAll();
	
	public Usuario findById(Long id);
	
	public void save(Usuario usuario);
	
	public void deleteById(Long id);
}
