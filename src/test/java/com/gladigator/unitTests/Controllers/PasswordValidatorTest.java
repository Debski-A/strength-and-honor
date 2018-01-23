package com.gladigator.unitTests.Controllers;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.gladigator.Controllers.PasswordsWorkflow.SimplePasswordValidator;

public class PasswordValidatorTest {
	
	private SimplePasswordValidator passswordValidator;
	
	@Before
	public void before() {
		passswordValidator = new SimplePasswordValidator();
	}
	
	@Test
	public void whenIsPasswordToWeak_AndPasswordIsWeak_ReturnTrue() throws Exception {
		
		assertThat(passswordValidator.isPasswordToWeak("weakpassword"), equalTo(true));
	}
	
	@Test
	public void whenIsPasswordToWeak_AndPasswordIsStrongEnough_ReturnFalse() throws Exception {
		assertThat(passswordValidator.isPasswordToWeak("strongpassword1234"), equalTo(false));
	}
}
