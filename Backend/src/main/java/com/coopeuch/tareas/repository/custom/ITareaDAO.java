package com.coopeuch.tareas.repository.custom;

import java.util.List;

import com.coopeuch.tareas.dto.TareaDTO;

public interface ITareaDAO extends ICrudDAO<TareaDTO> {
	
	TareaDTO findByIdentificador(Long identificador);
	
	void deleteTareasByMissingIds(List<Long> validIds);
	
}
