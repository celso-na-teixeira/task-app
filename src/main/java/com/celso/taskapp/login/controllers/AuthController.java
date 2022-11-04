package com.celso.taskapp.login.controllers;

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

import com.celso.taskapp.login.error.SignupException;
import com.celso.taskapp.login.payload.request.LoginRequest;
import com.celso.taskapp.login.payload.request.SignupRequest;
import com.celso.taskapp.login.payload.response.MessageResponse;
import com.celso.taskapp.login.payload.response.UserInfoResponse;
import com.celso.taskapp.login.services.AuthService;
import com.celso.taskapp.login.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthService authService;

    @PostMapping("/signing")
    public ResponseEntity<?> signing(@Valid @RequestBody final LoginRequest loginRequest) {
        LOGGER.info("signing user ", loginRequest.getUsername());
        UserDetailsImpl userDetails = null;
        try {
            userDetails = authService.signing(loginRequest);
        } catch (final BadCredentialsException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
        final List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE)
                .body(new UserInfoResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody final SignupRequest request) {
        try {
            authService.signup(request);
        } catch (final SignupException ex) {
            LOGGER.error("signup fail ", ex);
            return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage()));
        }
        return ResponseEntity.ok(new MessageResponse("User registered sucessfully!"));
    }

    public ResponseEntity<?> logout() {
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, "").body(new MessageResponse("You've been signed out!"));
    }

}
