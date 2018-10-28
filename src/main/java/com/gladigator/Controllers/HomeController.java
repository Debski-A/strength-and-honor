package com.gladigator.Controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gladigator.Controllers.Utils.ResponseEntityBuilder;

@Controller
public class HomeController {
	
	@Autowired
	private ResponseEntityBuilder responseEntityBuilder;

	@GetMapping("/")
	public String showHomePage() {

		return "homepage";
	}
	
	@GetMapping("/weather")
	public String showWeatherPage() {

		return "weatherpage";
	}
	
	@PostMapping("/weather") 
	public String processWeatherPage(@RequestParam("inputCity") String inputCity, Model model) {
		responseEntityBuilder.setAcceptableMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
		
		model.addAttribute("weatherEntity", responseEntityBuilder.createResponseEntity(inputCity));
		return "weatherpage";
	}
	
}
