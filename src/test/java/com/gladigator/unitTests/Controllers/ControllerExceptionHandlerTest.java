package com.gladigator.unitTests.Controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.gladigator.Controllers.Advices.ApplicationExceptionsHandler;

public class ControllerExceptionHandlerTest {
	
	private ApplicationExceptionsHandler exceptionController;
	
	@Before
	public void before() {
		exceptionController = new ApplicationExceptionsHandler();
	}
	
	@Test
	public void whenHandleError404ThenReturnPageNotFound() throws Exception {
		assertThat(exceptionController.handleError404(new Exception()), equalTo("pagenotfound"));
		
	}

}
