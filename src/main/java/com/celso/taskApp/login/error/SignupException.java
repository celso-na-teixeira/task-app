package com.celso.taskApp.login.error;

public class SignupException extends RuntimeException {

    public SignupException() {
        super();
    }

    public SignupException(String message) {
        super(message);
    }
}
