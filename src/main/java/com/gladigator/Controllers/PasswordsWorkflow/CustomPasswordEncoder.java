package com.gladigator.Controllers.PasswordsWorkflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class CustomPasswordEncoder {

	@Autowired
	private StandardPasswordEncoder encoder;
	
	public String encodePassword(String password) {
		return encoder.encode(password);
	}
}
