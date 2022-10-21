package com.celso.taskApp.login.services;

import com.celso.taskApp.login.payload.request.LoginRequest;
import com.celso.taskApp.login.payload.request.SignupRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

  public UserDetailsImpl signing(LoginRequest loginRequest);

  public void signup(SignupRequest request);

}
