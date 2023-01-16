package com.clases.springboot.app.service;

import java.util.List;

import com.clases.springboot.app.models.entity.LibroDetalle;

public interface ILibroDetalleService {
	
	public List<LibroDetalle> findAll();
	
	public LibroDetalle findById(Long id);
	
	void deleteById(Long id);
	
	public void save(LibroDetalle libroDetalle);
	

}
