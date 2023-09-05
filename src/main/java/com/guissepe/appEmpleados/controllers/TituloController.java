package com.guissepe.appEmpleados.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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

import com.guissepe.appEmpleados.entity.Titulos;
import com.guissepe.appEmpleados.services.TituloService;
import com.guissepe.appEmpleados.entity.RelationEmpTitulo;

@RestController
@RequestMapping("/api")
public class TituloController {

	@Autowired
	TituloService tituloService;
	
	@GetMapping("/titulos")
	public List<Titulos> index() {
		return tituloService.findAll();
	}
	@PostMapping("/titulos")
	public ResponseEntity<?> create(@RequestBody Titulos titulo, BindingResult result) {

		Titulos tituloNuevo = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			tituloNuevo = tituloService.save(titulo);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Los Titulos ha sido creado con éxito!");
		response.put("titulo", tituloNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/titulo/{id}")
	public ResponseEntity<?> update(@RequestBody Titulos titulo, BindingResult result,
			@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		
		Titulos tituloFiltro=tituloService.searchId(id);
		if (tituloFiltro == null) {
			response.put("mensaje", "Error: No se pudo buscar el Filtro, el titulo: "
					.concat(id.toString().concat("No existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		
		RelationEmpTitulo obj= new RelationEmpTitulo();
		obj.setNumEmpleado(tituloFiltro.getNumEmpleado());
		obj.setFechaInicio(tituloFiltro.getFechaInicio());
		obj.setTitulo(tituloFiltro.getTitulo());
		Titulos tituloActual = tituloService.findById(obj);
		Titulos tituloActualizado = null;
		

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (tituloActual == null) {
			response.put("mensaje", "Error: No se pudo editar, el titulo: "
					.concat(id.toString().concat("No existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			tituloActual.setFechaFin(titulo.getFechaFin());
			tituloActual.setFechaInicio(titulo.getFechaInicio());
			//tituloActual.setTitulo(titulo.getTitulo());
			tituloActualizado = tituloService.save(tituloActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el titulo en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El titulo ha sido actualizado con éxito!");
		response.put("titulo", tituloActualizado);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/titulo/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Titulos tituloFiltro=tituloService.searchId(id);
		if (tituloFiltro == null) {
			response.put("mensaje", "Error: No se pudo buscar el Filtro, el titulo: "
					.concat(id.toString().concat("No existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		
		
		RelationEmpTitulo obj= new RelationEmpTitulo();
		obj.setNumEmpleado(tituloFiltro.getNumEmpleado());
		obj.setFechaInicio(tituloFiltro.getFechaInicio());
		obj.setTitulo(tituloFiltro.getTitulo());
		try {
			
			tituloService.delete(obj);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el titulo de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El titulo eliminado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
}
