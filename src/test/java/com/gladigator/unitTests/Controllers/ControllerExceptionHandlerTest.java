package com.gladigator.unitTests.Controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.gladigator.Controllers.Advices.ControllerExceptionHandler;

public class ControllerExceptionHandlerTest {
	
	private ControllerExceptionHandler exceptionController;
	
	@Before
	public void before() {
		exceptionController = new ControllerExceptionHandler();
	}
	
	@Test
	public void whenHandleError404ThenReturnPageNotFound() throws Exception {
		assertThat(exceptionController.handleError404(new Exception()), equalTo("pagenotfound"));
		
	}

}
