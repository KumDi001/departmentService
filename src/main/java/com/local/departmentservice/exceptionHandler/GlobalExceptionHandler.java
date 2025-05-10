package com.local.departmentservice.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.local.departmentservice.exceptions.DepartmentAlreadyExistsException;
import com.local.departmentservice.exceptions.DepartmentNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(exception.getMessage());
	}
		
	@ExceptionHandler({ DepartmentNotFoundException.class })
	public ResponseEntity<Object> handleDepartmentNotFoundException(DepartmentNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(exception.getMessage());
	}

	@ExceptionHandler({ DepartmentAlreadyExistsException.class })
	public ResponseEntity<Object> handleDepartmentAlreadyExistsException(DepartmentAlreadyExistsException exception) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(exception.getMessage());
	}
}
