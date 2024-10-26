package com.email.validator;

public interface CustomValidator {
    <T> void validate(T object, Class<?>... groups);
}
