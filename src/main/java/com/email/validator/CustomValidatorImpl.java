package com.email.validator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CustomValidatorImpl implements CustomValidator{

    private final Validator validator;

    public <T> void validate(T object, Class<?>... groups) {
        if (object != null) {
            Set<ConstraintViolation<T>> violations = this.validator.validate(object, groups);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException("Validation Failed", violations);
            }
        }
    }
    public CustomValidatorImpl(final Validator validator) {
        this.validator = validator;
    }
}
