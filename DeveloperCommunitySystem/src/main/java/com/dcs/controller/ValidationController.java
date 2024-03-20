package com.dcs.controller;
import java.net.http.HttpHeaders;

import java.util.HashMap;
import java.util.Map;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
 
@ControllerAdvice // annotation allows the class to be globally shared across multiple
					// controllers to provide centralized exception handling.
public class ValidationController {
	 // Method to handle validation errors for method arguments
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		// Loop through validation errors and add them to the errors map
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField(); 
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
       });
		// Return a ResponseEntity with the errors and a status of BAD_REQUEST
        // indicating a client error, typically caused by a malformed request.
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST); //indicating that there was a client error, typically caused by a malformed request.
	}
}