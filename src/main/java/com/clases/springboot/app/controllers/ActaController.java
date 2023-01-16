package com.clases.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.clases.springboot.app.service.IActaService;

@Controller
@SessionAttributes("acta")
public class ActaController {
	
	@Autowired
	private IActaService actaService;
	
	@RequestMapping(value= {"/listarActa"}, method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Actas");
		model.addAttribute("actas", actaService.findAll());
		return "acta/listarActa";
	}

}
