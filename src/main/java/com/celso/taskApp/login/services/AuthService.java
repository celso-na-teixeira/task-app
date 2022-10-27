package com.celso.taskApp.login.services;

import com.celso.taskApp.login.error.SigningException;
import com.celso.taskApp.login.payload.request.LoginRequest;
import com.celso.taskApp.login.payload.request.SignupRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

  UserDetailsImpl signing(LoginRequest loginRequest) throws BadCredentialsException;

  void signup(SignupRequest request);

}
