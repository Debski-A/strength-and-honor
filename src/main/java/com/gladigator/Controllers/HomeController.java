package com.gladigator.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.gladigator.Daos.UserDao;
import com.gladigator.Daos.UserDaoImpl;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		
		return "homepage";
	}
	
	
}
