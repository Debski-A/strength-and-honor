package com.gladigator.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.gladigator.Daos.UserDao;

@Controller
public class HomeController {
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping("/")
	public String home() {
		
		userDao.printAllUsers();
		return "homepage";
	}
	
	
}
