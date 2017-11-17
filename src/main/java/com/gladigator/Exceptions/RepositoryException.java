package com.gladigator.Exceptions;

public class RepositoryException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RepositoryException() {}
	
	public RepositoryException(String message) {
		super(message);
	}
	
	public RepositoryException(String message, Exception ex) {
		super(message, ex);
	}
}
