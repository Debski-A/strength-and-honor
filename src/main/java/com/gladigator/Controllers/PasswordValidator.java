package com.gladigator.Controllers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {
	
	private static final Logger LOG = LogManager.getLogger(PasswordValidator.class);
	private static final String REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
	
	public boolean isPasswordToWeak(String password) {
		LOG.debug("Password regex = {}", REGEX);
		return !password.matches(REGEX);
	}
}
