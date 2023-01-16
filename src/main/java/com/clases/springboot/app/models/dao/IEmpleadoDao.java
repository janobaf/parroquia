package com.clases.springboot.app.models.dao;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.clases.springboot.app.models.entity.Empleado;

public interface IEmpleadoDao extends CrudRepository<Empleado, Long> {
	
	@Query(value="SELECT * FROM empleado where(dni= :dni or :dni = 'DNI')  ",nativeQuery=true)	
	public List<Empleado> buscarPorDNI(@Param("dni") String DNI);

}
