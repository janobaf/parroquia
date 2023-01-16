package com.clases.springboot.app.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.clases.springboot.app.models.entity.Libro;

public interface LibroRepository extends JpaRepository<Libro, Integer>{

	
}
