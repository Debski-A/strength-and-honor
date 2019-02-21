package com.gladigator.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.gladigator.Services.PostService;

@Controller
public class HomeController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping("/")
	public String showHomePage() {

		return "homepage";
	}
	
	@GetMapping("/weather")
	public String showWeatherPage() {

		return "weatherpage";
	}
	
	@PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String saveDivContentToDatabase(@RequestBody String body) {
		System.out.println(body);
		
		return "homepage";
	}
	
}
