package com.gladigator.Controllers.Advices;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.gladigator.Exceptions.RepositoryException;
import com.gladigator.Exceptions.ServiceException;

@ControllerAdvice
public class ApplicationExceptionsHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public String handleError404(Exception ex) {
		return "pagenotfound";
	}

	@ExceptionHandler({ ServiceException.class, RepositoryException.class })
	public ModelAndView handleServiceException(Exception ex) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("errorMessage", ex.getMessage());
		mav.setViewName("internalerrorpage");
		return mav;
	}

}
