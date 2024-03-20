package com.dcs.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	// Exception handler for specific DeveloperCommunitySystemException
	@ExceptionHandler(DeveloperCommunitySystemException.class)
	public ResponseEntity<String> handleCommentNotFoundException(DeveloperCommunitySystemException ex) {
	// Handles DeveloperCommunitySystemException and returns a NOT_FOUND response with the exception message
	return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
  // Generic exception handler for other types of exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex) {
		// Handles generic exceptions and returns a NOT_FOUND response with the exception message
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	// Exception handler for validation errors in request payload
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
		// Handles validation errors and returns a BAD_REQUEST response with a map of field errors
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(error->error.getDefaultMessage())
				.collect(Collectors.toList());
		Map<String, List<String>> errorResponse = new HashMap<>();
		errorResponse.put("errors", errors);
		return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
}