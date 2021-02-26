package com.coopeuch.tareas.repository.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.coopeuch.tareas.dto.TareaDTO;
import com.coopeuch.tareas.mapper.ITareaDTOMapper;
import com.coopeuch.tareas.repository.ITareaRepository;
import com.coopeuch.tareas.repository.custom.ITareaDAO;

@Repository
public class TareaDAO implements ITareaDAO {
	
	private final ITareaRepository repository;
	private final ITareaDTOMapper mapper;
	
	public TareaDAO(ITareaRepository repository, ITareaDTOMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	@Override
	public TareaDTO findByIdentificador(Long identificador) {
		return mapper.entityToDto(repository.findByIdentificador(identificador));
	}
	
	@Override
	public List<TareaDTO> findAll() {
		return mapper.entitiesToDtos(repository.findAll());
	}

	@Override
	public Page<TareaDTO> filterPaginated(int page, int size, TareaDTO entity, String column, String orderType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TareaDTO save(TareaDTO entity) {
		return mapper.entityToDto(repository.save(mapper.dtoToEntity(entity)));
	}

	@Override
	@Transactional
	public void deleteTareasByMissingIds(List<Long> validIds) {
		repository.deleteByMissingsIds(validIds);
	}

}
