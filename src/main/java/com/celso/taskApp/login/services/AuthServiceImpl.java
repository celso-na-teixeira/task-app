package com.celso.taskApp.login.services;

import com.celso.taskApp.login.error.SignupException;
import com.celso.taskApp.login.models.ERole;
import com.celso.taskApp.login.models.Role;
import com.celso.taskApp.login.models.User;
import com.celso.taskApp.login.payload.request.LoginRequest;
import com.celso.taskApp.login.payload.request.SignupRequest;
import com.celso.taskApp.login.payload.response.MessageResponse;
import com.celso.taskApp.login.repository.RoleRepository;
import com.celso.taskApp.login.repository.UserRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  UserRepository userRepository;

  @Autowired
  AuthenticationManager authenticationManager;


  @Override
  public UserDetailsImpl signing(LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetail = (UserDetailsImpl) authentication.getPrincipal();

    return userDetail;
  }

  @Override
  public void signup(SignupRequest request) throws SignupException {

    if (userRepository.existsByUsername(request.getUsername())){
      throw new SignupException("Error: Username is already taken");
    } else if (userRepository.existsByEmail(request.getEmail())) {
      throw new SignupException("Error: Email is already in use!");
    }

    User user = new User(request.getUsername(), request.getEmail(), passwordEncoder.encode(request.getPassword()));

    Set<String> stringRoleSet = request.getRole();
    Set<Role> roleSet = new HashSet<>();

    if (stringRoleSet == null){
      Role userRole = roleRepository.findByName(ERole.USER).orElseThrow(() -> new SignupException("Error: Role is not found."));
      roleSet.add(userRole);
    }else{
      stringRoleSet.forEach(role -> {
        switch (role){
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.ADMIN).orElseThrow(() -> new SignupException("Error: Role is not found."));
            roleSet.add(adminRole);
            break;
          default:
            Role userRole = roleRepository.findByName(ERole.USER).orElseThrow(() -> new SignupException("Error: Role is not found."));
            roleSet.add(userRole);
        }
      });
    }

    user.setRoles(roleSet);
    userRepository.save(user);

  }
}
