package com.gladigator.integrationTests.Controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.hibernate.hql.internal.ast.tree.IsNotNullLogicOperatorNode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.gladigator.Controllers.LoginController;

@ActiveProfiles("test")
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/sah-servlet.xml",
		   "classpath:com/gladigator/Configs/test-dao-context.xml",
		   "classpath:com/gladigator/Configs/security-context.xml",
		   "classpath:com/gladigator/Configs/service-context.xml"})
public class LoginControllerTest {
	
	@Autowired
	private LoginController controller;
	
	private MockMvc mockMvc;
	
	@Before
	public void before() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller, notNullValue());
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
