package com.guissepe.appEmpleados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guissepe.appEmpleados.dao.EmpDepartamentoDao;
import com.guissepe.appEmpleados.entity.EmpleadosDepartamento;
import com.guissepe.appEmpleados.entity.RelationEmpDpto;

@Service
public class EmpDeptoServiceImpl implements EmpDepartamentoService {

	@Autowired
	private EmpDepartamentoDao empDepartamentoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<EmpleadosDepartamento> findAll() {
		// TODO Auto-generated method stub
		return empDepartamentoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public EmpleadosDepartamento findById(RelationEmpDpto id) {
		// TODO Auto-generated method stub
		return empDepartamentoDao.findById(id).orElse(null);
	}

	@Override
	public EmpleadosDepartamento save(EmpleadosDepartamento empDpto) {
		// TODO Auto-generated method stub
		return empDepartamentoDao.save(empDpto);
	}

	@Override
	public void delete(RelationEmpDpto id) {
		empDepartamentoDao.deleteById(id);

	}

}
