package com.clases.springboot.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clases.springboot.app.models.dao.IAgendaDao;
import com.clases.springboot.app.models.entity.Agenda;
@Service
public class AgendaServiceImpl implements IAgendaService {

	@Autowired
	private IAgendaDao agendaDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<Agenda> findAll() {
		return (List<Agenda>)agendaDao.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Agenda findById(Long id) {
		return agendaDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void save(Agenda agenda) {
		agendaDao.save(agenda);
		
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		agendaDao.deleteById(id);
		
	}

}
