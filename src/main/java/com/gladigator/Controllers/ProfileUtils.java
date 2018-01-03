package com.gladigator.Controllers;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.gladigator.Entities.User;
import com.gladigator.Entities.UserDetails;
import com.gladigator.Services.UserDetailsService;

@Component
public class ProfileUtils {
	
	private static final Logger LOG = LogManager.getLogger(ProfileUtils.class);
	
	@Autowired
	private UserDetailsService userDetailsService;

	public UserDetails obtainUserDetails(User user) {
		UserDetails userDetailsFromUser = user.getUserDetails();
		LOG.debug("UserDetails from user = {}", userDetailsFromUser);
		if (userDetailsFromUser != null) {
			return user.getUserDetails();
		}
		return new UserDetails();
	}

	public void addListsOfAttributesToModel(Model model) {
		Map<String, List<?>> listOfSelectives = userDetailsService.getSelectiveDetailsAsMap();
		model.addAllAttributes(listOfSelectives);
	}

}
