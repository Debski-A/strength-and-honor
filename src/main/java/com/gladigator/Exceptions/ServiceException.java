package com.gladigator.Exceptions;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServiceException() {}
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(String message, Exception ex) {
		super(message, ex);
	}
}