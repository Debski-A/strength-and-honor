package com.gladigator.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.gladigator.Exceptions.ServiceException;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Async
	public void sendEmail(SimpleMailMessage email) {
	    try {
		this.mailSender.send(email);
	    } catch (MailException ex) {
		throw new ServiceException(ex.getMessage());
	    }
	}

}
