package com.coopeuch.tareas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.coopeuch.tareas.model.Tarea;

public interface ITareaRepository extends JpaRepository<Tarea, Long>, QuerydslPredicateExecutor<Tarea> {
	
	Tarea findByIdentificador(Long identificador);
	
	@Modifying
	@Query("DELETE FROM Tarea tar WHERE tar.identificador NOT IN :validIds")
	void deleteByMissingsIds(@Param("validIds") List<Long> validIds);
	
}
