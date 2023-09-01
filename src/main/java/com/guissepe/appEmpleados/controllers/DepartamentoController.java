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

import com.guissepe.appEmpleados.entity.Departamentos;
import com.guissepe.appEmpleados.services.DepartamentoService;

@RestController
@RequestMapping("/api")
public class DepartamentoController {

	@Autowired
	DepartamentoService departamentoService;

	@GetMapping("/departamentos")
	public List<Departamentos> index() {
		return departamentoService.findAll();
	}

	@PostMapping("/departamentos")
	public ResponseEntity<?> create(@RequestBody Departamentos departamento, BindingResult result) {

		Departamentos departamentoNuevo = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			departamentoNuevo = departamentoService.save(departamento);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El Departamento ha sido creado con éxito!");
		response.put("departamento", departamentoNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/departamentos/{id}")
	public ResponseEntity<?> update(@RequestBody Departamentos departamento, BindingResult result, @PathVariable String id) {

		Departamentos departamentoActual = departamentoService.findById(id);

		Departamentos departamentoActualizado = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (departamentoActual == null) {
			response.put("mensaje", "Error: No se pudo editar, el departamento: "
					.concat(id.toString().concat("No existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			departamentoActual.setNumDepartamento(departamento.getNumDepartamento());
			departamentoActual.setNombreDepartamento(departamento.getNombreDepartamento());
			departamentoActualizado = departamentoService.save(departamentoActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el departamento en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El departamento ha sido actualizado con éxito!");
		response.put("departamento", departamentoActualizado);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/departamentos/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {

		Map<String, Object> response = new HashMap<>();

		try {
			departamentoService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el departamento de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El departamento eliminado con éxito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
