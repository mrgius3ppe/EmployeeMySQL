package com.guissepe.appEmpleados.services;

import java.util.List;

import com.guissepe.appEmpleados.entity.DepartamentoGerente;
import com.guissepe.appEmpleados.entity.RelationEmpDpto;

public interface DptoGerenteService {
	public List<DepartamentoGerente> findAll();
	public DepartamentoGerente findById(RelationEmpDpto id);
	public DepartamentoGerente save(DepartamentoGerente deptoGerente);
	public void delete(RelationEmpDpto id);
}
