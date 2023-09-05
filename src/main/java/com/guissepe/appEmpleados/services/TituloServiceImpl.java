package com.guissepe.appEmpleados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guissepe.appEmpleados.dao.TituloDao;
import com.guissepe.appEmpleados.entity.RelationEmpTitulo;
import com.guissepe.appEmpleados.entity.Titulos;

@Service
public class TituloServiceImpl implements TituloService {

	@Autowired
	TituloDao tituloDao;
	@Override
	@Transactional(readOnly = true)
	public List<Titulos> findAll() {
		// TODO Auto-generated method stub
		return tituloDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Titulos findById(RelationEmpTitulo id) {
		// TODO Auto-generated method stub
		return tituloDao.findById(id).orElse(null);
	}

	@Override
	public Titulos save(Titulos titulo) {
		// TODO Auto-generated method stub
		return tituloDao.save(titulo);
	}

	@Override
	public void delete(RelationEmpTitulo id) {
		// TODO Auto-generated method stub
		tituloDao.deleteById(id);
	}

	@Override
	public Titulos searchId(Long id) {
		// TODO Auto-generated method stub
		return tituloDao.FILTRO_EMPLEADO_TITULOS(id);
	}

}
