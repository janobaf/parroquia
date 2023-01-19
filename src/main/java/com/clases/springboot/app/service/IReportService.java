package com.clases.springboot.app.service;

import java.io.IOException;
import java.text.ParseException;

import com.clases.springboot.app.models.entity.Report;
import com.itextpdf.text.DocumentException;

import net.sf.jasperreports.engine.JRException;

public interface IReportService {
	
	public Report reportePrueba()throws Exception,  JRException, IOException, ParseException, DocumentException;;

	
	
}
