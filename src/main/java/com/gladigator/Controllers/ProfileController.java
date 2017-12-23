package com.gladigator.Controllers;

import java.security.Principal;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gladigator.Entities.User;
import com.gladigator.Entities.UserDetails;
import com.gladigator.Services.UserService;

@Controller
@SessionAttributes("userDetails")
public class ProfileController {
	
	private static final Logger LOG = LogManager.getLogger(ProfileController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private MessageSource messageSource;
	
	@Autowired
	private ProfileUtils profileUtils;

	@GetMapping("/profile")
	public String showProfilePage(Model model, Principal principal) {
		String authenticatedUserUsername = principal.getName();
		User user = userService.getUserByUsername(authenticatedUserUsername);
		UserDetails userDetails = profileUtils.obtainUserDetails(user);
		userDetails.setUser(user);
		model.addAttribute("userDetails", userDetails);
		return "profilepage";
	}
	
	@PostMapping("/profile")
	public String processProfilePage(RedirectAttributes redirAttributs, @ModelAttribute @Valid UserDetails userDetails, BindingResult bindingResult, SessionStatus sessionStatus, Locale locale) {
		if (bindingResult.hasErrors()) {
			LOG.debug("Binding result error occured");
			return "profilepage";
		} else {
			userService.saveOrUpdateUserDetails(userDetails);
			sessionStatus.setComplete();
			redirAttributs.addFlashAttribute("success", messageSource.getMessage("profilepage.updateSuccess", null , locale));
		}
		return "redirect:profile";
	}

}
