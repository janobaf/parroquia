package com.clases.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="libro_detalle")
public class LibroDetalle implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "cliente", nullable = false)
	private Cliente idCliente;
	
	@Column(name="fecha_sacramento")
	private String fechaSacramento;
	
	@ManyToOne
	@JoinColumn(name = "parroco", nullable = false)
	private Empleado parroco;		
	
	@ManyToOne
	@JoinColumn(name = "libro", nullable = false)
	private Libro idLibro;	
	
	@Column(name="num_pag")
	private String numPag;
	
	@Column(name="num_registro")
	private String numRegistro;	

	@Column(name="esposo")
	private String esposo;	
	
	@Column(name="esposa")
	private String esposa;
	
	@Column(name="padrino")
	private String padrino;
	
	@Column(name="madrina")
	private String madrina;
	
	@Column(name="testigo1")
	private String testigo1;
	
	@Column(name="testigo2")
	private String testigo2;
	
	@Column(name="testigo3")
	private String testigo3;
	
	@Column(name="testigo4")
	private String testigo4;
	
	@Column(name="bautizo")
	private boolean bautizo;
	
	@Column(name="confirmacion")
	private boolean confirmacion;	
	
	@Column(name="matrimonio")
	private boolean matrimonio;	
	
	@Column(name="observacion")
	private String observacion;
	
	
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}

	public String getFechaSacramento() {
		return fechaSacramento;
	}

	public void setFechaSacramento(String fechaSacramento) {
		this.fechaSacramento = fechaSacramento;
	}

	public Empleado getParroco() {
		return parroco;
	}

	public void setParroco(Empleado parroco) {
		this.parroco = parroco;
	}
	
	public Libro getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(Libro idLibro) {
		this.idLibro = idLibro;
	}
	
	public String getNumPag() {
		return numPag;
	}

	public void setNumPag(String numPag) {
		this.numPag = numPag;
	}

	public String getNumRegistro() {
		return numRegistro;
	}

	public void setNumRegistro(String numRegistro) {
		this.numRegistro = numRegistro;
	}


	public String getEsposo() {
		return esposo;
	}

	public void setEsposo(String esposo) {
		this.esposo = esposo;
	}

	public String getEsposa() {
		return esposa;
	}

	public void setEsposa(String esposa) {
		this.esposa = esposa;
	}

	public String getPadrino() {
		return padrino;
	}

	public void setPadrino(String padrino) {
		this.padrino = padrino;
	}

	public String getMadrina() {
		return madrina;
	}

	public void setMadrina(String madrina) {
		this.madrina = madrina;
	}

	public String getTestigo1() {
		return testigo1;
	}

	public void setTestigo1(String testigo1) {
		this.testigo1 = testigo1;
	}

	public String getTestigo2() {
		return testigo2;
	}

	public void setTestigo2(String testigo2) {
		this.testigo2 = testigo2;
	}

	public String getTestigo3() {
		return testigo3;
	}

	public void setTestigo3(String testigo3) {
		this.testigo3 = testigo3;
	}

	public String getTestigo4() {
		return testigo4;
	}

	public void setTestigo4(String testigo4) {
		this.testigo4 = testigo4;
	}

	public boolean isBautizo() {
		return bautizo;
	}

	public void setBautizo(boolean bautizo) {
		this.bautizo = bautizo;
	}

	public boolean isConfirmacion() {
		return confirmacion;
	}

	public void setConfirmacion(boolean confirmacion) {
		this.confirmacion = confirmacion;
	}

	public boolean isMatrimonio() {
		return matrimonio;
	}

	public void setMatrimonio(boolean matrimonio) {
		this.matrimonio = matrimonio;
	}

		public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	
}
