package com.gladigator.unitTests.Controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.gladigator.ControllerAdvices.ExceptionController;

@RunWith(MockitoJUnitRunner.class)
public class ExceptionControllerTest {
	
	private ExceptionController exceptionController;
	
	@Before
	public void before() {
		exceptionController = new ExceptionController();
	}
	
	@Test
	public void whenHandleError404ThenReturnPageNotFound() throws Exception {
		assertThat(exceptionController.handleError404(new Exception()), equalTo("pageNotFound"));
		
	}

}