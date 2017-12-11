package com.gladigator.Controllers;

import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gladigator.Entities.User;
import com.gladigator.Services.UserService;

@Controller
public class RegisterController {
	
	private static final Logger LOG = LogManager.getLogger(RegisterController.class);

	@Autowired
	private StandardPasswordEncoder simplePasswordEncoder;
	@Autowired
	private UserService userService;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private RegisterUtils registerUtils;
	@Autowired
	private PasswordValidator passwordValidator;

	@GetMapping("/createUser")
	public String showRegistrationPage(Model model, User user) {
		model.addAttribute("user", user);
		return "registerpage";
	}

	@PostMapping("/createUser")
	public String processRegistrationForm(Model model, @Valid User user, BindingResult bindingResult,
			HttpServletRequest request, Locale locale) {

		//Jesli User juz istnieje
		if (userService.checkIfUsernameOrEmailAreTaken(user.getUsername(), user.getEmail())) {
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
			
			String token = registerUtils.generateToken();
			user.setConfirmationToken(token);
			LOG.debug("User from registerpage before DB save");
			userService.saveOrUpdateUser(user);

			String link = registerUtils.createLink(request, token);
			registerUtils.sendRegistrationLink(link, user.getEmail(), locale);

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
		} else { 
			//token bedzie w tagu hidden
			model.addAttribute("confirmationToken", user.getConfirmationToken());
			//password to pusty string
			model.addAttribute("password", new String());
		}

		return "confirmpage";
	}

	@PostMapping("/confirm")
	public String processConfirmationForm(@RequestParam Map<String,String> params, RedirectAttributes redir,  Locale locale) {
		//password z tagu input
		String password = params.get("password");
		//token z hidden tagu input, czyli ten sam ktory byl dodany to modelu
		String token = params.get("token");
		
		LOG.debug("Password provided in confirmpage form = {}", password);

		if (passwordValidator.isPasswordStrongEnough(password)) {
			LOG.info("Password is to weak");

			String passwordToWeak = messageSource.getMessage("confirmpage.passwordToWeak", null, locale);
			//Redirect dla errorMessage, aby mozna bylo je odczytac po redirect
			redir.addFlashAttribute("errorMessage", passwordToWeak);

			return "redirect:confirm?token=" + token;
		}

		User user = userService.getUserByToken(token);
		LOG.info("Encoding password, enabling user, erasing token and saving user to db");
		user.setEncryptedPassword(simplePasswordEncoder.encode(password));

		user.setEnabled(true);
		user.setConfirmationToken(null);

		userService.saveOrUpdateUser(user);

		String success = messageSource.getMessage("confirmpage.success", null, locale);
		redir.addFlashAttribute("successMessage", success);
		LOG.info("User has been registered and saved");
		
		return "redirect:login";
	}

}
