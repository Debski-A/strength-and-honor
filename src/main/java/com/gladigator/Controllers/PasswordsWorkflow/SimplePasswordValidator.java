package com.gladigator.Controllers.PasswordsWorkflow;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class SimplePasswordValidator implements PasswordValidator {
	
	private static final Logger LOG = LogManager.getLogger(SimplePasswordValidator.class);
	private static final String REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
	
	public boolean isPasswordToWeak(String password) {
		LOG.debug("Password regex = {}", REGEX);
		return !password.matches(REGEX);
	}
}
