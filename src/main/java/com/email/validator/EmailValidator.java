package com.email.validator;

import java.util.regex.Pattern;

import com.email.annotation.EmailAnnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class EmailValidator implements ConstraintValidator<EmailAnnotation, String[]> {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);


    @Override
    public boolean isValid(String[] emails, ConstraintValidatorContext context) {
        if (emails == null) {
            return true; // Let @NotNull handle this case
        }

        for (String email : emails) {
            if (!pattern.matcher(email).matches()) {
                return false;
            }
        }
        return true;
    }

}