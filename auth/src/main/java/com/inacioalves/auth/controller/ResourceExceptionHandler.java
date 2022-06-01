package com.inacioalves.auth.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.inacioalves.auth.exception.objectNotFoundException;
import com.inacioalves.auth.exception.DataIntegrityException;
import com.inacioalves.auth.model.StandarError;


@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(objectNotFoundException.class)
	public ResponseEntity<StandarError>objectNotFound(objectNotFoundException e, HttpServletRequest request){
		StandarError err = new StandarError(HttpStatus.NOT_FOUND.value(),e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
		
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandarError>DataIntegrityException(DataIntegrityException e, HttpServletRequest request){
		StandarError err = new StandarError(HttpStatus.BAD_REQUEST.value(),e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

}
