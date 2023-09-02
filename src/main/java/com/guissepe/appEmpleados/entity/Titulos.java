package com.guissepe.appEmpleados.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="titulos")
public class Titulos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long numEmpleado;
	@Column(length = 50,nullable = true)
	@Id
	private String titulo;
	@Id
	private Date fechaInicio;
	private Date fechaFin;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "numEmpleado", insertable = false,updatable = false)
	@JsonProperty(access = Access.WRITE_ONLY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Empleados empleado;
	
	public Long getNumEmpleado() {
		return numEmpleado;
	}

	public void setNumEmpleado(Long numEmpleado) {
		this.numEmpleado = numEmpleado;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Empleados getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleados empleado) {
		this.empleado = empleado;
	}


	

}
