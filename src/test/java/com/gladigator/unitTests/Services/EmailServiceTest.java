package com.gladigator.unitTests.Services;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.gladigator.Exceptions.ServiceException;
import com.gladigator.Services.EmailService;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {
	
	@Mock
	private JavaMailSender mailSender;
	
	@InjectMocks
	private EmailService emailService;

	//Then
	@Test(expected = ServiceException.class)
	public void whenSendEmail_ThenThrowServiceException() throws Exception {
		//Given
		SimpleMailMessage message = new SimpleMailMessage();
		doThrow(MailSendException.class).when(mailSender).send(message);
		//When
		emailService.sendEmail(message);
	}
	
	@Test
	public void whenSendEmail_ThenSendMethodIsInvoked_AndEverythingIsOk() throws Exception {
		//Given
		SimpleMailMessage message = new SimpleMailMessage();
		//When
		emailService.sendEmail(message);
		//Then
		Mockito.verify(mailSender, times(1)).send(message);
	}
}
