package com.guissepe.appEmpleados.entity;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="dept_manager")
@IdClass(RelationEmpDpto.class)//se agrega porque tiene llaves compuestas en la relacion 2 id
public class DepartamentoGerente implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(length = 4,nullable = true)
	private String numDepartamento;
	@Id
	private Long numEmpleado;
	
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	@Temporal(TemporalType.DATE)
	private Date fechaFinal;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "numEmpleado", insertable = false,updatable = false)
	@JsonProperty(access = Access.WRITE_ONLY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Empleados empleado;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "numDepartamento", insertable = false,updatable = false)
	@JsonProperty(access = Access.WRITE_ONLY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Departamentos departamento;

	public String getNumDepartamento() {
		return numDepartamento;
	}

	public void setNumDepartamento(String numDepartamento) {
		this.numDepartamento = numDepartamento;
	}

	public Long getNumEmpleado() {
		return numEmpleado;
	}

	public void setNumEmpleado(Long numEmpleado) {
		this.numEmpleado = numEmpleado;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Empleados getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleados empleado) {
		this.empleado = empleado;
	}

	public Departamentos getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamentos departamento) {
		this.departamento = departamento;
	}

	
	
	
}
