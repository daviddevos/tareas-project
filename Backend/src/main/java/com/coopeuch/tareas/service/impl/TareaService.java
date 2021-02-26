package com.coopeuch.tareas.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.coopeuch.tareas.dto.TareaDTO;
import com.coopeuch.tareas.repository.custom.ITareaDAO;
import com.coopeuch.tareas.service.custom.ITareaService;

@Service
public class TareaService implements ITareaService{
	
	private final ITareaDAO tareaDAO;

	public TareaService(ITareaDAO tareaDAO) {
		this.tareaDAO = tareaDAO;
	}
	
	@Override
	public List<TareaDTO> findAll() {
		return tareaDAO.findAll();
	}

	@Override
	public TareaDTO save(TareaDTO dto) {
		return tareaDAO.save(dto);
	}

	@Override
	public List<TareaDTO> actualizarTareas(List<TareaDTO> tareas) {
		List <TareaDTO> resultTareas = new ArrayList<TareaDTO>();
		List <Long> resultIdsTareas = new ArrayList<Long>();
		for (TareaDTO tareaDTO : tareas) {
			TareaDTO currentTarea = tareaDAO.findByIdentificador(tareaDTO.getIdentificador());
			if (currentTarea == null)
				tareaDTO.setFechaCreacion(new Date());
			TareaDTO newTarea = tareaDAO.save(tareaDTO);
			resultTareas.add(newTarea);
			resultIdsTareas.add(newTarea.getIdentificador());
		}
		if (resultIdsTareas.size() > 0) {
			tareaDAO.deleteTareasByMissingIds(resultIdsTareas);
		}
		return resultTareas;
	}

}
