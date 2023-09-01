package com.guissepe.appEmpleados.services;

import java.util.List;

import com.guissepe.appEmpleados.entity.Departamentos;


public interface DepartamentoService {
	public List<Departamentos> findAll();
	public Departamentos findById(String id);
	public Departamentos save(Departamentos departamento);
	public void delete(String id);
}
