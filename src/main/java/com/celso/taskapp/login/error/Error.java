package com.celso.taskapp.login.error;

public enum Error {
    USERNAME_TAKEN(0, "Username is already taken"), EMAIL_TAKEN(1, "Email is already in use!"), ROLE_NOT_FOUND(2, "Role not found");

    private final int code;
    private final String message;

    Error(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return code + ": " + message;
    }
}
