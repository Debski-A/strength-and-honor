package com.gladigator.Controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.gladigator.Entities.User;
import com.gladigator.Entities.UserDetails;

@Component
public class ProfileUtils {
	
	private static final Logger LOG = LogManager.getLogger(ProfileUtils.class);

	public UserDetails obtainUserDetails(User user) {
		UserDetails userDetailsFromUser = user.getUserDetails();
		LOG.debug("UserDetails from user = {}", userDetailsFromUser);
		if (userDetailsFromUser != null) {
			return user.getUserDetails();
		}
		return new UserDetails();
	}

	public void addListsOfAttributesToModel(Model model) {
		// TODO Auto-generated method stub
		
	}

}
