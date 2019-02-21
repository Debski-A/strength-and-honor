package com.gladigator.unitTests.Controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.gladigator.Controllers.HomeController;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {
	
	private HomeController controller;
	
	@Before
	public void before() {
		controller = new HomeController();
	}

	@Test
	public void whenShowHomePage_ThenReturnHomepage() throws Exception {
		assertThat(controller.showHomePage(), equalTo("homepage"));
	}
	
	@Test
	public void whenShowWheatherPage_ThenReturnWeatherpage() throws Exception {
		assertThat(controller.showWeatherPage(), equalTo("weatherpage"));
	}
	
}
