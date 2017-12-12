package com.gladigator.unitTests.Controllers;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.gladigator.Controllers.PasswordValidator;

public class PasswordValidatorTest {
	
	private PasswordValidator passswordValidator;
	
	@Before
	public void before() {
		passswordValidator = new PasswordValidator();
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
