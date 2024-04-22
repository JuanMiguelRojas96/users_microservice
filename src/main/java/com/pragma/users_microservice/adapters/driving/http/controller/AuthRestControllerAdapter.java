package com.pragma.users_microservice.adapters.driving.http.controller;

import com.pragma.users_microservice.adapters.driven.jpa.mysql.adapter.UserDetailServiceImpl;
import com.pragma.users_microservice.adapters.driving.http.dto.request.AddUserRequest;
import com.pragma.users_microservice.adapters.driving.http.dto.request.AuthLoginRequest;
import com.pragma.users_microservice.adapters.driving.http.dto.response.AuthResponse;
import com.pragma.users_microservice.adapters.driving.http.mapper.IUserRequestMapper;
import com.pragma.users_microservice.domain.api.IUserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
@PreAuthorize("denyAll()")
public class AuthRestControllerAdapter {

  private final UserDetailServiceImpl userDetailServiceImpl;
  private final IUserServicePort userServicePort;
  private final IUserRequestMapper userRequestMapper;


  @PostMapping("/register")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public ResponseEntity<Void> addUser(@Valid @RequestBody AddUserRequest request) {
    userServicePort.saveUser(userRequestMapper.addRequestToUser(request));
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/login")
  @PreAuthorize("permitAll()")
  public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthLoginRequest request) {
    return ResponseEntity.ok(userDetailServiceImpl.login(request));

  }

  @GetMapping("/hello")
  @PreAuthorize("hasAuthority('ROLE_TUTOR')")
  public ResponseEntity<String> hello() {
    return ResponseEntity.ok("hello");
  }
}
