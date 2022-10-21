package com.celso.taskApp.login.payload.response;

import java.util.ArrayList;
import java.util.List;

public class UserInfoResponse {

    private Long Id;
    private String username;
    private String email;
    private List<String> roles = new ArrayList<>();

    public UserInfoResponse(Long id, String username, String email, List<String> roles) {
        Id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() {
        return Id;
    }


    public void setId(Long id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
