package com.gladigator.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String showLoginPage() {
		
		return "loginpage";
	}
	
	@GetMapping("/accessDenied")
	public String showPageNotFound() {
		return "pagenotfound";
	}
	

}
