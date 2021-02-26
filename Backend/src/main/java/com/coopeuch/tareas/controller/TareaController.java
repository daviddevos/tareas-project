package com.coopeuch.tareas.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coopeuch.tareas.dto.TareaDTO;
import com.coopeuch.tareas.service.custom.ITareaService;
import com.coopeuch.tareas.utils.Constantes;

@Validated
@RestController
@RequestMapping(path = "/tareas")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, 
				RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class TareaController {

	private HashMap<String, Object> jsonResponse;
	private String messageResponse;
	private HttpStatus statusResponse;
	private final ITareaService service;

	public TareaController(ITareaService service) {
		this.service = service;
	}
	
	@GetMapping("/todas")
	public List<TareaDTO> findAll() {
		return service.findAll();
	}
	
	@PostMapping("/actualizarLista")
	public Object updateTareas(@RequestBody @Valid List<TareaDTO> newTareas) {
		try {
			List<TareaDTO> updatedTareas = service.actualizarTareas(newTareas);
			statusResponse = HttpStatus.CREATED;
			return new ResponseEntity<List<TareaDTO>>(updatedTareas, statusResponse);
		} catch (ConstraintViolationException constraintException) {
			messageResponse = constraintException.getCause().toString();
			statusResponse = HttpStatus.BAD_REQUEST;
		} catch (DataIntegrityViolationException dataIntegrityViolationException) {
			messageResponse = dataIntegrityViolationException.getMessage();
			statusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
			if (dataIntegrityViolationException.getCause().getCause() instanceof PSQLException) {
				String[] campos = StringUtils.split(((PSQLException) dataIntegrityViolationException.getCause().getCause()).getMessage(), Constantes.SEPARADOR_DOS_PUNTOS);
				messageResponse = String.format("%s", campos[2]);
			}
		} catch (Exception e) {
			messageResponse = e.getMessage();
			statusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		jsonResponse.put("message", messageResponse);
		jsonResponse.put("code", statusResponse);

		return new ResponseEntity<>(jsonResponse, statusResponse);
	}
}
