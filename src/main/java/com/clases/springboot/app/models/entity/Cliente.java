package com.clases.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="cliente")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	

	@Column(name="dni")
	private String dni;	

	@NotEmpty
	@Column(name="nombre")
	private String nombre;	
		
	@NotEmpty
	@Column(name="ape_paterno")
	private String apePaterno;
	
	@NotEmpty
	@Column(name="ape_materno")
	private String apeMaterno;	
	
	@Column(name="fecha_nacimiento")
	private String fechasNacimiento;
	
	@Column(name="sexo")
	private String sexo;
	
	@NotEmpty
	@Column(name="celular1")
	private String celular1;
	
	@Column(name="celular2")
	private String celular2;	

	@Column(name="direccion")
	private String direccion;	
	
	@Email
	@Column(name="correo")
	private String correo;
	
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCelular1() {
		return celular1;
	}

	public void setCelular1(String celular1) {
		this.celular1 = celular1;
	}

	public String getCelular2() {
		return celular2;
	}

	public void setCelular2(String celular2) {
		this.celular2 = celular2;
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
	
}
