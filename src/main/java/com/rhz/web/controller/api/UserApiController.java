package com.rhz.web.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rhz.web.model.User;
import com.rhz.web.model.dto.ResponseDto;
import com.rhz.web.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApiController {
	
	private final UserService userService;
	private final AuthenticationManager authenticationManager;

	@PostMapping("/auth/joinProc")
	public ResponseDto<String> join(@RequestBody User user) {
		// JSON 데이터를 받으려면 @RequestBody 붙여줘야 한다.
		// 아니면 key = value 형태인 x-www-form-urlencoded 방식만 받을 수 있다.
		userService.join(user);
		return new ResponseDto<String>(HttpStatus.OK.value(), "회원가입이 완료되었습니다.");
	}
	
	@PutMapping("/user")
	public ResponseDto<String> update(@RequestBody User user) { 
		userService.update(user);
		// 지금 이 상태에서는 트랜잭션이 종료되기 때문에 DB에 값은 변경이 되었지만,
		// 세션 값은 변경되지 않은 상태이기 때문에, 직접 세션 값을 변경해줘야 한다.
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseDto<String>(HttpStatus.OK.value(), "회원수정이 완료되었습니다.");
	}

}

/*
@PostMapping("/api/user/login")
public ResponseDto<String> login(@RequestBody User user, HttpSession session) {
	User principal = userService.login(user);
	
	if (principal != null) {
		session.setAttribute("principal", principal);
	}
	
	return new ResponseDto<String>(HttpStatus.OK.value(), "로그인이 완료되었습니다.");
}
*/