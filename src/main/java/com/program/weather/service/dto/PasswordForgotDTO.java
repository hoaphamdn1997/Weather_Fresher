package com.program.weather.service.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * The type Password forgot dto.
 */
@Data
public class PasswordForgotDTO {

    @Email
    @NotEmpty
    private String email;
}
