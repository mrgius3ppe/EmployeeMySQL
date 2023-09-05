package com.guissepe.appEmpleados.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guissepe.appEmpleados.dao.DepartamentoGerenteDao;
import com.guissepe.appEmpleados.entity.DepartamentoGerente;
import com.guissepe.appEmpleados.entity.RelationEmpDpto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
@RestController
@RequestMapping("/api")
public class DptoGerenteController {
	
	@Autowired
	DepartamentoGerenteDao departamentoGerenteDao;
	
	@GetMapping("/dptoGerente")
	public List<DepartamentoGerente> index() {
		return departamentoGerenteDao.findAll();
	}
	
	@PostMapping("/dptoGerente")
	public ResponseEntity<?> create(@RequestBody DepartamentoGerente dtpoGerente, BindingResult result) {

		DepartamentoGerente deptoGerente = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			deptoGerente = departamentoGerenteDao.save(dtpoGerente);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Relacion Departamento Gerente ha sido creado con éxito!");
		response.put("dptoger", deptoGerente);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	@PutMapping("/dptoGerente/{numEmpleado}/{dpto}")
	public ResponseEntity<?> update(@RequestBody DepartamentoGerente dptoGer, BindingResult result,
			@PathVariable Long numEmpleado,@PathVariable String dpto) {

		RelationEmpDpto obj= new RelationEmpDpto();
		obj.setNumEmpleado(numEmpleado);
		obj.setNumDepartamento(dpto);
		DepartamentoGerente dptoGerActual = departamentoGerenteDao.findById(obj).orElse(null);

		DepartamentoGerente dptoGerente = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (dptoGerActual == null) {
			response.put("mensaje", "Error: No se pudo editar, el empdpto: "
					.concat(numEmpleado.toString().concat("No existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			dptoGerActual.setNumEmpleado(dptoGer.getNumEmpleado());
			dptoGerActual.setNumDepartamento(dptoGer.getNumDepartamento());
			dptoGerActual.setFechaInicio(dptoGer.getFechaInicio());
			dptoGerActual.setFechaFinal(dptoGer.getFechaFinal());
			dptoGerente = departamentoGerenteDao.save(dptoGerActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el empleado en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El empdpto ha sido actualizado con éxito!");
		response.put("empdpto", dptoGerente);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/dptoGerente/{id}/{dpto}")
	public ResponseEntity<?> delete(@PathVariable Long id,@PathVariable String dpto) {
		RelationEmpDpto obj= new RelationEmpDpto();
		obj.setNumEmpleado(id);
		obj.setNumDepartamento(dpto);
		Map<String, Object> response = new HashMap<>();
		
		try {
			boolean exists = departamentoGerenteDao.existsById(obj);
			if(exists) {
				departamentoGerenteDao.deleteById(obj);
			}else {
				response.put("mensaje", "No existe el dpto gerente en la base de datos.");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el dpto gerente de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El Dpto Gerente eliminado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
