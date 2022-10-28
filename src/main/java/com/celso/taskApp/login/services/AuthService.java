package com.celso.taskApp.login.services;

import org.springframework.security.authentication.BadCredentialsException;

import com.celso.taskApp.login.payload.request.LoginRequest;
import com.celso.taskApp.login.payload.request.SignupRequest;

public interface AuthService {

    UserDetailsImpl signing(LoginRequest loginRequest) throws BadCredentialsException;

    void signup(SignupRequest request);

}
