package com.gladigator.Controllers;

import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gladigator.Entities.User;
import com.gladigator.Services.EmailService;
import com.gladigator.Services.UserService;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

@Controller
public class RegisterController {

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

	// Process form input data
	@PostMapping("/createUser")
	public String processRegistrationForm(Model model, @Valid User user, BindingResult bindingResult,
			HttpServletRequest request, Locale locale) {

		User userExists = userService.getUserByEmail(user.getEmail());

		System.out.println(userExists);

		//Jesli User juz istnieje
		if (userExists != null) {

			String message = messageSource.getMessage("registerpage.emailAlreadyRegistered",
					new Object[] { user.getEmail() }, locale);
			model.addAttribute("errorMessage", message);
			bindingResult.reject("email");
		}

		//Jesli wprowadzone dane w formularzu byly niezgodne z ograniczeniami
		if (bindingResult.hasErrors()) {
		//Jesliu wszystko bylo ok - wysyla email
		} else { 

			user.setEnabled(false);

			// Generate random 36-character string token for confirmation link
			user.setConfirmationToken(UUID.randomUUID().toString());

			userService.saveOrUpdateUser(user);

			String appUrl = request.getScheme() + "://" + request.getServerName();

			SimpleMailMessage registrationEmail = new SimpleMailMessage();
			registrationEmail.setTo(user.getEmail());
			String emailSubject = messageSource.getMessage("registerpage.emailSubject", null, locale);
			String emailContent = messageSource.getMessage("registerpage.emailContent", null, locale);
			registrationEmail.setSubject(emailSubject);
			registrationEmail.setText(emailContent + appUrl + "/confirm?token=" + user.getConfirmationToken());
			registrationEmail.setFrom("noreply@gladigator.com");

			emailService.sendEmail(registrationEmail);

			String confirmationMessage = messageSource.getMessage("registerpage.confirmationMessage", new Object[] {user.getEmail()}, locale);
			model.addAttribute("confirmationMessage", confirmationMessage);
			
		}

		return "registerpage";
	}

	@GetMapping("/confirm")
	public String showConfirmationPage(Model model, @RequestParam("token") String token, Locale locale) {

		User user = userService.getUserByToken(token);

		if (user == null) { //Brak tokenu w DB
			String invalidToken = messageSource.getMessage("registerpage.invalidToken", null, locale);
			model.addAttribute("invalidToken", invalidToken);
		} else { // Token found
			model.addAttribute("confirmationToken", user.getConfirmationToken());
		}

		return "confirm";
	}

	// Process confirmation link
	@PostMapping("/confirm")
	public String processConfirmationForm(Model model, BindingResult bindingResult,
			@RequestParam Map<String, String> requestParams, RedirectAttributes redir, Locale locale) {

		Zxcvbn passwordCheck = new Zxcvbn();

		Strength strength = passwordCheck.measure(requestParams.get("password"));

		if (strength.getScore() < 3) {
			bindingResult.reject("password");

			String passwordToWeak = messageSource.getMessage("registerpage.passwordToWeak", null, locale);
			redir.addFlashAttribute("errorMessage", passwordToWeak);

			System.out.println(requestParams.get("token"));
			return "redirect:confirm?token=" + requestParams.get("token");
		}

		// Find the user associated with the reset token
		User user = userService.getUserByToken(requestParams.get("token"));

		// Set new password
		user.setPassword(simplePasswordEncoder.encode(requestParams.get("password")));

		// Set user to enabled
		user.setEnabled(true);

		// Save user
		userService.saveOrUpdateUser(user);

		String success = messageSource.getMessage("registerpage.success", null, locale);
		model.addAttribute("successMessage", success);
		return "confirm";
	}

}
