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


import com.clases.springboot.app.models.entity.Usuario;
import com.clases.springboot.app.service.IUsuarioService;

@Controller
@SessionAttributes("usuario")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@RequestMapping("/")
    public String formUsuario() {
        return "Login";
    }
	
	
	@RequestMapping(value= "/listarUsuario", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Usuarios");
		model.addAttribute("usuarios", usuarioService.findAll());
		return "user/listarUsuario";
	}
	
	@RequestMapping(value="/formUsuario")
	public String crear(Map<String, Object> model) {
		model.put("titulo", "Formulario de Usuario");
		
		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		return "user/formUsuario";
	}
	
	@RequestMapping(value="/formUsuario/{id}")
	public String editar (@PathVariable(value="id") Long id, Map<String, Object> model,RedirectAttributes f) {
		Usuario usuario = null;
		
		if (id>0) {
			usuario = usuarioService.findById(id);
			if(usuario == null) {
				f.addFlashAttribute("error","El ID del Usuario no existe en la BD");
				return "redirect:/user/listarUsuario";
			}
		}
		else {			
			f.addFlashAttribute("error","El ID del Usuario no puede ser cero!");
			return "redirect:/user/listarUsuario";
		}
		model.put("usuario", usuario);
		model.put("titulo", "Editar el Usuario");
		return "user/formUsuario";
	}
	
	
	@RequestMapping(value="/formUsuario", method=RequestMethod.POST)
	public String guardar(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes f,SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Usuario");
			return "user/formUsuario";
		}
		usuarioService.save(usuario);
		status.setComplete();
		f.addFlashAttribute("success","Grabado con exito");
		return "redirect:user/listarUsuario";
	}
	
	@RequestMapping(value="/eliminarUsuario/{id}")
	public String eliminar(@PathVariable(value="id") Long id,RedirectAttributes f ) {		
		if (id>0)
			usuarioService.deleteById(id);
			f.addFlashAttribute("success","Eliminado con exito");
		return "redirect:/user/listarUsuario";			
		
	}	
	
	
	
	
}
