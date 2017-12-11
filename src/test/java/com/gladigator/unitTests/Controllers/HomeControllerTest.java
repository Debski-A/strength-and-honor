package com.gladigator.unitTests.Controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.gladigator.Controllers.HomeController;

public class HomeControllerTest {
	
	private HomeController controller = new HomeController();

	@Test
	public void whenShowHomePage_ThenReturnHomepage() throws Exception {
		assertThat(controller.showHomePage(), equalTo("homepage"));
	}
}
