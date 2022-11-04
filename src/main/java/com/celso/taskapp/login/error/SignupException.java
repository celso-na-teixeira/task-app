package com.celso.taskapp.login.error;

public class SignupException extends RuntimeException {

    public SignupException() {
        super();
    }

    public SignupException(String message) {
        super(message);
    }
}
