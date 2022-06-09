package com.rhz.web.handler.ex;

import java.util.Map;

public class CustomValidationApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Map<String, String> errorMap;
	
	public CustomValidationApiException(String message) {
		super(message);
	}
	
	public CustomValidationApiException(String message, Map<String, String> errorMap) {
		super(message); // message에 대한 getter는 부모인 RuntimeException의 부모의 부모가 getter를 가지고 있다.
		this.errorMap = errorMap;
	}
	
	public Map<String, String> getErrorMap() {
		return errorMap;
	}

}