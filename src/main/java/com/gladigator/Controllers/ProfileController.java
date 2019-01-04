package com.gladigator.Controllers;

import java.security.InvalidParameterException;
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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ws.client.WebServiceIOException;

import com.gladigator.Controllers.Utils.ProfileUtils;
import com.gladigator.Entities.CalculateBMIRequest;
import com.gladigator.Entities.CalculateBMIResponse;
import com.gladigator.Entities.CalculateBMRResponse;
import com.gladigator.Entities.UserDetails;
import com.gladigator.Exceptions.RepositoryException;
import com.gladigator.Exceptions.ServiceException;
import com.gladigator.Services.UserService;

@Controller
@SessionAttributes({ "userDetails", "bodyTypeListOfSelectives", "sexListOfSelectives", "frequenciesListOfSelectives" })
// zwroci true to metoda zwroci widok profilepage bez w/w atrybutow. Zamiast
// przekazywac atrybuty z requesta na request dodalem je do sesji.
public class ProfileController {

	private static final Logger LOG = LogManager.getLogger(ProfileController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ProfileUtils profileUtils;

	@GetMapping("/profile")
	public String showProfile(Model model, Principal principal, Locale locale) {
		if (isModelVaild(model)) {
			UserDetails userDetails = profileUtils.obtainUserDetails(principal);
			profileUtils.addListsOfAttributesToModel(model, locale);
			model.addAttribute("userDetails", userDetails);
		}

		return "profilepage";
	}

	private boolean isModelVaild(Model model) {
		if (model.containsAttribute("org.springframework.validation.BindingResult.userDetails")) {
			BindingResult bindingResult = (BindingResult) model.asMap()
					.get("org.springframework.validation.BindingResult.userDetails");
			if (bindingResult.hasErrors())
				return false;
		}
		return true;
	}

	@PostMapping("/profile")
	public String processProfilePage(RedirectAttributes redirAttributs, @ModelAttribute @Valid UserDetails userDetails,
			BindingResult bindingResult, SessionStatus sessionStatus, Locale locale) {
		if (bindingResult.hasErrors()) {
			LOG.debug("Binding result error occured");
			return "profilepage";
		} else {
			userService.saveOrUpdateUserDetails(userDetails);
			sessionStatus.setComplete();
			redirAttributs.addFlashAttribute("success",
					messageSource.getMessage("profilepage.updateSuccess", null, locale));
		}
		return "redirect:profile";
	}

	@PostMapping("/calculateBmi")
	public String calculateBmi(@ModelAttribute UserDetails userDetails, BindingResult bindingResult, Locale locale) {
		CalculateBMIRequest bmiRequest = new CalculateBMIRequest();
		bmiRequest.setHeight(userDetails.getHeight());
		bmiRequest.setWeight(userDetails.getWeight());
		try {
			CalculateBMIResponse bmiResponse = profileUtils.prepareBMIReponse(userDetails);
			userDetails.setBmi(Double.valueOf(bmiResponse.getCalculatedBMI()));
		} catch (WebServiceIOException ex) {
			LOG.error("Couldn't connect to service", ex);
			bindingResult.addError(
					new FieldError("userDetails", "bmi", messageSource.getMessage("userDetails.bmi", null, locale)));
		}

		return "profilepage";
	}

	@PostMapping("/calculateBmr")
	public String calculateBmr(@Valid @ModelAttribute UserDetails userDetails, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Locale locale) {
		
		//z uwagi na redirect napisz unit testy!!

		redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userDetails", bindingResult);
		redirectAttributes.addFlashAttribute("userDetails", userDetails);
		try {
			if (bindingResult.hasErrors()) {
				LOG.debug("Binding result error occured");
				addSpecifiedErrorToUserDetails(bindingResult, "userDetails.bmr.missingParams", locale);
				return "redirect:profile";
			}
			CalculateBMRResponse bmrResponse = profileUtils.prepareBMRReponse(userDetails);
			userDetails.setBmr(Double.valueOf(bmrResponse.getCalculatedBMRResponse()));
		} catch (WebServiceIOException ex) {
			LOG.error("Couldn't connect to service", ex);
			addSpecifiedErrorToUserDetails(bindingResult, "userDetails.bmr.connectionProblem", locale);
			return "redirect:profile";
		} catch (InvalidParameterException ex) {
			LOG.debug("Missing parameters in BmrRequest");
			addSpecifiedErrorToUserDetails(bindingResult, "userDetails.bmr.missingParams", locale);
			return "redirect:profile";
		}
		return "redirect:profile";
	}

	private void addSpecifiedErrorToUserDetails(BindingResult bindingResult, String specifiedError, Locale locale) {
		bindingResult
				.addError(new FieldError("userDetails", "bmr", messageSource.getMessage(specifiedError, null, locale)));
	}

	@GetMapping("/serviceerror")
	public void throwErrorFromServiceLayer() throws ServiceException {
		userService.throwServiceException();
	}

	@GetMapping("/repositoryerror")
	public void throwErrorFromRepoistoryLayer() throws RepositoryException {
		userService.throwRepositoryException();
	}

}
