package com.clases.springboot.app.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.clases.springboot.app.models.entity.Acta;


public interface ActaRepository extends JpaRepository<Acta, Integer>{

	
}
