package com.gladigator.integrationTests.Controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.gladigator.Controllers.LoginController;


public class LoginControllerTest {
	
	private LoginController controller = new LoginController();
	
	private MockMvc mockMvc;
	
	@Before
	public void before() {
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
