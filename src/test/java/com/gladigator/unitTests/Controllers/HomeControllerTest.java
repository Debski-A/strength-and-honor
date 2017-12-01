package com.gladigator.unitTests.Controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.gladigator.Controllers.HomeController;
import com.gladigator.Controllers.Advices.ControllerExceptionHandler;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/sah-servlet.xml")
public class HomeControllerTest {

	@Autowired
	private HomeController controller;
	
	private ControllerExceptionHandler exceptionController;

	private MockMvc mockMvc;

	@Before
	public void before() {
		exceptionController = Mockito.mock(ControllerExceptionHandler.class);
		Mockito.when(exceptionController.handleError404(Mockito.any(Exception.class))).thenReturn("pageNotFound");
		mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(exceptionController).build();
	}

	@Test
	public void whenHomeIsCalled_ThenReturnHomepage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.view().name("homepage"));
	}

	@Test
	public void whenInvalidUrl_Then404() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/invalidURL")).
		andExpect(MockMvcResultMatchers.status().is(404));
		//TODO NIE WIEM CZEMU TO NIE DZIALA
		//Mockito.verify(exceptionController).handleError404(Mockito.any(Exception.class));

	}

}
