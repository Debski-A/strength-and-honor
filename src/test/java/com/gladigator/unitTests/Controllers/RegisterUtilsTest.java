package com.gladigator.unitTests.Controllers;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;

import com.gladigator.Controllers.Utils.RegisterUtils;
import com.gladigator.Services.EmailService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import java.util.Locale;


@RunWith(MockitoJUnitRunner.class)
public class RegisterUtilsTest {

	@Mock
	private EmailService emailService;
	@Mock 
	private SimpleMailMessage registrationEmail;
	@Mock
	private MessageSource messageSource;
	@Mock
	private HttpServletRequest request;
	
	@InjectMocks
	private RegisterUtils registerUtils;
	
	
	@Test
	public void whenCreateLink_ThenReturnExpectedLink() throws Exception {
		when(request.getScheme()).thenReturn("scheme");
		when(request.getServerName()).thenReturn("serverName");
		when(request.getLocalPort()).thenReturn(8080);
		when(request.getContextPath()).thenReturn("/contextPath");
		
		assertThat(registerUtils.createLink(request, "1234"), equalTo("scheme://serverName:8080/contextPath/confirm?token=1234"));
		
		verify(request).getScheme();
		verify(request).getServerName();
		verify(request).getLocalPort();
		verify(request).getContextPath();
	}
	
	@Test
	public void whenSendRegistrationLink_ThenPrepareEmailAndSend() throws Exception {
		Locale testLocale = new Locale("pl");
		when(messageSource.getMessage("registerpage.emailSubject", null, testLocale)).thenReturn("subject");
		when(messageSource.getMessage("registerpage.emailContent", null, testLocale)).thenReturn("content + ");
		
		registerUtils.sendRegistrationLink("link", "email@gmail.com", testLocale);
		
		verify(registrationEmail).setTo("email@gmail.com");
		verify(registrationEmail).setSubject("subject");
		verify(registrationEmail).setText("content + link");
		verify(messageSource, times(2)).getMessage(anyString(), any(), any(Locale.class));
		verify(emailService).sendEmail(registrationEmail);
	}
	
}
