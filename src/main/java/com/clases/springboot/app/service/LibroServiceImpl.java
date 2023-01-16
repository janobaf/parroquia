package com.clases.springboot.app.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clases.springboot.app.models.dao.ILibroDao;
import com.clases.springboot.app.models.dao.LibroRepository;
import com.clases.springboot.app.models.entity.Libro;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
@Service
public class LibroServiceImpl implements ILibroService {
	
	@Autowired
	private LibroRepository repository;
	
	private String tempPath = System.getProperty("java.io.tmpdir");

	@Autowired
	private ILibroDao libroDao;
	@Transactional (readOnly= true)
	@Override
	public List<Libro> findAll() {
		return (List<Libro>)libroDao.findAll();
	}

	@Transactional (readOnly= true)
	@Override
	public Libro findById(Long id) {
		return libroDao.findById(id).orElse(null);
	}
	
	@Transactional
	@Override
	public void deleteById(Long id) {
		libroDao.deleteById(id);		
	}

	@Transactional
	@Override
	public void save(Libro libro) {
		libroDao.save(libro);		
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Libro> buscarPorLibro(String numTomo) {
		return (List<Libro>) libroDao.buscarPorLibro(numTomo);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Libro> buscarLibroNombre(String tipoSacramento) {
		return (List<Libro>) libroDao.buscarPorLibro(tipoSacramento);
	}
	
	public String exportReport(String reportFormat) throws Exception {	
		List<Libro> libros= repository.findAll();
		
		//File file = ResourceUtils.getFile("classpath:libros.jrxml");
		InputStream file = getTemplate();
		JasperReport jasperReport = JasperCompileManager.compileReport(file);
		List<Map<String,Object>> mapDataSource = new ArrayList<>();
		for(Libro libro : libros) {
			Map<String,Object> obj = new HashMap<>();
			obj.put("id", libro.getId().toString());
			obj.put("fecha_pertura", libro.getFechaApertura());
			obj.put("fecha_cierre", libro.getFechaCierre());
			obj.put("num_pags", libro.getNumPaginas());
			obj.put("num_tomo", libro.getNumTomo());
			obj.put("tipo_sacramento", libro.getTipoSacramento());
			mapDataSource.add(obj);
		}
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(mapDataSource);

		java.util.Map<String, Object> parameters = new HashMap<>();
		parameters.put("parametro1", "Lista de Libros");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
		
		String pathFile = getFormatExport(jasperPrint, reportFormat, "libros"+"."+reportFormat);
		String archivoCodificado = convertBytes(pathFile);
		deleteFile(pathFile);
		

		return archivoCodificado;
	}

	private void deleteFile(String pathFile) throws Exception {
		// TODO Auto-generated method stub
		File file = new File(pathFile);

		if (!file.delete()) {
			throw new Exception("Ocurrió un problema al eliminar el archivo");
		}
	}

	private String convertBytes(String pathFile) throws IOException {
		byte[] input_file = Files.readAllBytes(Paths.get(pathFile));
		byte[] encodedBytes = Base64.getEncoder().encode(input_file);
		String encodedString = new String(encodedBytes);

		return encodedString;
	}

	@Override
	public String base64Libros(String format) throws FileNotFoundException, JRException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private InputStream getTemplate() {
		InputStream file;
		file=getClass().getResourceAsStream("/static/Report/libros.jrxml");
		return file;
	}
	
	private String getFormatExport(JasperPrint jasperPrint, String format, String filename) throws JRException {
		String path="";
		path = tempPath + "/" + filename;
		path = replacePaths(path);
		if(format.contentEquals("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, path);
			
		}
		if(format.contentEquals("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, path);
		}
		return path;
	}

	private String replacePaths(String path) {
		return path.replace("\\","\\\\");
	}
	
}
