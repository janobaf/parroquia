package com.clases.springboot.app.controllers;

import java.io.Console;
import java.net.http.HttpRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.clases.springboot.app.service.ILibroDetalleService;


import com.clases.springboot.app.models.entity.LibroDetalle;

@Controller
@SessionAttributes("graph")
public class GraphController {

	@Autowired
	private ILibroDetalleService libroDetalleService;	
	private int devolverIdMes(String date)
	{
		//  SEP = -  OR NOT  /     
		//    0    1     2 
		//  [12] [12] [1998]
		String dateParts[] = date.split("/");
        int mes = Integer.parseInt(dateParts[1]) ;
		return mes;
	}
	private int devolverIDAnio(String date)
	{
		//  SEP = -  OR NOT  /     
		//    0    1     2 
		//  [12] [12] [1998]
		String dateParts[] = date.split("/");
        int mes = Integer.parseInt(dateParts[2]) ;
		return mes;
	}

	//base de datos     libroDetalle 
	@RequestMapping(value= {"/reportMes"}, method=RequestMethod.GET)
	public String barGraph(@RequestParam(value="id",required=false) Integer id,Model model) {
		model.addAttribute("titulo", "Reporte Mensual");
		Map<String, Integer> surveyMap = new LinkedHashMap<>();
		List<LibroDetalle> auxiliar =   libroDetalleService.findAll();
		int auxiliar_bautizo =0 ;
		int auxiliar_confirmacion =0;
		int auxiliar_matrimonio =0;
		if(id==null  || id ==13)
		{
			for (int i =0; i<auxiliar.size();i++)
			{
					if(auxiliar.get(i).isBautizo()==true);
					auxiliar_bautizo+=1;
					if(auxiliar.get(i).isConfirmacion()==true)
						auxiliar_confirmacion +=1;
					if(auxiliar.get(i).isMatrimonio()==true)
						auxiliar_matrimonio+=1;
			}
		
		}
		else{
			for (int i =0; i<auxiliar.size();i++)
			{
				System.out.println(i);
				if(devolverIdMes(auxiliar.get(i).getFechaSacramento())==id)
				{
					if(auxiliar.get(i).isBautizo()==true);
					auxiliar_bautizo+=1;
					if(auxiliar.get(i).isConfirmacion()==true)
						auxiliar_confirmacion +=1;
					if(auxiliar.get(i).isMatrimonio()==true)
						auxiliar_matrimonio+=1;
				}
		}
			
		}
	
		surveyMap.put("Bautizo", auxiliar_bautizo);
		surveyMap.put("Confirmacion", auxiliar_confirmacion);
		surveyMap.put("Matrimonio", auxiliar_matrimonio);
		model.addAttribute("surveyMap", surveyMap);
		return "grafico/reportMes";
	}
	
	
	@RequestMapping(value= {"/reportAno"}, method=RequestMethod.GET)
	public String pieChart(Model model) {
		model.addAttribute("titulo", "Reporte Anual");
		model.addAttribute("bautizo", 50);
		model.addAttribute("confirmacion", 50);
		return "grafico/reportAno";
	}
	
	
	
}
