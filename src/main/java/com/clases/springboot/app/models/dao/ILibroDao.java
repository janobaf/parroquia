package com.clases.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.clases.springboot.app.models.entity.Libro;

public interface ILibroDao extends CrudRepository<Libro,Long>{

	@Query(value="SELECT * FROM libro where(num_tomo= :num_tomo or :num_tomo = 'numTomo')  ",nativeQuery=true)	
	public List<Libro> buscarPorLibro(@Param("num_tomo") String numTomo);
	

}
