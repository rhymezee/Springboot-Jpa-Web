package com.rhz.web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CMResponseDto<T> {
	
	private int code; // 1은 성공, -1은 실패
	private String message;
	private T data;

}