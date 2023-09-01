package com.guissepe.appEmpleados.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guissepe.appEmpleados.entity.Empleados;


public interface EmpleadoDao extends JpaRepository<Empleados, Long>{

}
