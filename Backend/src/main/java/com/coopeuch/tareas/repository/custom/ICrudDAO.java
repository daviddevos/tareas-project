package com.coopeuch.tareas.repository.custom;

import java.util.List;

import org.springframework.data.domain.Page;

import com.coopeuch.tareas.dto.BaseDTO;

public interface ICrudDAO <T extends BaseDTO> {

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<T> findAll();
	
	/**
	 * Filter paginated.
	 *
	 * @param page      the page
	 * @param size      the size
	 * @param entity    the entity
	 * @param column    the column
	 * @param orderType the order type
	 * @return the page
	 */
	Page<T> filterPaginated(int page, int size, T entity, String column, String orderType);

	/**
	 * Creates the Entity.
	 *
	 * @param entity the entity
	 * @return the saved entity
	 */
	T save(final T entity);
	
}
