package com.celso.taskapp.login.services;

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

import com.celso.taskapp.login.error.Error;
import com.celso.taskapp.login.error.SignupException;
import com.celso.taskapp.login.models.ERole;
import com.celso.taskapp.login.models.Role;
import com.celso.taskapp.login.models.User;
import com.celso.taskapp.login.payload.request.LoginRequest;
import com.celso.taskapp.login.payload.request.SignupRequest;
import com.celso.taskapp.login.repository.RoleRepository;
import com.celso.taskapp.login.repository.UserRepository;

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

    @Override
    public UserDetailsImpl signing(final LoginRequest loginRequest) throws BadCredentialsException {
        LOGGER.info("signing user " + loginRequest.getUsername());
        UserDetailsImpl userDetail = null;

        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        userDetail = (UserDetailsImpl) authentication.getPrincipal();
        LOGGER.info("signing succeed");
        return userDetail;
    }

    @Override
    public void signup(final SignupRequest request) throws SignupException {
        LOGGER.info("signup user " + request.getUsername());
        if (userRepository.existsByUsername(request.getUsername())) {
            LOGGER.error(Error.USERNAME_TAKEN.getMessage());
            throw new SignupException(Error.USERNAME_TAKEN.getMessage());
        } else if (userRepository.existsByEmail(request.getEmail())) {
            LOGGER.error(Error.EMAIL_TAKEN.getMessage());
            throw new SignupException(Error.EMAIL_TAKEN.getMessage());
        }

        final User user = new User(request.getUsername(), request.getEmail(), passwordEncoder.encode(request.getPassword()));

        final Set<String> stringRoleSet = request.getRole();
        final Set<Role> roleSet = new HashSet<>();

        if (stringRoleSet == null) {
            final Role userRole = roleRepository.findByName(ERole.USER).orElseThrow(() -> new SignupException(Error.ROLE_NOT_FOUND.getMessage()));
            roleSet.add(userRole);
        } else {
            stringRoleSet.forEach(role -> {
                if ("admin".equals(role)) {
                    final Role adminRole = roleRepository.findByName(ERole.ADMIN)
                            .orElseThrow(() -> new SignupException(Error.ROLE_NOT_FOUND.getMessage()));
                    roleSet.add(adminRole);
                } else {
                    final Role userRole = roleRepository.findByName(ERole.USER)
                            .orElseThrow(() -> new SignupException(Error.ROLE_NOT_FOUND.getMessage()));
                    roleSet.add(userRole);
                }
            });
        }

        user.setRoles(roleSet);
        userRepository.save(user);
        LOGGER.info("signup succeed");
    }
}
