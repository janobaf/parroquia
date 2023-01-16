package com.clases.springboot.app.controllers;

import java.util.LinkedHashMap;
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

import com.clases.springboot.app.models.entity.Libro;
import com.clases.springboot.app.models.entity.LibroDetalle;
import com.clases.springboot.app.service.ILibroDetalleService;

@Controller
@SessionAttributes("libroDetalle")
public class LibroDetalleController {

	@Autowired
	private ILibroDetalleService libroDetalleService;	
	
	
	@RequestMapping(value= {"/listarLibroBautizo"}, method=RequestMethod.GET)
	public String listarBautizo(Model model) {
		model.addAttribute("titulo", "Registro Libros Bautizo");
		model.addAttribute("librosBautizo", libroDetalleService.findAll());
		return "libroDetalle/listarLibroBautizo";
	}
	
	
	@RequestMapping(value= {"/listarLibroConfirmacion"}, method=RequestMethod.GET)
	public String listarLibroConfirmacion(Model model) {
		model.addAttribute("titulo", "Registro Libros Confirmaci�n");
		model.addAttribute("librosConfirmacion", libroDetalleService.findAll());
		return "libroDetalle/listarLibroConfirmacion";
	}
	
	@RequestMapping(value= {"/listarLibroMatrimonio"}, method=RequestMethod.GET)
	public String listarLibroMatrimonio(Model model) {
		model.addAttribute("titulo", "Registro Libros Matrimonio");
		model.addAttribute("librosMatrimonio", libroDetalleService.findAll());
		return "libroDetalle/listarLibroMatrimonio";
	}
	
	
	
	@RequestMapping(value= {"/formLibroBautizo"} )
	public String crearBautizo(Map<String, Object> model) {
		model.put("titulo", "Formulario de Libro Bautizo");		
		LibroDetalle libroBautizo = new LibroDetalle();
		model.put("librosBautizo", libroBautizo);
		return "libroDetalle/formLibroBautizo";
	}
	
	@RequestMapping(value= {"/formLibroConfirmacion"} )
	public String crearConfirmacion(Map<String, Object> model) {
		model.put("titulo", "Formulario de Libro Confirmacion");		
		LibroDetalle libroConfirmacion = new LibroDetalle();
		model.put("librosConfirmacion", libroConfirmacion);
		return "libroDetalle/formLibroConfirmacion";
	}
	
	@RequestMapping(value= {"/formLibroMatrimonio"} )
	public String crearMatrimonio(Map<String, Object> model) {
		model.put("titulo", "Formulario de Libro Matrimonio");		
		LibroDetalle libroMatrimonio = new LibroDetalle();
		model.put("librosMatrimonio", libroMatrimonio);
		return "libroDetalle/formLibroMatrimonio";
	}
	
	
	@RequestMapping(value= {"/formLibroBautizo"}, method=RequestMethod.POST)
	public String guardarLibroBautizo(@Valid LibroDetalle libroDetalle, BindingResult result, Model model,RedirectAttributes f, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Libro Bautizo");
			return "libroDetalle/formLibroBautizo";
		}
		libroDetalleService.save(libroDetalle);
		status.setComplete();
		f.addFlashAttribute("success","Grabado con exito");
		return "redirect:libroDetalle/listarLibroBautizo";
	}


	@RequestMapping(value="formLibroBautizo/{id}" )
	public String editar (@PathVariable(value="id") Long id, Map<String, Object> model,RedirectAttributes f) {
		LibroDetalle libroDetalle = null;
		
		if (id>0) {
			libroDetalle = libroDetalleService.findById(id);
			if(libroDetalle == null) {
				f.addFlashAttribute("error","El ID del Libro no existe en la BD");
				return "libroDetalle/listarLibroDetalle";
			}
		}
		else {
			f.addFlashAttribute("error","El ID del Libro no puede ser cero!");
			return "libroDetalle/listarLibroDetalle";
			
		}
		model.put("libroDetalle", libroDetalle);
		model.put("titulo", "Editar Libro");
		return "libroDetalle/formLibroBautizo";
	}
	
	
}
