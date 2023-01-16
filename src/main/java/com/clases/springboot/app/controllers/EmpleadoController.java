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

import com.clases.springboot.app.models.entity.Empleado;
import com.clases.springboot.app.service.IEmpleadoService;

@Controller
@SessionAttributes("empleado")
public class EmpleadoController {
	@Autowired
	//@Qualifier("empleadoDaoJPA")
	private IEmpleadoService empleadoService;
	
	@RequestMapping(value= "/listarEmpleado", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Empleados");
		model.addAttribute("empleados", empleadoService.findAll());
		return "empleado/listarEmpleado";
	}
	
	@RequestMapping(value="/formEmpleado")
	public String crear(Map<String, Object> model) {
		model.put("titulo", "Formulario de Empleado");
		
		Empleado empleado = new Empleado();
		model.put("empleado", empleado);
		return "empleado/formEmpleado";
	}
	
	@RequestMapping(value="/formEmpleado/{id}")
	public String editar (@PathVariable(value="id") Long id, Map<String, Object> model,RedirectAttributes f) {
		Empleado empleado = null;
		
		if (id>0) {
			empleado = empleadoService.findById(id);
			if(empleado == null) {
				f.addFlashAttribute("error","El ID del Empleado no existe en la BD");
				return "redirect:/empleado/listarEmpleado";
			}
		}
		else {			
			f.addFlashAttribute("error","El ID del Empleado no puede ser cero!");
			return "redirect:/empleado/listarEmpleado";
		}
		model.put("empleado", empleado);
		model.put("titulo", "Editar Empleado");
		return "empleado/formEmpleado";
	}
	
	
	@RequestMapping(value="/formEmpleado", method=RequestMethod.POST)
	public String guardar(@Valid Empleado empleado, BindingResult result, Model model, RedirectAttributes f,SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Empleado");
			return "empleado/formEmpleado";
		}
		empleadoService.save(empleado);
		status.setComplete();
		f.addFlashAttribute("success","Grabado con exito");
		return "redirect:empleado/listarEmpleado";
	}
	
	@RequestMapping(value="/eliminarEmpleado/{id}")
	public String eliminar(@PathVariable(value="id") Long id,RedirectAttributes f ) {		
		if (id>0)
			empleadoService.deleteById(id);
			f.addFlashAttribute("success","Eliminado con exito");
		return "redirect:/empleado/listarEmpleado";			
		
	}
	
	//listar por DNI
			@RequestMapping(value="listarEmpleado/{DNI}", method=RequestMethod.GET)
			public String filtrar(@PathVariable(value="DNI") String DNI, Model model ) {
					model.addAttribute("titulo", "Empleados");
					empleadoService.buscarPorDNI(DNI);
					model.addAttribute("empleados", empleadoService.buscarPorDNI(DNI));
					return "empleado/listarEmpleado";
			}
	
}
