package com.gladigator.integrationTests.Controllers;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.gladigator.Controllers.ProfileController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProfileControllerTest {
	
	private ProfileController controller;
	
	private MockMvc mockMvc;
	
	@Before
	public void before() {
		controller = new ProfileController();
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void whenProfile_ThenReturnProfilepage() throws Exception {
		mockMvc.perform(get("/profile")).andExpect(view().name("profilepage"))
										.andExpect(status().isOk())
										.andExpect(forwardedUrl("profilepage"));
	}

}
