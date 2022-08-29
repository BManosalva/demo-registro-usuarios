package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.exception.ServiceException;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler{

    /**
	 * 
	 * @param ServiceException ex
	 * @param WebRequest request
	 * @return buildResponseEntity
	 */
	@ExceptionHandler({ ServiceException.class })
	public ResponseEntity<Object> handleSchemaException(ServiceException ex, WebRequest request) {
		return buildResponseEntity(HttpStatus.valueOf(Integer.parseInt(ex.getCode())), ex.getMessage());
	}// closure method

    /**
	 * 
	 * @param HttpStatus status
	 * @param String message
	 * @return ResponseEntity
	 */
	private ResponseEntity<Object> buildResponseEntity(HttpStatus status, String message) {
		ErrorResponse schemaError = new ErrorResponse(status.value(), message);
		return new ResponseEntity<>(schemaError, status);
	}// closure method

}// Class Closure