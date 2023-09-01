package com.guissepe.appEmpleados.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guissepe.appEmpleados.entity.Departamentos;


public interface DepartamentoDao  extends JpaRepository<Departamentos, String>{

}
