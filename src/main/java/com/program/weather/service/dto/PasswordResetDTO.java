package com.program.weather.service.dto;

import com.program.weather.common.validator.PasswordMatches;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@PasswordMatches
public class PasswordResetDTO {

	@NotEmpty
	private String password;

	@NotEmpty
	private String confirmPassword;

	@NotEmpty
	private String token;
}
