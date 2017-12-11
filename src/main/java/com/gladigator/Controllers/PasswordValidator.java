package com.gladigator.Controllers;

import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {
	
	private static final Logger LOG = LogManager.getLogger(PasswordValidator.class);
	private static final String REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

	@Autowired
	private MessageSource messageSource;
	
	public boolean isPasswordStrongEnough(String password) {
		LOG.debug("Password regex = {}", REGEX);
		Pattern pattern = Pattern.compile(REGEX);
		return !pattern.matcher(password).matches();
	}
}
