package com.email.exception;

public class EmailException extends RuntimeException{

    public EmailException(String s, Throwable e) {
        super(s, e);
    }
    public EmailException(Throwable e) { super(e);
    }
    public EmailException(String s) { super(s); }
}
