package com.guissepe.appEmpleados.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="departamentos")
public class Departamentos implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(length = 4)
	private String numDepartamento;
	@Column(unique = true, length = 40)
	private String nombreDepartamento;
	
	@OneToMany(mappedBy = "departamento",cascade = CascadeType.ALL)
	private Set<EmpleadosDepartamento> empDep= new HashSet<>();
	
	@OneToMany(mappedBy = "departamento",cascade = CascadeType.ALL)
	private Set<DepartamentoGerente> depGer= new HashSet<>();
	
	
	public String getNumDepartamento() {
		return numDepartamento;
	}
	public void setNumDepartamento(String numDepartamento) {
		this.numDepartamento = numDepartamento;
	}
	public String getNombreDepartamento() {
		return nombreDepartamento;
	}
	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}
	public Set<EmpleadosDepartamento> getEmpDep() {
		return empDep;
	}
	public void setEmpDep(Set<EmpleadosDepartamento> empDep) {
		this.empDep = empDep;
	}
	public Set<DepartamentoGerente> getDepGer() {
		return depGer;
	}
	public void setDepGer(Set<DepartamentoGerente> depGer) {
		this.depGer = depGer;
	}
	
	
	
	
}
