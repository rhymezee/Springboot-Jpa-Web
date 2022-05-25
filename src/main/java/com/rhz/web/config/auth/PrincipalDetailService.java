package com.rhz.web.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rhz.web.model.User;
import com.rhz.web.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrincipalDetailService implements UserDetailsService {
	
	private final UserRepository userRepository;

	// 스프링 시큐리티가 로그인 요청을 가로챌 때, password는 알아서 비교해주고, 
	// 해당 username이 DB에 있는지 확인해주면 된다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username).orElseThrow(() -> {
			return new UsernameNotFoundException("해당 사용자 " + username + "를 찾을 수 없습니다.");
		});
		return new PrincipalDetail(principal); // 이 때 시큐리티 세션에 User 정보가 UserDetails 타입으로 저장된다.
	}

}