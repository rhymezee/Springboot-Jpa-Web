package com.rhz.web.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.rhz.web.model.User;

import lombok.Getter;

@Getter
public class PrincipalDetail implements UserDetails {

	private static final long serialVersionUID = 1826588363331725438L;
	private User user;
	
	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// 계정이 만료되지 않았는지를 리턴한다. (true : 만료안됨)
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// 계정이 잠겨있지 않았는지를 리턴한다. (true : 잠기지 않음)
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// 비밀번호가 만려되지 않았는지를 리턴한다. (true : 만료안됨)
		return true;
	}

	@Override
	public boolean isEnabled() {
		// 계정이 활성화(사용 가능)인지를 리턴한다. (true : 활성화)
		return true;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 계정의 권한을 리턴한다.
		// 권한이 여러개면 for문을 돌려야 한다.
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(() -> {
			return "ROLE_" + user.getRole(); // ROLE_USER, ROLE_ADMIN
		});
		return collectors;
	}

}