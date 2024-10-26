package com.email.builder.dto;

import java.io.Serializable;

public class FieldError implements Serializable {
    private static final long serialVersionUID = 5961222887541847592L;
    private String field;
    private String error;
    private String errorCode;

    public FieldError(String field, String error) {
        this.field = field;
        this.error = error;
    }

    public String getField() {
        return this.field;
    }

    public String getError() {
        return this.error;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setField(final String field) {
        this.field = field;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }

    public FieldError(final String field, final String error, final String errorCode) {
        this.field = field;
        this.error = error;
        this.errorCode = errorCode;
    }

    public FieldError() {
    }
}
