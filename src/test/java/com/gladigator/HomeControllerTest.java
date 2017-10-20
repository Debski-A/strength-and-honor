package com.gladigator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.gladigator.Controller.HomeController;
import com.gladigator.Exceptions.Controller.ExceptionController;

public class HomeControllerTest {
	
	private HomeController controller;
	
	@Mock
	private ExceptionController exceptionController;
	
	private MockMvc mockMvc;
	
	@Before
	public void before() {
		controller = new HomeController();
		mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(exceptionController).build();
	}
	
	@Test 
	public void whenHomeIsCalledThenReturnHomepage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.view().name("homepage"));
	}
	
	@Test
	public void whenInvalidUrlThenPageNotFound() throws Exception {
		Mockito.when(exceptionController.handleError404(Mockito.any(Exception.class))).thenReturn("pageNotFound");
		mockMvc.perform(MockMvcRequestBuilders.get("/invalidURL")).andExpect(MockMvcResultMatchers.view().name("pageNotFound"));
	}
	

}
