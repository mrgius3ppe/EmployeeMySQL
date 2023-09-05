package com.guissepe.appEmpleados.services;

import java.util.List;

import com.guissepe.appEmpleados.entity.RelationEmpSalario;
import com.guissepe.appEmpleados.entity.Salarios;

public interface SalarioService {
	public List<Salarios> findAll();
	public Salarios findById(RelationEmpSalario id);
	public Salarios save(Salarios salario);
	public void delete(RelationEmpSalario id);
}
