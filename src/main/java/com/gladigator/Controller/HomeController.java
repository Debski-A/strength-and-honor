package com.gladigator.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(HttpServletRequest req) {
		return "homepage";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public String handleError404(Exception ex) {
		
		return "pageNotFound";
	}
	
}
