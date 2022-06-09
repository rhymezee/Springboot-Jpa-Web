package com.rhz.web.model.dto.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.rhz.web.model.User;

import lombok.Data;

@Data
public class UserUpdateRequestDto {
	
	@NotBlank
	private String password;
	
	@Size(min = 2, max = 20)
	@NotBlank
	private String characterName;
	
	public User toEntity() {
		return User.builder()
				.password(password)
				.characterName(characterName)
				.build();
	}

}