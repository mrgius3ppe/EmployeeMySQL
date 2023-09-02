package com.guissepe.appEmpleados.entity;

import java.io.Serializable;


public class RelationEmpDpto implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long numEmpleado;
	private String numDepartamento;
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
	
	
}
