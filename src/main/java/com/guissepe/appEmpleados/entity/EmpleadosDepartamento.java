package com.guissepe.appEmpleados.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="dept_emp")
public class EmpleadosDepartamento implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private Long numEmpleado;
	@Id
	private String numDepartamento;
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	@Temporal(TemporalType.DATE)
	private Date fechaFin;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "empleado_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Empleados empleado;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "departamento_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Departamentos departamento;
	

	public Long getNumEmpleado() {
		return numEmpleado;
	}
	public void setNumEmpleado(Long numEmpleado) {
		this.numEmpleado = numEmpleado;
	}
	public String getNumDepartamento() {
		return numDepartamento;
	}
	public void setNumDepartamento(String numDepartamento) {
		this.numDepartamento = numDepartamento;
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
	public Departamentos getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamentos departamento) {
		this.departamento = departamento;
	}


	
}
