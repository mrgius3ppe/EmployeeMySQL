package com.guissepe.appEmpleados.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.guissepe.appEmpleados.entity.RelationEmpTitulo;
import com.guissepe.appEmpleados.entity.Titulos;
import com.guissepe.appEmpleados.utils.Querys;


public interface TituloDao extends JpaRepository<Titulos, RelationEmpTitulo>{

	@Query( value =Querys.FILTRO_NUM_EMPLEADO_TITULOS,nativeQuery = true)
	Titulos FILTRO_EMPLEADO_TITULOS(@Param("num_empleado") Long num_empleado);
}

