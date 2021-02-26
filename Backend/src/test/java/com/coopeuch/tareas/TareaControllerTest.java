package com.coopeuch.tareas;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.coopeuch.tareas.controller.TareaController;
import com.coopeuch.tareas.dto.TareaDTO;
import com.coopeuch.tareas.service.impl.TareaService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TareaController.class)
public class TareaControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	TareaService tareaService;
	
	@Test
	public void findAll() throws Exception {
		Calendar calendar = new GregorianCalendar(2021,1,1,7,0,0);
		List<TareaDTO> tareas = Arrays.asList(
				new TareaDTO(1L, "prueba1", calendar.getTime(), true),
				new TareaDTO(2L, "prueba2", calendar.getTime(), true),
				new TareaDTO(3L, "prueba3", calendar.getTime(), true));
		Mockito.when(tareaService.findAll()).thenReturn(tareas);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/tareas/todas")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expected = "["
				+ "{\"identificador\":1,\"descripcion\":\"prueba1\",\"fechaCreacion\":\"2021-02-01T07:00:00.000-0500\",\"vigente\":true},"
				+ "{\"identificador\":2,\"descripcion\":\"prueba2\",\"fechaCreacion\":\"2021-02-01T07:00:00.000-0500\",\"vigente\":true},"
				+ "{\"identificador\":3,\"descripcion\":\"prueba3\",\"fechaCreacion\":\"2021-02-01T07:00:00.000-0500\",\"vigente\":true}]";
		
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
}
