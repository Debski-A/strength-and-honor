package com.gladigator.unitTests.Controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.gladigator.Controllers.LoginController;

public class LoginControllerTest {
	
	private LoginController controller = new LoginController();

	@Test
	public void whenShowLoginPage_ThenReturnLoginpage() {
		assertThat(controller.showLoginPage(), equalTo("loginpage"));
	}
	
	@Test
	public void whenShowPageNotFound_ThenReturnPagenotfound() {
		assertThat(controller.showPageNotFound(), equalTo("pagenotfound"));
	}
}
