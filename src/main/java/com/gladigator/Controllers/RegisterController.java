package com.gladigator.Controllers;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gladigator.Controllers.PasswordsWorkflow.CustomPasswordEncoder;
import com.gladigator.Controllers.PasswordsWorkflow.PasswordValidator;
import com.gladigator.Controllers.Utils.RegisterUtils;
import com.gladigator.Entities.User;
import com.gladigator.Services.UserService;

@Controller
public class RegisterController {
	
	private static final Logger LOG = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private RegisterUtils registerUtils;
	@Autowired
	private PasswordValidator passwordValidator;
	@Autowired
	private CustomPasswordEncoder encoder;

	@GetMapping("/createUser")
	public String showRegistrationPage(Model model, User user) {
		model.addAttribute("user", user);
		return "registerpage";
	}

	@PostMapping("/createUser")
	public String processRegistrationForm(Model model, @Valid User user, BindingResult bindingResult,
			HttpServletRequest request, Locale locale) {

		if (userService.checkIfUsernameOrEmailAreTaken(user.getUsername(), user.getEmail())) {
			String message = messageSource.getMessage("registerpage.emailOrUsernameAlreadyTaken", null, locale);
			model.addAttribute("errorMessage", message);
			bindingResult.reject("email");
		}

		if (bindingResult.hasErrors()) {
			LOG.debug("Binding result error occured");
		} else {
			user.setEnabled(false);
			
			String token = registerUtils.generateToken();
			user.setConfirmationToken(token);

			String link = registerUtils.createLink(request, token);
			registerUtils.sendRegistrationLink(link, user.getEmail(), locale);

			String confirmationMessage = messageSource.getMessage("registerpage.confirmationMessage", new Object[] {user.getEmail()}, locale);
			userService.saveOrUpdate(user);
			model.addAttribute("confirmationMessage", confirmationMessage);
		}

		return "registerpage";
	}

	@GetMapping("/confirm")
	public String showConfirmationPage(String password, Model model, @RequestParam("token") String token, Locale locale) {

		User user = userService.getUserByToken(token);
		LOG.debug("User retrieved form DB by token = {}", user);
		if (user == null) {
			String invalidToken = messageSource.getMessage("confirmpage.invalidToken", null, locale);
			model.addAttribute("invalidToken", invalidToken);
		} else { 
			model.addAttribute("confirmationToken", user.getConfirmationToken());
			model.addAttribute("password", new String());
		}

		return "confirmpage";
	}

	@PostMapping("/confirm")
	public String processConfirmationForm(@RequestParam Map<String,String> params, RedirectAttributes redir,  Locale locale) {
		String password = String.valueOf(params.get("password"));
		String token = String.valueOf(params.get("token"));
		
		LOG.debug("Password provided in confirmpage form = {}", password);

		if (passwordValidator.isPasswordToWeak(password)) {
			LOG.info("Password is to weak");

			String passwordToWeak = messageSource.getMessage("confirmpage.passwordToWeak", null, locale);
			redir.addFlashAttribute("errorMessage", passwordToWeak);

			return "redirect:confirm?token=" + token;
		}

		User user = userService.getUserByToken(token);
		LOG.info("Encoding password, enabling user, erasing token and saving user to db");
		user.setEncryptedPassword(encoder.encodePassword(password));
		user.setEnabled(true);
		user.setConfirmationToken(null);
		user.getRoles().add(userService.getRoleById(1));
		
		userService.saveOrUpdate(user);

		String success = messageSource.getMessage("confirmpage.success", null, locale);
		redir.addFlashAttribute("successMessage", success);
		LOG.info("User has been registered and saved");
		
		return "redirect:login";
	}

}
