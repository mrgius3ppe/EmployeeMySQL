package com.guissepe.appEmpleados.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;
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
import com.guissepe.appEmpleados.entity.Salarios;
import com.guissepe.appEmpleados.services.SalarioService;

import com.guissepe.appEmpleados.entity.RelationEmpSalario;
@RestController
@RequestMapping("/api")
public class SalarioController {

	@Autowired
	SalarioService salarioService;
	
	@GetMapping("/salarios")
	public List<Salarios> index() {
		return salarioService.findAll();
	}

	@PostMapping("/salarios")
	public ResponseEntity<?> create(@RequestBody Salarios salario, BindingResult result) {

		Salarios salarioNuevo = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			salarioNuevo = salarioService.save(salario);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El Salario ha sido creado con éxito!");
		response.put("salario", salarioNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/salarios/{id}/{fecha}")
	public ResponseEntity<?> update(@RequestBody Salarios salario, BindingResult result,
			@PathVariable Long id,@PathVariable String fecha) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
		Date date=null;
		try {
			String dateInString = fecha;
			 date = formatter.parse(dateInString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		RelationEmpSalario obj= new RelationEmpSalario();
		obj.setNumEmpleado(id);
		obj.setFechaInicio(date);
		Salarios salarioActual = salarioService.findById(obj);
		
	
		Salarios salarioActualizado = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (salarioActual == null) {
			response.put("mensaje", "Error: No se pudo editar, el salario: "
					.concat(id.toString().concat("No existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			salarioActual.setNumEmpleado(salario.getNumEmpleado());
			salarioActual.setFechaFin(salario.getFechaFin());
			salarioActual.setFechaInicio(salario.getFechaInicio());
			salarioActual.setSalario(salario.getSalario());
			salarioActualizado = salarioService.save(salarioActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el salario en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El salario ha sido actualizado con éxito!");
		response.put("salario", salarioActualizado);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/salarios/{id}/{fecha}")
	public ResponseEntity<?> delete(@PathVariable Long id,@PathVariable String fecha) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
		Date date=null;
		try {
			String dateInString = fecha;
			 date = formatter.parse(dateInString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String, Object> response = new HashMap<>();
		RelationEmpSalario obj= new RelationEmpSalario();
		obj.setNumEmpleado(id);
		obj.setFechaInicio(date);
		try {
			salarioService.delete(obj);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el salario de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El salario eliminado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
}
