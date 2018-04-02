package com.gladigator.Controllers.Utils;

import java.security.Principal;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.gladigator.Entities.User;
import com.gladigator.Entities.UserDetails;
import com.gladigator.Services.UserDetailsService;
import com.gladigator.Services.UserService;

@Component
public class ProfileUtils {
	
	private static final Logger LOG = LogManager.getLogger(ProfileUtils.class);
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;

	public UserDetails obtainUserDetails(Principal principal) {
		String authenticatedUserUsername = principal.getName();
		User user = userService.getUserByUsername(authenticatedUserUsername);
		
		//Jesli User ma juz jakies UserDetails
		UserDetails userDetailsFromUserObtainedForUpdate = user.getUserDetails();
		LOG.debug("UserDetails from user = {}", userDetailsFromUserObtainedForUpdate);
		if (userDetailsFromUserObtainedForUpdate != null) {
			user.getUserDetails().setUser(user);
			return user.getUserDetails();
		}
		
		//Jesli User pierwszy raz ustawia UserDetails
		UserDetails emptyUserDetails = new UserDetails();
		emptyUserDetails.setUser(user);
		return emptyUserDetails;
	}
	
	public void addListsOfAttributesToModel(Model model, Locale locale) {
		Map<String, List<?>> listOfSelectives = userDetailsService.getSelectiveDetailsAsMap(locale);
		model.addAllAttributes(listOfSelectives);
	}
	
	

}
