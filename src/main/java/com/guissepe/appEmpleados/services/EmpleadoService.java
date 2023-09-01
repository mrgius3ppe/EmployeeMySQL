package com.guissepe.appEmpleados.services;

import java.util.List;

import com.guissepe.appEmpleados.entity.Empleados;

public interface EmpleadoService {
	public List<Empleados> findAll();
	public Empleados findById(Long id);
	public Empleados save(Empleados empleado);
	public void delete(Long id);
}
