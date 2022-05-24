package com.rhz.web.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rhz.web.model.RoleType;
import com.rhz.web.model.User;
import com.rhz.web.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. (IoC)
public class UserService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder;
	
	// Oracle은 Read Committed라 데이터의 정합성이 보장되지 않음.
	// MySQL은 Repeatable Read라 트랜잭션이 유지되는 동안 데이터의 정합성이 보장됨.
	// (본인 트랜잭션보다 전에 시작된 트랜잭션의 Undo 테이블을 Select하기 때문)
	@Transactional 
	public void join(User user) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}

}

/*
@Transactional(readOnly = true) // Select할 때도 데이터 정합성을 유지할 수 있음.
public User login(User user) {
	return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
}
*/