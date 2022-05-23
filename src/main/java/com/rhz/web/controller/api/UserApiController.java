package com.rhz.web.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rhz.web.model.RoleType;
import com.rhz.web.model.User;
import com.rhz.web.model.dto.ResponseDto;
import com.rhz.web.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApiController {
	
	private final UserService userService;
	
	@PostMapping("/api/user")
	public ResponseDto<String> join(@RequestBody User user) {
		user.setRole(RoleType.USER);
		userService.join(user);
		return new ResponseDto<String>(HttpStatus.OK.value(), "회원가입이 완료되었습니다.");
	}
	
	@PostMapping("/api/user/login")
	public ResponseDto<String> login(@RequestBody User user, HttpSession session) {
		User principal = userService.login(user);
		
		if (principal != null) {
			session.setAttribute("principal", principal);
		}
		
		return new ResponseDto<String>(HttpStatus.OK.value(), "로그인이 완료되었습니다.");
	}

}