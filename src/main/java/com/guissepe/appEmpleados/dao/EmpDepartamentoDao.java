package com.guissepe.appEmpleados.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guissepe.appEmpleados.entity.EmpleadosDepartamento;
import com.guissepe.appEmpleados.entity.RelationEmpDpto;


public interface EmpDepartamentoDao  extends JpaRepository<EmpleadosDepartamento, RelationEmpDpto>{

}
