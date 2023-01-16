package com.clases.springboot.app.controllers;

import java.io.IOException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.clases.springboot.app.models.entity.Libro;
import com.clases.springboot.app.service.ILibroService;



@Controller
@SessionAttributes("libro")
public class LibroController {

	@Autowired
	private ILibroService libroService;
	
	@RequestMapping(value= {"/listarLibro"}, method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Libros");
		model.addAttribute("libros", libroService.findAll());
		return "libro/listarLibro";
	}
	
	@RequestMapping(value= {"/formLibro"} )
	public String crear(Map<String, Object> model) {
		model.put("titulo", "Formulario de Libro");		
		Libro libro = new Libro();
		model.put("libro", libro);
		return "libro/formLibro";
	}
	
	//listar por tomo
	@RequestMapping(value="listarLibro/{numTomo}", method=RequestMethod.GET)
	public String filtrar(@PathVariable(value="numTomo") String numTomo, Model model ) {
			model.addAttribute("titulo", "Listado de Libros");
			libroService.buscarPorLibro(numTomo);				
			model.addAttribute("libros", libroService.buscarPorLibro(numTomo));
			return "libro/listarLibro";
	}
	
	/*
	 //listar por tomo
	@RequestMapping(value="listarLibro/{numTomo}", method=RequestMethod.GET)
	public String filtrar(@PathVariable(value="numTomo") String numTomo, Model model ) {
			model.addAttribute("titulo", "Listado de Libros");
			libroService.buscarPorLibro(numTomo);				
			model.addAttribute("libros", libroService.buscarPorLibro(numTomo));
			return "listarLibro";
	}
	 
	 */
	
	
	@RequestMapping(value="formLibro/{id}" )
	public String editar (@PathVariable(value="id") Long id, Map<String, Object> model,RedirectAttributes f) {
		Libro libro = null;
		
		if (id>0) {
			libro = libroService.findById(id);
			if(libro == null) {
				f.addFlashAttribute("error","El ID del Libro no existe en la BD");
				return "listarLibro";
			}
		}
		else {
			f.addFlashAttribute("error","El ID del Libro no puede ser cero!");
			return "listarLibro";
			
		}
		model.put("libro", libro);
		model.put("titulo", "Editar Libro");
		return "libro/formLibro";
	}
	
	
	@RequestMapping(value= {"/formLibro"}, method=RequestMethod.POST)
	public String guardar(@Valid Libro libro, BindingResult result, Model model,RedirectAttributes f, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Libro");
			return "libro/formLibro";
		}
		libroService.save(libro);
		status.setComplete();
		f.addFlashAttribute("success","Grabado con exito");
		return "redirect:libro/listarLibro";
	}
	
	@RequestMapping(value="/eliminarLibro/{id}")
	public String eliminar(@PathVariable(value="id") Long id,RedirectAttributes f) {		
		if (id>0)
			libroService.deleteById(id);
			f.addFlashAttribute("success","Eliminado con exito");
		return "redirect:/libro/listarLibro";		
	}
	
	
	
	@GetMapping("/reee")
    public String generateReport() throws IOException, Exception {
        String base64= libroService.exportReport("pdf");
		return "redirect:/libro/listarLibro";	
    }
	
	
}
