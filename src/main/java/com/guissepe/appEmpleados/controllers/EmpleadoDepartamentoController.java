package com.guissepe.appEmpleados.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guissepe.appEmpleados.dao.EmpDepartamentoDao;
import com.guissepe.appEmpleados.entity.EmpleadosDepartamento;
import com.guissepe.appEmpleados.entity.RelationEmpDpto;

@RestController
@RequestMapping("/api")
public class EmpleadoDepartamentoController {

	@Autowired
	private EmpDepartamentoDao empDepartamentoDao;
	
	@GetMapping("/empdpto")
	public List<EmpleadosDepartamento> index() {
		return empDepartamentoDao.findAll();
	}
	
	@PostMapping("/empdpto")
	public ResponseEntity<?> create(@RequestBody EmpleadosDepartamento empdpto, BindingResult result) {

		EmpleadosDepartamento empdptoNuevo = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			empdptoNuevo = empDepartamentoDao.save(empdpto);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Relacion Empleado Departamento ha sido creado con éxito!");
		response.put("empdpto", empdptoNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	@PutMapping("/empdpto/{numEmpleado}/{dpto}")
	public ResponseEntity<?> update(@RequestBody EmpleadosDepartamento empdpto, BindingResult result,
			@PathVariable Long numEmpleado,@PathVariable String dpto) {

		RelationEmpDpto obj= new RelationEmpDpto();
		obj.setNumEmpleado(numEmpleado);
		obj.setNumDepartamento(dpto);
		EmpleadosDepartamento empdptoActual = empDepartamentoDao.findById(obj).orElse(null);

		EmpleadosDepartamento empleadoActualizado = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (empdptoActual == null) {
			response.put("mensaje", "Error: No se pudo editar, el empdpto: "
					.concat(numEmpleado.toString().concat("No existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			empdptoActual.setNumEmpleado(empdpto.getNumEmpleado());
			empdptoActual.setNumDepartamento(empdpto.getNumDepartamento());
			empdptoActual.setFechaInicio(empdpto.getFechaInicio());
			empdptoActual.setFechaFin(empdpto.getFechaFin());
			empleadoActualizado = empDepartamentoDao.save(empdptoActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el empleado en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El empdpto ha sido actualizado con éxito!");
		response.put("empdpto", empleadoActualizado);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/empdpto/{id}/{dpto}")
	public ResponseEntity<?> delete(@PathVariable Long id,@PathVariable String dpto) {
		RelationEmpDpto obj= new RelationEmpDpto();
		obj.setNumEmpleado(id);
		obj.setNumDepartamento(dpto);
		Map<String, Object> response = new HashMap<>();
		
		try {
			empDepartamentoDao.deleteById(obj);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el empdpto de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El empdpto eliminado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
}
