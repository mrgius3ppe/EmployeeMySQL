package com.guissepe.appEmpleados.entity;

import java.io.Serializable;
import java.util.Date;

public class RelationEmpSalario implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long numEmpleado;
	private Date fechaInicio;
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
	
}
