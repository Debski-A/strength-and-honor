package com.gladigator.Controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.gladigator.Controllers.RestUrls.ResponseEntityBuilder;

@RestController
public class RestWeatherController {
	
	@Autowired
	private ResponseEntityBuilder responseEntityBuilder;
	
	@GetMapping("/weatherConditions/{city}")
	public ResponseEntity<String> showWeatherConditions(@PathVariable("city") String city) {
		responseEntityBuilder.setAcceptableMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
		
		return responseEntityBuilder.callWeatherApi(city);
	}

}
