package com.clases.springboot.app.service;

import java.util.List;

import com.clases.springboot.app.models.entity.Agenda;

public interface IAgendaService {

	public List<Agenda> findAll();

	public Agenda findById(Long id);
	
	public void save(Agenda agenda);
	
	public void deleteById(Long id);
}
