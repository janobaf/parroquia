package com.clases.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.clases.springboot.app.models.entity.LibroDetalle;

public interface ILibroDetalleDao extends CrudRepository<LibroDetalle,Long>{

	/*Buscar Libro Bautizo*/	
	@Query(value="SELECT LD.id, C.dni, LD.num_registro, LD.num_registro, LD.libro, LD.num_pag, LD.parroco, LD.padrino, LD.madrina, LD.bautizo FROM libro_detalle LD " + 
			"JOIN cliente C " + 
			"ON LD.cliente = C.id " +
			"WHERE libro = '1' ",nativeQuery=true)	
	public List<LibroDetalle> getJoinDetalleLibro();
	
	
	
	
	
	
}
