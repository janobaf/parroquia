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
@Table(name="acta")
public class Acta implements Serializable{

	private static final long serialVersionUID =1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="numero")
	private String numero;
	
	@ManyToOne
	@JoinColumn(name = "libroDetalle_id", nullable = false)
	private LibroDetalle libroDetalle;
		
	@ManyToOne
	@JoinColumn(name = "empleado_id", nullable = false)
	private Empleado empleadoId;
	
	@Column(name="fecha_emicion")
	private String fechaEmicion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}


	public Empleado getEmpleadoId() {
		return empleadoId;
	}

	public void setEmpleadoId(Empleado empleadoId) {
		this.empleadoId = empleadoId;
	}

	public String getFechaEmicion() {
		return fechaEmicion;
	}

	public void setFechaEmicion(String fechaEmicion) {
		this.fechaEmicion = fechaEmicion;
	}
	
	
	public LibroDetalle getLibroDetalle() {
		return libroDetalle;
	}

	public void setLibroDetalle(LibroDetalle libroDetalle) {
		this.libroDetalle = libroDetalle;
	}
	
}
