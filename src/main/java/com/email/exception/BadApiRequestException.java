package com.email.exception;

public class BadApiRequestException extends RuntimeException {
    private final String customStatusCode;

    public BadApiRequestException() {
        super("Bad Request !!");
        this.customStatusCode = null;
    }

    public BadApiRequestException(String message) {
        super(message);
        this.customStatusCode = null;
    }

    public BadApiRequestException(String message, String customStatusCode) {
        super(message);
        this.customStatusCode = customStatusCode;
    }

    public BadApiRequestException(String message, Throwable cause) {
        super(message, cause);
        this.customStatusCode = null;
    }

    public BadApiRequestException(String message, String customStatusCode, Throwable cause) {
        super(message, cause);
        this.customStatusCode = customStatusCode;
    }

    public BadApiRequestException(Throwable cause) {
        super(cause);
        this.customStatusCode = null;
    }

    public String getCustomStatusCode() {
        return this.customStatusCode;
    }
}