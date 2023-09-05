package com.guissepe.appEmpleados.services;

import java.util.List;

import com.guissepe.appEmpleados.entity.RelationEmpTitulo;
import com.guissepe.appEmpleados.entity.Titulos;


public interface TituloService {
	public List<Titulos> findAll();
	public Titulos findById(RelationEmpTitulo id);
	public Titulos save(Titulos titulo);
	public void delete(RelationEmpTitulo id);
	public Titulos searchId(Long id);
}
