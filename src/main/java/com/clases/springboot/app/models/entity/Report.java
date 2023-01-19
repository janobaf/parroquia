package com.clases.springboot.app.models.entity;

public class Report {
	private String id;
	
	private String documento;
	private String correlativo;
	private String serie;
	private String pdfBase;
	private int cantidadFilas;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getPdfBase() {
		return pdfBase;
	}
	public void setPdfBase(String pdfBase) {
		this.pdfBase = pdfBase;
	}
	public int getCantidadFilas() {
		return cantidadFilas;
	}
	public void setCantidadFilas(int cantidadFilas) {
		this.cantidadFilas = cantidadFilas;
	}
	
	
}
