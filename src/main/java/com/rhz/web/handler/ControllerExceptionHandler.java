package com.rhz.web.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.rhz.web.handler.ex.CustomValidationApiException;
import com.rhz.web.handler.ex.CustomValidationException;
import com.rhz.web.handler.util.Script;
import com.rhz.web.model.dto.CMResponseDto;

@ControllerAdvice
@RestController
public class ControllerExceptionHandler {
	
	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
		return Script.back(e.getErrorMap().toString());
	}
	
	@ExceptionHandler(CustomValidationApiException.class)
	public ResponseEntity<?> validationApiException(CustomValidationApiException e) {
		return new ResponseEntity<>(new CMResponseDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
	}

}