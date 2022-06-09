package com.rhz.web.model.dto.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.rhz.web.model.User;

import lombok.Data;

@Data
public class JoinRequestDto {
	
	@Size(min = 2, max = 20)
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	@Size(min = 2, max = 20)
	@NotBlank
	private String characterName;
	
	public User toEntity() {
		return User.builder()
				.username(username)
				.password(password)
				.characterName(characterName)
				.build();
	}

}