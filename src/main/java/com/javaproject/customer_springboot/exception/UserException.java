package com.javaproject.customer_springboot.exception;

public class UserException extends RuntimeException { 
	
	private static final long serialVersionUID = 1L;

	public UserException() {
		super();
	}
	
	public UserException(String errorMessage) {
		super(errorMessage);
	}
	
	public UserException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}