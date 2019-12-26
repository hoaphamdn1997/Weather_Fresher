package com.program.weather.service.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The type User dto.
 */
@Data
@NoArgsConstructor
public class UserDTO {

    private Long userId;

    @Pattern(regexp = "^[a-zA-Z0-9]{8,32}",
            message = "{user.username.msg}")
    @NotEmpty(message = "{not.empty.msg} {user.username.empty}")
    private String userName;

    @Email(regexp = "[a-zA-Z0-9.-_]{1,}@[a-zA-Z.-]{1,}[.]{1}[a-zA-Z]{2,}", message = "{user.email.msg}")
    @NotEmpty(message = "{not.empty.msg} {user.email.empty}")
    private String email;

    @Size(min = 8, max = 32, message = "{user.password.msg}")
    @NotEmpty(message = "{not.empty.msg} {user.password.empty}")
    private String encrytedPassword;

    @Size(min = 8, max = 32, message = "{user.password.msg}")
    private String confirmPassword;

    @NotEmpty(message = "{not.empty.msg} {user.firstname.msg}")
    private String firstName;

    @NotEmpty(message = "{not.empty.msg} {user.lastname.msg}")
    private String lastName;

    private boolean enabled;

    private Set<Long> roles;

    /**
     * Instantiates a new User dto.
     *
     * @param userId    the user id
     * @param userName  the user name
     * @param email     the email
     * @param firstName the first name
     * @param lastName  the last name
     * @param enabled   the enabled
     */
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