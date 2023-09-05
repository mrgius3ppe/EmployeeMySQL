package com.guissepe.appEmpleados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guissepe.appEmpleados.dao.SalariosDao;
import com.guissepe.appEmpleados.entity.RelationEmpSalario;
import com.guissepe.appEmpleados.entity.Salarios;

@Service
public class SalarioServiceImpl implements SalarioService{

	@Autowired
	SalariosDao salariosDao;
	@Override
	@Transactional(readOnly = true)
	public List<Salarios> findAll() {
		// TODO Auto-generated method stub
		return salariosDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Salarios findById(RelationEmpSalario id) {
		// TODO Auto-generated method stub
		return salariosDao.findById(id).orElse(null);
	}

	@Override
	public Salarios save(Salarios salario) {
		// TODO Auto-generated method stub
		return salariosDao.save(salario);
	}

	@Override
	public void delete(RelationEmpSalario id) {
		// TODO Auto-generated method stub
		salariosDao.deleteById(id);
	}

}
