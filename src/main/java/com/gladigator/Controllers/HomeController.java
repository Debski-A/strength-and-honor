package com.gladigator.Controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

	@GetMapping("/weatherConditions")
	public ResponseEntity<String> showWeatherConditions() {
		responseEntityBuilder.setAcceptableMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));

		return responseEntityBuilder.createResponseEntity();
	}

}
