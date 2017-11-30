package com.gladigator.unitTests.Controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gladigator.Controllers.LoginController;
import com.gladigator.Controllers.Advices.ControllerExceptionHandler;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Objects;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/appconfig-mvc.xml")
public class LoginControllerTest {
	
	@Autowired
	private LoginController controller;
	
	private MockMvc mockMvc;
	
	@Before
	public void before() {
		System.out.println("LoginController = " + Objects.isNull(controller));
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void whenLogin_ThenReturnLoginpage() throws Exception {
		mockMvc.perform(get("/login")).andExpect(view().name("loginpage"));
	}
	
	@Test
	public void whenAccessDenied_ThenReturnPageNotFound() throws Exception {
		mockMvc.perform(get("/accessDenied")).andExpect(view().name("pagenotfound"));
	}

}
