package com.guissepe.appEmpleados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guissepe.appEmpleados.dao.DepartamentoGerenteDao;
import com.guissepe.appEmpleados.entity.DepartamentoGerente;
import com.guissepe.appEmpleados.entity.RelationEmpDpto;

@Service
public class DptoGerenteServiceImpl implements DptoGerenteService{

	@Autowired
	DepartamentoGerenteDao departamentoGerenteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<DepartamentoGerente> findAll() {
		// TODO Auto-generated method stub
		return departamentoGerenteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public DepartamentoGerente findById(RelationEmpDpto id) {
		// TODO Auto-generated method stub
		return departamentoGerenteDao.findById(id).orElse(null);
	}

	@Override
	public DepartamentoGerente save(DepartamentoGerente deptoGerente) {
		// TODO Auto-generated method stub
		return departamentoGerenteDao.save(deptoGerente);
	}

	@Override
	public void delete(RelationEmpDpto id) {
		departamentoGerenteDao.deleteById(id);
		
	}

}
