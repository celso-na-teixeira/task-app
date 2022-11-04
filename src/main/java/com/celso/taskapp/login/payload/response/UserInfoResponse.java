package com.celso.taskapp.login.payload.response;

import java.util.ArrayList;
import java.util.List;

public class UserInfoResponse {

    private Long id;
    private String username;
    private String email;
    private List<String> roles = new ArrayList<>();

    public UserInfoResponse(final Long id, final String username, final String email, final List<String> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(final List<String> roles) {
        this.roles = roles;
    }
}
