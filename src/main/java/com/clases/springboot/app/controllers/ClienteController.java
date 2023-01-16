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


import com.clases.springboot.app.models.entity.Cliente;
import com.clases.springboot.app.service.IClienteService;



@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	//@Qualifier("clienteDaoJPA")
	private IClienteService clienteService;
	
	@RequestMapping(value= {"/listarCliente"}, method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Fieles");
		model.addAttribute("clientes", clienteService.findAll());
		return "cliente/listarCliente";
	}
	
	@RequestMapping(value="/formCliente")
	public String crear(Map<String, Object> model) {
		model.put("titulo", "Formulario de Fiel");
		
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		return "cliente/formCliente";
	}
	
	@RequestMapping(value="/formCliente/{id}")
	public String editar (@PathVariable(value="id") Long id, Map<String, Object> model,RedirectAttributes f) {
		Cliente cliente = null;
		
		if (id>0) {
			cliente = clienteService.findById(id);
			if(cliente == null) {
				f.addFlashAttribute("error","El ID del Fiel no existe en la BD");
				return "redirect:/cliente/listarCliente";
			}
		}
		else {			
			f.addFlashAttribute("error","El ID del Fiele no puede ser cero!");
			return "redirect:/listarCliente";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Editar de Fiel");
		return "cliente/formCliente";
	}
	
	
	@RequestMapping(value="/formCliente", method=RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes f,SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Fiel");
			return "cliente/formCliente";
		}
		clienteService.save(cliente);
		status.setComplete();
		f.addFlashAttribute("success","Grabado con exito");
		return "redirect:cliente/listarCliente";
	}
	
	@RequestMapping(value="/eliminarCliente/{id}")
	public String eliminar(@PathVariable(value="id") Long id,RedirectAttributes f ) {		
		if (id>0)
			clienteService.deleteById(id);
			f.addFlashAttribute("success","Eliminado con exito");
		return "redirect:/cliente/listarCliente";			
		
	}
	
	@GetMapping("/clientePDF")
    public String generateReport() throws IOException, Exception {
        String base64= clienteService.exportReport("pdf");
		return "redirect:/cliente/listarCliente";	
    }
	
	
	//listar por DNI
		@RequestMapping(value="listarCliente/{DNI}", method=RequestMethod.GET)
		public String filtrar(@PathVariable(value="DNI") String DNI, Model model ) {
				model.addAttribute("titulo", "Fieles");
				clienteService.buscarPorDNI(DNI);
				model.addAttribute("clientes", clienteService.buscarPorDNI(DNI));
				return "cliente/listarCliente";
		}
}
