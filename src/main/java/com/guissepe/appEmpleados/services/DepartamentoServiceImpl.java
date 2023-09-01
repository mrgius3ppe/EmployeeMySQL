package com.guissepe.appEmpleados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guissepe.appEmpleados.dao.DepartamentoDao;
import com.guissepe.appEmpleados.entity.Departamentos;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

	@Autowired
	DepartamentoDao departamentoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Departamentos> findAll() {
		// TODO Auto-generated method stub
		return departamentoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Departamentos findById(String id) {
		// TODO Auto-generated method stub
		return departamentoDao.findById(id).orElse(null);
	}

	@Override
	public Departamentos save(Departamentos departamento) {
		// TODO Auto-generated method stub
		return departamentoDao.save(departamento);
	}

	@Override
	public void delete(String id) {
		departamentoDao.deleteById(id);

	}

}
