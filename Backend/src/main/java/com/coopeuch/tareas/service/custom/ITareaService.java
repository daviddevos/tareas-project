package com.coopeuch.tareas.service.custom;

import java.util.List;

import com.coopeuch.tareas.dto.TareaDTO;

public interface ITareaService extends ICrudService<TareaDTO>{
	
	List<TareaDTO> actualizarTareas(List<TareaDTO> tareas);
	
}
