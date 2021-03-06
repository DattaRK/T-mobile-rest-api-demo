package com.javatpoint.globalException;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class CustomExceptionHandle {
	
	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<CustomErrorResponse> ResourceNotFoundException(NotFoundException e){
		 CustomErrorResponse error = new CustomErrorResponse("NOT_FOUND_ERROR", e.getMessage());
		 error.setTimestamp(LocalDateTime.now());
		 error.setStatus((HttpStatus.NOT_FOUND.value()));
		 return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	
}
