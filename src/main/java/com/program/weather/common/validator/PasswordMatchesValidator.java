package com.program.weather.common.validator;


import com.program.weather.service.dto.PasswordResetDTO;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * The type Password matches validator.
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        PasswordResetDTO user = (PasswordResetDTO) obj;
        return user.getPassword().equals(user.getConfirmPassword());
    }
}
