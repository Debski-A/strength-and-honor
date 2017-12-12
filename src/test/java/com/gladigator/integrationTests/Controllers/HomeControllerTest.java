package com.gladigator.integrationTests.Controllers;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.DispatcherServlet;

import com.gladigator.Controllers.HomeController;
import com.gladigator.Controllers.Advices.ControllerExceptionHandler;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

	private HomeController controller = new HomeController();
	
	@Mock
	private ControllerExceptionHandler exceptionController;

	private MockMvc mockMvc;

	@Before
	public void before() throws Exception{
		Mockito.when(exceptionController.handleError404(Mockito.any(Exception.class))).thenReturn("pagenotfound");
		mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(exceptionController).build();
		throwExceptionIfNoHandlerFound(mockMvc);
	}

	@Test
	public void whenHomeIsCalled_ThenReturnHomepage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.view().name("homepage"));
	}

	@Test
	public void whenInvalidUrl_Then404() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/invalidURL")).
		andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("pagenotfound"));
		Mockito.verify(exceptionController).handleError404(Mockito.any(Exception.class));

	}
	
	//Potrzebne aby podczas testow rzucany byl wyjatek NoHandlerFoundException
	private void throwExceptionIfNoHandlerFound(MockMvc mvc) throws NoSuchFieldException, IllegalAccessException { 
        final Field field = MockMvc.class.getDeclaredField("servlet"); 
        field.setAccessible(true); 
        final DispatcherServlet servlet = (DispatcherServlet) field.get(mvc); 
        servlet.setThrowExceptionIfNoHandlerFound(true); 
    } 

}
