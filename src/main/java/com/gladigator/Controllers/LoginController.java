package com.gladigator.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login() {
		
		return "loginpage";
	}
	
	@GetMapping("/accessDenied")
	public String accessDenied() {
		return "pagenotfound";
	}
	
	@GetMapping("/createUser")
	public String createUser() {
		return "registerpage";
	}

}
