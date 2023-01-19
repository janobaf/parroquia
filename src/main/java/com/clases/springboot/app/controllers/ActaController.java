package com.clases.springboot.app.controllers;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.clases.springboot.app.models.entity.Report;
import com.clases.springboot.app.service.IActaService;
import com.clases.springboot.app.service.IReportService;
import com.itextpdf.text.DocumentException;

import net.sf.jasperreports.engine.JRException;

@Controller
@SessionAttributes("acta")
public class ActaController {
	
	@Autowired
	private IActaService actaService;
	
	@Autowired
	private IReportService reportService;
	
	@RequestMapping(value= {"/listarActa"}, method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Actas");
		model.addAttribute("actas", actaService.findAll());
		return "acta/listarActa";
	}
	
	
	@GetMapping("/prueba")
	@ResponseBody
	public Report reportePrueba() throws JRException, IOException, ParseException, DocumentException, Exception{
		
		return reportService.reportePrueba();
	}
}
