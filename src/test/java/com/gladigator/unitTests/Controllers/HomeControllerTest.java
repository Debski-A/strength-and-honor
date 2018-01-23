package com.gladigator.unitTests.Controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.gladigator.Controllers.HomeController;
import com.gladigator.Controllers.ResponseEntityBuilder;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {
	
	@Mock
	private ResponseEntity<String> responseEntity;
	
	@Mock
	private ResponseEntityBuilder responseEntityBuilder;

	@InjectMocks
	private HomeController controller;

	@Test
	public void whenShowHomePage_ThenReturnHomepage() throws Exception {
		assertThat(controller.showHomePage(), equalTo("homepage"));
	}
	
	@Test
	public void whenShowWheatherPage_ThenReturnWeatherpage() throws Exception {
		assertThat(controller.showWeatherPage(), equalTo("weatherpage"));
	}
	
	@Test
	public void whenShowWeatherConditions_ThenReturnResponseEntity() throws Exception {
		when(responseEntityBuilder.createResponseEntity()).thenReturn(responseEntity);
		
		assertThat(controller.showWeatherConditions(), equalTo(responseEntity));
		
		verify(responseEntityBuilder).createResponseEntity();
	}
	
}
