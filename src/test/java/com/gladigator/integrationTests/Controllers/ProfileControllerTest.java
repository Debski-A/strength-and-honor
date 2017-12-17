package com.gladigator.integrationTests.Controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@WebAppConfiguration
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/sah-servlet.xml",
								   "classpath:com/gladigator/Configs/test-dao-context.xml",
								   "classpath:com/gladigator/Configs/security-context.xml",
								   "classpath:com/gladigator/Configs/service-context.xml"})
public class ProfileControllerTest {
	
	@Autowired
	private WebApplicationContext webContext;
	
	private MockMvc mockMvc;
	
	@Before
	public void before() {
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).apply(SecurityMockMvcConfigurers.springSecurity()).build();
	}
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(webContext, notNullValue());
	}
	
	@Test
	public void whenProfile_AndUserUnauthenticated_ThenReturnLoginpage() throws Exception {
		mockMvc.perform(get("/profile").with(anonymous())).andExpect(status().is(302))
										.andExpect(redirectedUrlPattern("**/login"));
	}
	
	@Test
	public void whenProfile_AndUserIsAuthenticated_ThenReturnProfilepage() throws Exception {
		mockMvc.perform(get("/profile").with(user("user"))).andExpect(status().isOk())
										.andExpect(view().name("profilepage"));
	}

}
