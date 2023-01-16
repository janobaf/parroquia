package com.clases.springboot.app.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clases.springboot.app.models.dao.IClienteDao;
import com.clases.springboot.app.models.dao.ClienteRepository;
import com.clases.springboot.app.models.entity.Cliente;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private ClienteRepository repository;
	
	private String tempPath = System.getProperty("java.io.tmpdir");
	
	@Transactional(readOnly= true)
	@Override
	public List<Cliente> findAll() {
		return (List<Cliente>)clienteDao.findAll();
	}

	@Transactional(readOnly= true)
	@Override
	public Cliente findById(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void save(Cliente cliente) {
		clienteDao.save(cliente);
		
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Cliente> buscarPorDNI(String DNI) {
		return (List<Cliente>) clienteDao.buscarPorDNI(DNI);
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		clienteDao.deleteById(id);
		
	}
	
	public String exportReport(String reportFormat) throws Exception {	
		List<Cliente> clientes= repository.findAll();
		
		//File file = ResourceUtils.getFile("classpath:libros.jrxml");
		InputStream file = getTemplate();
		JasperReport jasperReport = JasperCompileManager.compileReport(file);
		List<Map<String,Object>> mapDataSource = new ArrayList<>();
		for(Cliente cliente : clientes) {
			Map<String,Object> obj = new HashMap<>();
			obj.put("id", cliente.getId());
			obj.put("dni", cliente.getDni());
			obj.put("ape_paterno", cliente.getApePaterno());
			obj.put("ape_materno", cliente.getApeMaterno());
			obj.put("nombre", cliente.getNombre());
			obj.put("sexo", cliente.getSexo());
			obj.put("fecha_nacimiento", cliente.getFechasNacimiento());
			obj.put("celular1", cliente.getCelular1());
			obj.put("correo", cliente.getCorreo());
			obj.put("direccion", cliente.getDireccion());
			mapDataSource.add(obj);
		}
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(mapDataSource);

		java.util.Map<String, Object> parameters = new HashMap<>();
		parameters.put("parametro1", "Lista de Clientes");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
		
		String pathFile = getFormatExport(jasperPrint, reportFormat, "clientes"+"."+reportFormat);
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
	public String base64Clientes(String format) throws FileNotFoundException, JRException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private InputStream getTemplate() {
		InputStream file;
		file=getClass().getResourceAsStream("/static/Report/listarClientes.jrxml");
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
