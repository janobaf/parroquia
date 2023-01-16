package com.clases.springboot.app.models.dao;



import org.springframework.data.repository.CrudRepository;

import com.clases.springboot.app.models.entity.Agenda;

public interface IAgendaDao extends CrudRepository<Agenda, Long> {
	
}
