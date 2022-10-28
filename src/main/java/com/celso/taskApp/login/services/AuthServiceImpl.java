package com.celso.taskApp.login.services;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.celso.taskApp.login.error.SignupException;
import com.celso.taskApp.login.models.ERole;
import com.celso.taskApp.login.models.Role;
import com.celso.taskApp.login.models.User;
import com.celso.taskApp.login.payload.request.LoginRequest;
import com.celso.taskApp.login.payload.request.SignupRequest;
import com.celso.taskApp.login.repository.RoleRepository;
import com.celso.taskApp.login.repository.UserRepository;
import com.celso.taskApp.login.security.JwtUtils;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public UserDetailsImpl signing(LoginRequest loginRequest) throws BadCredentialsException {
        UserDetailsImpl userDetail = null;

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        userDetail = (UserDetailsImpl) authentication.getPrincipal();

        return userDetail;
    }

    @Override
    public void signup(SignupRequest request) throws SignupException {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new SignupException("Error: Username is already taken");
        } else if (userRepository.existsByEmail(request.getEmail())) {
            throw new SignupException("Error: Email is already in use!");
        }

        User user = new User(request.getUsername(), request.getEmail(), passwordEncoder.encode(request.getPassword()));

        Set<String> stringRoleSet = request.getRole();
        Set<Role> roleSet = new HashSet<>();

        if (stringRoleSet == null) {
            Role userRole = roleRepository.findByName(ERole.USER).orElseThrow(() -> new SignupException("Error: Role is not found."));
            roleSet.add(userRole);
        } else {
            stringRoleSet.forEach(role -> {
                if ("admin".equals(role)) {
                    Role adminRole = roleRepository.findByName(ERole.ADMIN).orElseThrow(() -> new SignupException("Error: Role is not found."));
                    roleSet.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByName(ERole.USER).orElseThrow(() -> new SignupException("Error: Role is not found."));
                    roleSet.add(userRole);
                }
            });
        }

        user.setRoles(roleSet);
        userRepository.save(user);

    }
}
