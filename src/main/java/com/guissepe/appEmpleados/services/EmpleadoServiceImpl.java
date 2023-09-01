package com.guissepe.appEmpleados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guissepe.appEmpleados.dao.EmpleadoDao;
import com.guissepe.appEmpleados.entity.Empleados;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private EmpleadoDao empleadoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Empleados> findAll() {
		// TODO Auto-generated method stub
		return empleadoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Empleados findById(Long id) {
		// TODO Auto-generated method stub
		return empleadoDao.findById(id).orElse(null);
	}

	@Override
	public Empleados save(Empleados empleado) {
		// TODO Auto-generated method stub
		return empleadoDao.save(empleado);
	}

	@Override
	public void delete(Long id) {
		empleadoDao.deleteById(id);

	}

}
