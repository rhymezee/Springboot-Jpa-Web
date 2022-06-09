package com.rhz.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/user/updateForm")
	public String updateForm() {
		return "user/updateForm";
	}

}

/*
@PostMapping("/auth/joinProc")
public String join(@Valid JoinRequestDto joinRequestDto, BindingResult bindingResult) {
	if (bindingResult.hasErrors()) {
		Map<String, String> errorMap = new HashMap<>();
		
		for (FieldError error : bindingResult.getFieldErrors()) {
			errorMap.put(error.getField(), error.getDefaultMessage());
		}
		
		throw new CustomValidationException("유효성 검사 실패", errorMap);
	}
	else {
		User user = joinRequestDto.toEntity();
		userService.join(user);
		return "user/loginForm";
	}
}
*/