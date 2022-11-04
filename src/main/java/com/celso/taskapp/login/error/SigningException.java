package com.celso.taskapp.login.error;

public class SigningException extends RuntimeException {

    public SigningException() {
        super();
    }

    public SigningException(final String message) {
        super(message);
    }
}
