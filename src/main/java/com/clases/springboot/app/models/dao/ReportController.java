package com.clases.springboot.app.models.dao;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clases.springboot.app.service.ILibroService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private ILibroService libroService;
	
	@GetMapping(value = {"/books"})
	public String generarPdfLibros() throws FileNotFoundException, JRException, IOException, Exception {
		
		
		return libroService.exportReport("pdf");
	}
	
}
