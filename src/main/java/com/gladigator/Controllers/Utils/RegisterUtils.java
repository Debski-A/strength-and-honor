package com.gladigator.Controllers.Utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.gladigator.Services.EmailService;

@Component
public class RegisterUtils {
	
	@Autowired
	private EmailService emailService;
	@Autowired 
	private SimpleMailMessage registrationEmail;
	
	@Autowired
	private MessageSource messageSource;
	
	private static final Logger LOG = LoggerFactory.getLogger(RegisterUtils.class);
	
	public String createLink(HttpServletRequest request, String confirmationToken) {
		//String appUrl = request.getScheme() + "://" + gethostName(request) + ":" + request.getLocalPort() + request.getContextPath();
		String appUrl = obtainApplicationUrl(request);
		appUrl += "/confirm?token=" + confirmationToken;
		LOG.debug("Application URL with token param = {}", appUrl);
		return appUrl;
	}
	
	private String obtainApplicationUrl(HttpServletRequest request) {
		String appUrl = null;
		
		String domainName = System.getProperty("domainName");
		if (domainName == null) {
			appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getLocalPort() + request.getContextPath();
		} else {
			appUrl = request.getScheme() + "://" + domainName + request.getContextPath();
		}
		return appUrl;
	}
	
	public String generateToken() {
		String token = UUID.randomUUID().toString();
		LOG.debug("Generated token = {}", token);
		return token;
	}
	
	public void sendRegistrationLink(String link, String email, Locale locale) {
		registrationEmail.setTo(email);
		String emailSubject = messageSource.getMessage("registerpage.emailSubject", null, locale);
		String emailContent = messageSource.getMessage("registerpage.emailContent", null, locale);
		registrationEmail.setSubject(emailSubject);
		registrationEmail.setText(emailContent + link);

		emailService.sendEmail(registrationEmail);
	}

}
