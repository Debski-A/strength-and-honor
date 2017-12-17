package com.gladigator.unitTests.Controllers;

import org.junit.Before;
import org.junit.Test;

import com.gladigator.Controllers.ProfileController;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ProfileControllerTest {
	
	private ProfileController controller;
	
	@Before
	public void before() {
		controller = new ProfileController();
	}
	
	@Test
	public void whenShowProfilePage_ThenReturnProfilepage() throws Exception {
		assertThat(controller.showProfilePage(), equalTo("profilepage"));
	}

}
