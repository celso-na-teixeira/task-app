package com.celso.taskApp.login.controllers;

import com.celso.taskApp.login.error.SignupException;
import com.celso.taskApp.login.payload.request.LoginRequest;
import com.celso.taskApp.login.payload.request.SignupRequest;
import com.celso.taskApp.login.payload.response.MessageResponse;
import com.celso.taskApp.login.payload.response.UserInfoResponse;
import com.celso.taskApp.login.services.AuthService;
import com.celso.taskApp.login.services.UserDetailsImpl;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  static Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

  @Autowired
  AuthService authService;


  @PostMapping("/signing")
  public ResponseEntity<?> signing(@Valid @RequestBody LoginRequest loginRequest) {
    LOGGER.info("signing user " + loginRequest.getUsername());
    UserDetailsImpl userDetails = null;
    try {
      userDetails = authService.signing(loginRequest);
    } catch (BadCredentialsException e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
    List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
        .collect(
            Collectors.toList());

    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE)
        .body(new UserInfoResponse(userDetails.getId(), userDetails.getUsername(),
            userDetails.getEmail(), roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest request) {
    try {
      authService.signup(request);
    } catch (SignupException ex) {
      LOGGER.error("signup fail " + ex);
      return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage()));
    }
    return ResponseEntity.ok(new MessageResponse("User registered sucessfully!"));
  }

  public ResponseEntity<?> logout() {
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, "")
        .body(new MessageResponse("You've been signed out!"));
  }

}
