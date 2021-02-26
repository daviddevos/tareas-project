package com.coopeuch.tareas.mapper;

import org.mapstruct.Mapper;

import com.coopeuch.tareas.dto.TareaDTO;
import com.coopeuch.tareas.model.Tarea;

@Mapper(componentModel = "spring")
public interface ITareaDTOMapper extends IModelDTOMapper<Tarea, TareaDTO> {

	TareaDTO entityToDto(Tarea entity);
	
	Tarea dtoToEntity(TareaDTO dto);
	
}
