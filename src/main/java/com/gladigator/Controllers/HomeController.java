package com.gladigator.Controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String showHomePage() {

		return "homepage";
	}
	
	@GetMapping("/weather")
	public String showWeatherPage() {

		return "weatherpage";
	}
	
	@GetMapping("/post")
	public String showPostPage(Model model) {
		model.addAttribute("postInvoked", true);
		
		return "homepage";
	}
	
	@PostMapping("/save")
	public String saveDivContentToDatabase() {
		System.out.println("ss");
		
		return "homepage";
	}
	
}
