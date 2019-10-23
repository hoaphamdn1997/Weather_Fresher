package com.program.weather.service.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserDTO {

    private Long userId;

    @Pattern(regexp = "^[a-zA-Z0-9]{8,32}",
            message = "Length 8 - 32 char")
    @NotEmpty(message = "Please provide your user name")
    private String userName;

    @Email(regexp = "[a-zA-Z0-9.-_]{1,}@[a-zA-Z.-]{1,}[.]{1}[a-zA-Z]{2,}",message = "EXABLE@ABC.com")
    @NotEmpty(message = "Please provide your email")
    private String email;

    @Size(min = 8, max = 32, message = "Password must have 8 - 32 characters")
    @NotEmpty(message = "Please provide your password")
    private String encrytedPassword;

    @Size(min = 8, max = 32, message = "{user.password.msg}")
    private String confirmPassword;

    @NotEmpty(message = "Please provide your first name")
    private String firstName;

    @NotEmpty(message = "Please provide your Last name")
    private String lastName;

    private boolean enabled;

    private Set<Long> roles;

    public UserDTO(Long userId, String userName, String email, String firstName, String lastName, boolean enabled) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
    }


}