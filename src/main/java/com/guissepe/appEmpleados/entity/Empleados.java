package com.guissepe.appEmpleados.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="empleados")
public class Empleados implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numEmpleado;
	//@Column(unique = true,  name = "user",length = 20)
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	@Column(length = 20,nullable = true)
	private String nombre;
	@Column(length = 20,nullable = true)
	private String apellidoPaterno;
	@Column(length = 20,nullable = true)
	private String apellidoMaterno;
	@Column(length = 1,nullable = true)
	private String sexo;
	@Temporal(TemporalType.DATE)
	private Date fechaContratacion;
	
	@OneToMany(mappedBy = "empleado",cascade = CascadeType.ALL)
	private Set<EmpleadosDepartamento> empDep= new HashSet<>();
	
	@OneToMany(mappedBy = "empleado",cascade = CascadeType.ALL)
	private Set<DepartamentoGerente> depGer= new HashSet<>();
	
	@OneToMany(mappedBy = "empleado",cascade = CascadeType.ALL)
	private Set<Salarios> salario= new HashSet<>();
	
	@OneToMany(mappedBy = "empleado",cascade = CascadeType.ALL)
	private Set<Titulos> titulos= new HashSet<>();
	
	
	public Long getNumEmpleado() {
		return numEmpleado;
	}
	public void setNumEmpleado(Long numEmpleado) {
		this.numEmpleado = numEmpleado;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Date getFechaContratacion() {
		return fechaContratacion;
	}
	public void setFechaContratacion(Date fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
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
	public Set<Salarios> getSalario() {
		return salario;
	}
	public void setSalario(Set<Salarios> salario) {
		this.salario = salario;
	}
	public Set<Titulos> getTitulos() {
		return titulos;
	}
	public void setTitulos(Set<Titulos> titulos) {
		this.titulos = titulos;
	}

	

}
