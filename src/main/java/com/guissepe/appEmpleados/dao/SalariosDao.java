package com.guissepe.appEmpleados.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guissepe.appEmpleados.entity.RelationEmpSalario;
import com.guissepe.appEmpleados.entity.Salarios;


public interface SalariosDao extends JpaRepository<Salarios, RelationEmpSalario>{

}
