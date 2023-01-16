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

import com.clases.springboot.app.models.entity.Role;
import com.clases.springboot.app.service.IRoleService;

@Controller
@SessionAttributes("role")
public class RoleController {
	
	@Autowired
	private IRoleService roleService;
	
	@RequestMapping(value= "/listarRole", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Roles");
		model.addAttribute("roles", roleService.findAll());
		return "rol/listarRole";
	}
	
	@RequestMapping(value="/formRole")
	public String crear(Map<String, Object> model) {
		model.put("titulo", "Formulario de Role");
		
		Role role = new Role();
		model.put("role", role);
		return "rol/formRole";
	}
	
	
	@RequestMapping(value="/formRole/{id}")
	public String editar (@PathVariable(value="id") Long id, Map<String, Object> model,RedirectAttributes f) {
		Role role = null;
		
		if (id>0) {
			role = roleService.findById(id);
			if(role == null) {
				f.addFlashAttribute("error","El ID del Rol no existe en la BD");
				return "redirect:/rol/listarRole";
			}
		}
		else {			
			f.addFlashAttribute("error","El ID del Rol no puede ser cero!");
			return "redirect:/rol/listarRole";
		}
		model.put("role", role);
		model.put("titulo", "Editar el Rol");
		return "rol/formRole";
	}
	
	
	@RequestMapping(value="/formRole", method=RequestMethod.POST)
	public String guardar(@Valid Role role, BindingResult result, Model model, RedirectAttributes f,SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Rol");
			return "rol/formRole";
		}
		roleService.save(role);
		status.setComplete();
		f.addFlashAttribute("success","Grabado con exito");
		return "redirect:rol/listarRole";
	}
	
	@RequestMapping(value="/eliminarRole/{id}")
	public String eliminar(@PathVariable(value="id") Long id,RedirectAttributes f ) {		
		if (id>0)
			roleService.deleteById(id);
			f.addFlashAttribute("success","Eliminado con exito");
		return "redirect:/rol/listarRole";			
		
	}
	
}
