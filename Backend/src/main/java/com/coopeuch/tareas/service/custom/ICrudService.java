package com.coopeuch.tareas.service.custom;

import java.util.List;

import com.coopeuch.tareas.dto.BaseDTO;

public interface ICrudService <T extends BaseDTO> {

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<T> findAll();
	
	/**
	 * Creates the Entity.
	 *
	 * @param entity the entity
	 * @return the saved entity
	 */
	T save(final T dto);
	
}
