package com.dcs.exception;

import org.springframework.http.HttpStatus;

public class DeveloperCommunitySystemException extends Exception{
	
	private static final long serialVersionUID = 1L;
	// Constructor with a message to describe the exception
    public DeveloperCommunitySystemException(String str) {
		super(str);
	}
    // Constructor with a message and HTTP status code to describe the exception
	public DeveloperCommunitySystemException(String str, HttpStatus internalServerError) {
		// TODO Auto-generated constructor stub
		super(str);
	}
	 // Constructor with an HTTP status code to describe the exception
	public DeveloperCommunitySystemException(HttpStatus internalServerError) {
		// TODO Auto-generated constructor stub
		super();
	}

	
}
