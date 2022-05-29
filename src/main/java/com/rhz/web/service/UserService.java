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
	
	@Transactional
	public void update(User user) {
		// 1. 영속성 컨텍스트에 User 오브젝트를 영속화 시킨다.
		User persistance = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("회원을 찾을 수 없습니다.");
		});
		
		// 2. 영속화된 User 오브젝트를 수정한다.
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setCharacterName(user.getCharacterName());
		
		// 3. update() 메서드 종료 -> 트랜잭션 종료 -> commit
		
		// 4. 영속화된 persistance 오브젝트의 변화가 감지되면 더티체킹이 되어 DB로 flush -> update 완료
	}

}

/*
@Transactional(readOnly = true) // Select할 때도 데이터 정합성을 유지할 수 있음.
public User login(User user) {
	return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
}
*/