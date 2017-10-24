package com.gladigator.Controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.gladigator.Controller.HomeController;
import com.gladigator.ControllerAdvice.ExceptionController;

public class HomeControllerTest {
	
	private HomeController controller;
	
	private MockMvc mockMvc;
	
	@Before
	public void before() {
		controller = new HomeController();
		mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new ExceptionController()).build();
	}
	
	@Test 
	public void whenHomeIsCalledThenReturnHomepage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.view().name("homepage"));
	}
	
	@Test
	public void whenInvalidUrlThen404() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/invalidURL"))
			.andExpect(MockMvcResultMatchers.status().is(404));
	}
	
}
