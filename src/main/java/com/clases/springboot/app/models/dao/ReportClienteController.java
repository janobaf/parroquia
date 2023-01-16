package com.clases.springboot.app.models.dao;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clases.springboot.app.service.IClienteService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/reportCliente")
public class ReportClienteController {

	@Autowired
	private IClienteService clienteService;
	
	@GetMapping(value = {"/books"})
	public String generarPdfClientes() throws FileNotFoundException, JRException, IOException, Exception {
		
		
		return clienteService.exportReport("pdf");
	}
}
