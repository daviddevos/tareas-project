package com.coopeuch.tareas.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.coopeuch.tareas.dto.BaseDTO;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

// TODO: Auto-generated Javadoc
/**
 * The Interface IModelDTOMapper.
 *
 * @param <S> the element type
 * @param <D> the generic type
 */
public interface IModelDTOMapper <S extends Serializable, D extends BaseDTO>{
  
  /**
   * Entity to dto.
   *
   * @param entity the entity
   * @return the d
   */
  D entityToDto(S entity);
  
  /**
   * Dto to entity.
   *
   * @param dto the dto
   * @return the e
   */
  S dtoToEntity(D dto);
  
  /**
   * Entities to dtos.
   *
   * @param entities the entities
   * @return the list
   */
  List<D> entitiesToDtos(List<S> entities);
  
  /**
   * Dtos toentities.
   *
   * @param dtos the dtos
   * @return the list
   */
  List<S> dtosToentities(List<D> dtos);
  
  /**
   * Optional entity to dto.
   *
   * @param optEntity the opt entity
   * @return the optional
   */
  default Optional<D> optionalEntityToDto(Optional<S> optEntity) {
    if ( optEntity.isPresent() ) {
    	return Optional.ofNullable( entityToDto( optEntity.get() ) );
    }  else {
    	return Optional.empty();
    }
  }
  
  /**
   * Entity page to dto page.
   *
   * @param page the page
   * @param entityPage the entity page
   * @return the page
   */
  default Page<D> entityPageToDtoPage(Pageable page, Page<S> entityPage) {
    List<D> dtos = entitiesToDtos(entityPage.getContent());
    return new PageImpl<>(dtos, page, entityPage.getTotalElements());
  }
  
  /**
   * Entity page to dto list.
   *
   * @param entityPage the entity page
   * @return the list
   */
  default List<D> entityPageToDtoList(Page<S> entityPage) {
    return entitiesToDtos(entityPage.getContent());
  }

}