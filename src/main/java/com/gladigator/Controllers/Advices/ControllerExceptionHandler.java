package com.gladigator.Controllers.Advices;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public String handleError404(Exception ex) {
			
		return "pagenotfound";
	}

}
