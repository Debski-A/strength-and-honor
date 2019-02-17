package com.gladigator.Controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
	
	@PostMapping(value = "/saveDivContentToDatabase", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String saveDivContentToDatabase(@RequestBody String encodedContent) {
		System.out.println(encodedContent);
		
		return "homepage";
	}
	
}
