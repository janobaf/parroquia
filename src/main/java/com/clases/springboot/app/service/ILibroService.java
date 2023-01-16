package com.clases.springboot.app.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.clases.springboot.app.models.entity.Libro;

import net.sf.jasperreports.engine.JRException;

public interface ILibroService {
	
	public List<Libro> findAll();
	
	Libro findById(Long id);
	
	void deleteById(Long id);
	
	public void save(Libro libro);
	
	public List<Libro> buscarPorLibro(String numTomo);
	
	public List<Libro> buscarLibroNombre(String tipoSacramento);

	String exportReport(String reportFormat) throws FileNotFoundException, JRException, IOException, Exception;
	
	String base64Libros(String format) throws FileNotFoundException, JRException;
}
