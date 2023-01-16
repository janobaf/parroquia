package com.clases.springboot.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clases.springboot.app.models.dao.ILibroDetalleDao;
import com.clases.springboot.app.models.entity.LibroDetalle;


@Service
public class LibroDetalleServiceImpl implements ILibroDetalleService {
	
	
	@Autowired
	private ILibroDetalleDao libroDetalleDao;
	
	@Transactional (readOnly= true)
	@Override
	public List<LibroDetalle> findAll() {
		return (List<LibroDetalle>)libroDetalleDao.findAll();
	}

	@Transactional (readOnly= true)
	@Override
	public LibroDetalle findById(Long id) {
		return libroDetalleDao.findById(id).orElse(null);
	}
	
	@Transactional
	@Override
	public void deleteById(Long id) {
		libroDetalleDao.deleteById(id);		
	}

	@Transactional
	@Override
	public void save(LibroDetalle libroDetalle) {
		libroDetalleDao.save(libroDetalle);		
	}

	
	
}
