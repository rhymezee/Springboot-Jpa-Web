package com.rhz.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.rhz.web.config.auth.PrincipalDetailService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소 접근에 대한 권한 및 인증을 미리 체크
@EnableWebSecurity // Security 필터 추가 = 설정을 이 파일에서 함.
@Configuration // Bean 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final PrincipalDetailService principalDetailService;
	
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**")
				.permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc") // Security가 해당 주소로 요청된 로그인을 가로챈다.
				.defaultSuccessUrl("/");
	}

}