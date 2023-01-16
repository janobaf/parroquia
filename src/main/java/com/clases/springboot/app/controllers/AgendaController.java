package com.clases.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clases.springboot.app.models.entity.Agenda;
import com.clases.springboot.app.service.IAgendaService;



@Controller
@SessionAttributes("agenda")
public class AgendaController {
	
	@Autowired
	//@Qualifier("agendaDaoJPA")
	private IAgendaService agendaService;
	
	@RequestMapping(value= "/listarAgenda", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Agenda");
		model.addAttribute("agendas", agendaService.findAll());
		return "agenda/listarAgenda";
	}
	
	@RequestMapping(value="/formAgenda")
	public String crear(Map<String, Object> model) {
		model.put("titulo", "Formulario de Agenda");
		
		Agenda agenda = new Agenda();
		model.put("agenda", agenda);
		return "agenda/formAgenda";
	}
	
	@RequestMapping(value="/formAgenda/{id}")
	public String editar (@PathVariable(value="id") Long id, Map<String, Object> model,RedirectAttributes f) {
		Agenda agenda = null;
		
		if (id>0) {
			agenda = agendaService.findById(id);
			if(agenda == null) {
				f.addFlashAttribute("error","El ID de la Agenda no existe en la BD");
				return "redirect:/agenda/listarAgenda";
			}
		}
		else {			
			f.addFlashAttribute("error","El ID de la Agenda no puede ser cero!");
			return "redirect:/agenda/listarAgenda";
		}
		model.put("agenda", agenda);
		model.put("titulo", "Editar Agenda");
		return "agenda/formAgenda";
	}
	
	
	@RequestMapping(value="/formAgenda", method=RequestMethod.POST)
	public String guardar(@Valid Agenda agenda, BindingResult result, Model model, RedirectAttributes f,SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Agenda");
			return "agenda/formAgenda";
		}
		agendaService.save(agenda);
		status.setComplete();
		f.addFlashAttribute("success","Grabado con exito");
		return "redirect:agenda/listarAgenda";
	}
	
	@RequestMapping(value="/eliminarAgenda/{id}")
	public String eliminar(@PathVariable(value="id") Long id,RedirectAttributes f ) {		
		if (id>0)
			agendaService.deleteById(id);
			f.addFlashAttribute("success","Eliminado con exito");
		return "redirect:/agenda/listarAgenda";			
		
	}

}
