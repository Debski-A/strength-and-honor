package com.gladigator.integrationTests.Controllers;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(locations = "classpath:com/gladigator/Configs/security-context.xml" )
public class CsrfEnabledIntegrationTest {
	
	private MockMvc mockMvc;
	
	@Before
	public void before() {
		mockMvc = MockMvcBuilders.build();
	}
	
	@Test
	public void addStudentWithoutCSRF() throws Exception {
	    mockMvc.perform(post("/saveStudent").contentType(MediaType.APPLICATION_JSON)
	      .param("id", "1234567").param("name", "Joe").param("gender", "M")
	      .with(testUser())).andExpect(status().isForbidden());
	}
	 
	@Test
	public void addStudentWithCSRF() throws Exception {
	    mockMvc.perform(post("/saveStudent").contentType(MediaType.APPLICATION_JSON)
	      .param("id", "1234567").param("name", "Joe").param("gender", "M")
	      .with(testUser()).with(csrf())).andExpect(status().isOk());
	}

}
