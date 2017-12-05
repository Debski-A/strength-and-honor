package com.gladigator.Controllers;

import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gladigator.Daos.UserDaoImpl;
import com.gladigator.Entities.User;
import com.gladigator.Services.EmailService;
import com.gladigator.Services.UserService;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

@Controller
public class RegisterController {
	
	private static final Logger LOG = LogManager.getLogger(RegisterController.class);

	private StandardPasswordEncoder simplePasswordEncoder;
	private UserService userService;
	private EmailService emailService;
	private MessageSource messageSource;

	@Autowired
	public RegisterController(StandardPasswordEncoder passwordEncoder, UserService userService,
			EmailService emailService, MessageSource messageSource) {

		this.simplePasswordEncoder = passwordEncoder;
		this.userService = userService;
		this.emailService = emailService;
		this.messageSource = messageSource;
	}

	@GetMapping("/createUser")
	public String showRegistrationPage(Model model, User user) {
		model.addAttribute("user", user);
		return "registerpage";
	}

	@PostMapping("/createUser")
	public String processRegistrationForm(Model model, @Valid User user, BindingResult bindingResult,
			HttpServletRequest request, Locale locale) {

		boolean userExists = userService.checkIfUsernameOrEmailAreTaken(user.getUsername(), user.getEmail());
		LOG.debug("userExists = {}", userExists);

		//Jesli User juz istnieje
		if (userExists) {
			String message = messageSource.getMessage("registerpage.emailOrUsernameAlreadyTaken", null, locale);
			model.addAttribute("errorMessage", message);
			bindingResult.reject("email");
		}

		//Jesli wprowadzone dane w formularzu byly niezgodne z ograniczeniami
		if (bindingResult.hasErrors()) {
			LOG.debug("Binding results occured");
		//Jesliu wszystko bylo ok - wysyla email
		} else { 

			user.setEnabled(false);

			// Generate random 36-character string token for confirmation link
			user.setConfirmationToken(UUID.randomUUID().toString());
			LOG.debug("User from registerpage before DB save");
			userService.saveOrUpdateUser(user);

			String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getLocalPort() + request.getContextPath();
			appUrl += "/confirm?token=" + user.getConfirmationToken();
			LOG.debug("Application URL with token param = {}", appUrl);

			SimpleMailMessage registrationEmail = new SimpleMailMessage();
			registrationEmail.setTo(user.getEmail());
			String emailSubject = messageSource.getMessage("registerpage.emailSubject", null, locale);
			String emailContent = messageSource.getMessage("registerpage.emailContent", null, locale);
			registrationEmail.setSubject(emailSubject);
			registrationEmail.setText(emailContent + appUrl);

			emailService.sendEmail(registrationEmail);

			String confirmationMessage = messageSource.getMessage("registerpage.confirmationMessage", new Object[] {user.getEmail()}, locale);
			model.addAttribute("confirmationMessage", confirmationMessage);
			
		}

		return "registerpage";
	}

	@GetMapping("/confirm")
	public String showConfirmationPage(String password, Model model, @RequestParam("token") String token, Locale locale) {

		User user = userService.getUserByToken(token);
		LOG.debug("User retrieved form DB by token = {}", user);
		if (user == null) { //Brak tokenu w DB
			String invalidToken = messageSource.getMessage("confirmpage.invalidToken", null, locale);
			model.addAttribute("invalidToken", invalidToken);
		} else { // Token found
			model.addAttribute("confirmationToken", user.getConfirmationToken());
			model.addAttribute("password", new String());
		}

		return "confirmpage";
	}

	@PostMapping("/confirm")
	public String processConfirmationForm(ModelAndView model, BindingResult bindingResult, @RequestParam Map<String,String> params, RedirectAttributes redir,  Locale locale) {

		Zxcvbn passwordCheck = new Zxcvbn();
		String password = params.get("password");
		String token = params.get("token");
		
		LOG.debug("Password provided in confirmpage form = {}", password);

		Strength strength = passwordCheck.measure(password);
		LOG.debug("Password strength = {}", strength);

		if (strength.getScore() < 3) {
			LOG.info("Password was to weak");
			bindingResult.reject("password");

			String passwordToWeak = messageSource.getMessage("confirmpage.passwordToWeak", null, locale);
			redir.addFlashAttribute("errorMessage", passwordToWeak);

			return "redirect:confirm?token=" + token;
		}

		User user = userService.getUserByToken(token);
		LOG.debug("User retrieved form DB by token = {}", user);
		LOG.info("Encoding password, enabling user, erasing token");
		user.setEncryptedPassword(simplePasswordEncoder.encode(password));

		user.setEnabled(true);
		user.setConfirmationToken(null);

		LOG.debug("Saving user to DB. User = {}", user);
		userService.saveOrUpdateUser(user);

		String success = messageSource.getMessage("confirmpage.success", null, locale);
		model.addObject("successMessage", success);
		LOG.info("User has been registered and saved");
		
		return "redirect:login";
	}

}
