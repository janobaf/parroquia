package com.clases.springboot.app.service;

import java.util.List;

import com.clases.springboot.app.models.entity.Empleado;

public interface IEmpleadoService {

	public List<Empleado> findAll();

	Empleado findById(Long id);

	void save(Empleado empleado);

	void deleteById(Long id);
	
	public List<Empleado> buscarPorDNI(String DNI);
}
