package com.coopeuch.tareas.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.coopeuch.tareas.dto.TareaDTO;
import com.coopeuch.tareas.repository.impl.TareaDAO;
import com.coopeuch.tareas.service.custom.ITareaService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TareaServiceTest {
	
	@MockBean
	private TareaDAO tareaDAO;
	
	@Autowired
	private ITareaService tareaService;
	
	@Captor
	private ArgumentCaptor<ArrayList<Long>> longListCaptor;
	
	@Test
	public void findAll() {
		Calendar calendar = new GregorianCalendar(2021,1,1,7,0,0);
		List<TareaDTO> tareas = Arrays.asList(
				new TareaDTO(1L, "prueba1", calendar.getTime(), true),
				new TareaDTO(2L, "prueba2", calendar.getTime(), true),
				new TareaDTO(3L, "prueba3", calendar.getTime(), true));
		Mockito.when(tareaDAO.findAll()).thenReturn(tareas);
		
		assertThat(tareaService.findAll().size()).isEqualTo(3);
	}
	
	@Test
	public void save() {
		Calendar calendar = new GregorianCalendar(2021,1,1,7,0,0);
		TareaDTO newTarea = new TareaDTO(1L, "prueba1", calendar.getTime(), true);
		Mockito.when(tareaDAO.save(Mockito.any())).thenReturn(newTarea);
		
		assertThat(tareaService.save(newTarea)).isEqualTo(newTarea);
	}
	
	@Test
	public void actualizarTareasSinDatosExistentes() {
		Date currentDate = new Date();
		List<TareaDTO> tareas = Arrays.asList(
				new TareaDTO(1L, "prueba1", currentDate, true),
				new TareaDTO(2L, "prueba2", currentDate, true),
				new TareaDTO(3L, "prueba3", currentDate, true));
		Mockito.when(tareaDAO.findByIdentificador(Mockito.anyLong())).thenReturn(null);
		Mockito.when(tareaDAO.save(Mockito.any())).thenReturn(
				new TareaDTO(1L, "prueba1", currentDate, true),
				new TareaDTO(2L, "prueba2", currentDate, true),
				new TareaDTO(3L, "prueba3", currentDate, true));
		doNothing().when(tareaDAO).deleteTareasByMissingIds(Mockito.anyList());
		assertArrayEquals(tareaService.actualizarTareas(tareas).toArray(), tareas.toArray());
	}
	
	@Test
	public void actualizarTareasConDatosExistentes() {
		Date currentDate = new Date();
		List<TareaDTO> tareasUpdated = Arrays.asList(
				new TareaDTO(1L, "prueba1", currentDate, true),
				new TareaDTO(3L, "prueba3Editada", currentDate, false),
				new TareaDTO(4L, "prueba4", currentDate, true));
		Mockito.when(tareaDAO.findByIdentificador(Mockito.anyLong())).thenReturn(
				new TareaDTO(1L, "prueba1", currentDate, true),
				new TareaDTO(3L, "prueba3", currentDate, true), null);
		Mockito.when(tareaDAO.save(Mockito.any())).thenReturn(
				new TareaDTO(1L, "prueba1", currentDate, true),
				new TareaDTO(3L, "prueba3Editada", currentDate, false),
				new TareaDTO(4L, "prueba4", currentDate, true));
		doNothing().when(tareaDAO).deleteTareasByMissingIds(longListCaptor.capture());
		assertArrayEquals(tareaService.actualizarTareas(tareasUpdated).toArray(), tareasUpdated.toArray());
		assertEquals(Arrays.asList(1L, 3L, 4L),longListCaptor.getAllValues().get(0));
	}
}
