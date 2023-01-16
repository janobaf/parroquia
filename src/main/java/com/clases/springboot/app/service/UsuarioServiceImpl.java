package com.clases.springboot.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clases.springboot.app.models.dao.IUsuarioDao;
import com.clases.springboot.app.models.entity.Usuario;


@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional (readOnly=true)
	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>)usuarioDao.findAll();
	}

	@Transactional (readOnly=true)
	@Override
	public Usuario findById(Long id) {
		
		return usuarioDao.findById(id).orElse(null);
	}

	@Transactional 
	@Override
	public void save(Usuario usuario) {
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		usuarioDao.save(usuario);
		
	}

	@Transactional 
	@Override
	public void deleteById(Long id) {
		usuarioDao.deleteById(id);
		
	}

}
