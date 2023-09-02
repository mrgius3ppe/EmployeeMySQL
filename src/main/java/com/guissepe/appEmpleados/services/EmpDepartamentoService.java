package com.guissepe.appEmpleados.services;

import java.util.List;

import com.guissepe.appEmpleados.entity.EmpleadosDepartamento;
import com.guissepe.appEmpleados.entity.RelationEmpDpto;


public interface EmpDepartamentoService {
	public List<EmpleadosDepartamento> findAll();
	public EmpleadosDepartamento findById(RelationEmpDpto id);
	public EmpleadosDepartamento save(EmpleadosDepartamento empDpto);
	public void delete(RelationEmpDpto id);
}
