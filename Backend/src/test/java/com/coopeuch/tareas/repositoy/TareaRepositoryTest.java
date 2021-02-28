package com.coopeuch.tareas.repositoy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.coopeuch.tareas.model.Tarea;
import com.coopeuch.tareas.repository.ITareaRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TareaRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ITareaRepository repository;
	
	@Test
	public void findAll() {
		Tarea tareaUno = new Tarea(1L, "prueba1", new Date(), true);
		entityManager.persistAndFlush(tareaUno);
		Tarea tareaDos = new Tarea(2L, "prueba2", new Date(), true);
		entityManager.persistAndFlush(tareaDos);
		assertThat(repository.findAll()).isNotEmpty();
	}
	
	@Test
	public void findByIdentificador() {
		Tarea tarea = new Tarea(1L, "prueba1", new Date(), true);
		entityManager.persistAndFlush(tarea);
		assertThat(repository.findByIdentificador(tarea.getIdentificador())).isNotNull();
	}
	
	@Test
	public void save() {
		Tarea tarea = new Tarea(1L, "prueba1", new Date(), true);
		assertThat(entityManager.persistAndFlush(tarea)).isNotNull();
	}
	
	@Test
	public void deleteByMissingsIds() {
		Tarea tareaUno = new Tarea(1L, "prueba1", new Date(), true);
		entityManager.persistAndFlush(tareaUno);
		Tarea tareaDos = new Tarea(2L, "prueba2", new Date(), true);
		entityManager.persistAndFlush(tareaDos);
		Tarea tareaTres = new Tarea(3L, "prueba3", new Date(), true);
		entityManager.persistAndFlush(tareaTres);
		repository.deleteByMissingsIds(new ArrayList<Long>(Arrays.asList(1L, 2L)));
		assertEquals(repository.findAll().size(), 2);
	}
}
