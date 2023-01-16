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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="empleado")
public class Empleado implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="dni")
	private String dni;
	
	@NotEmpty
	@Column(name="nombres")
	private String nombres;
			
	@NotEmpty
	@Column(name="ape_paterno")
	private String apePaterno;
	
	@NotEmpty
	@Column(name="ape_materno")
	private String apeMaterno;	
	
	@ManyToOne
	@JoinColumn(name = "cargo_id", nullable = false)
	private Cargo cargoId;		

	@Column(name="fecha_nacimiento")
	private String fechasNacimiento;	

	@NotEmpty
	@Column(name="celular")
	private String celular;
	
	@Column(name="direccion")
	private String direccion;
		
	@Email
	@Column(name="correo")
	private String correo;
	
	@Column(name="estado")
	private String estado;
	
	@Column(name="detalle")
	private String detalle;
	
	@Column(name="fecha_inicio")
	private String fechaInicio;
	
	@Column(name="fecha_baja")
	private String fechaBaja;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApePaterno() {
		return apePaterno;
	}

	public void setApePaterno(String apePaterno) {
		this.apePaterno = apePaterno;
	}

	public String getApeMaterno() {
		return apeMaterno;
	}

	public void setApeMaterno(String apeMaterno) {
		this.apeMaterno = apeMaterno;
	}

	

	public String getFechasNacimiento() {
		return fechasNacimiento;
	}

	public void setFechasNacimiento(String fechasNacimiento) {
		this.fechasNacimiento = fechasNacimiento;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	
	public Cargo getCargoId() {
		return cargoId;
	}

	public void setCargoId(Cargo cargoId) {
		this.cargoId = cargoId;
	}

	
}
