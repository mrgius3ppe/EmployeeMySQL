package com.guissepe.appEmpleados.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guissepe.appEmpleados.entity.DepartamentoGerente;
import com.guissepe.appEmpleados.entity.RelationEmpDpto;

public interface DepartamentoGerenteDao extends JpaRepository<DepartamentoGerente, RelationEmpDpto>{

}
