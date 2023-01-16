package com.clases.springboot.app.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clases.springboot.app.models.dao.ActaRepository;
import com.clases.springboot.app.models.dao.IActaDao;
import com.clases.springboot.app.models.entity.Acta;


@Service
public class ActaServiceImpl implements IActaService {
	
	@Autowired
	private ActaRepository repository;

	@Autowired
	private IActaDao actaDao;
	@Transactional (readOnly= true)
	@Override
	public List<Acta> findAll() {
		return (List<Acta>)actaDao.findAll();
	}
	
	
	
}
