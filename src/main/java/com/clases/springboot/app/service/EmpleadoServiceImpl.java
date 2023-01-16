package com.clases.springboot.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clases.springboot.app.models.dao.IEmpleadoDao;
import com.clases.springboot.app.models.entity.Empleado;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

	@Autowired
	private IEmpleadoDao empleadoDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<Empleado> findAll() {
		return (List<Empleado>)empleadoDao.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Empleado findById(Long id) {
		return empleadoDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void save(Empleado empleado) {
		empleadoDao.save(empleado);		
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Empleado> buscarPorDNI(String DNI) {
		return (List<Empleado>) empleadoDao.buscarPorDNI(DNI);
	}

	
	@Transactional
	@Override
	public void deleteById(Long id) {
		empleadoDao.deleteById(id);
	}

}
