package com.pragma.users_microservice.adapters.driving.http.controller;

import com.pragma.users_microservice.adapters.driven.jpa.mysql.adapter.UserDetailServiceImpl;
import com.pragma.users_microservice.adapters.driving.http.dto.request.AddUserRequest;
import com.pragma.users_microservice.adapters.driving.http.dto.request.AuthLoginRequest;
import com.pragma.users_microservice.adapters.driving.http.dto.response.AuthResponse;
import com.pragma.users_microservice.adapters.driving.http.exceptions.RoleConstants;
import com.pragma.users_microservice.adapters.driving.http.mapper.IUserRequestMapper;
import com.pragma.users_microservice.domain.api.IRoleServicePort;
import com.pragma.users_microservice.domain.api.IUserServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
@PreAuthorize("denyAll()")
@Tag(name = "Login and Register", description = "Endpoints for user authentication and register")
public class AuthRestControllerAdapter {

  private final UserDetailServiceImpl userDetailServiceImpl;
  private final IRoleServicePort roleServicePort;
  private final IUserServicePort userServicePort;
  private final IUserRequestMapper userRequestMapper;


  @PostMapping("/register")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TUTOR')")
  @Operation(summary = "Allows registered users (admins or tutors) to add new users")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<Void> addUser(@Valid @RequestBody AddUserRequest request, Authentication authentication) {

    String authenticatedUser = authentication.getAuthorities().iterator().next().getAuthority();
    String roleRequest = roleServicePort.getRoleNameById(request.getRole().getId());

    if (RoleConstants.TUTOR.equals(roleRequest) && RoleConstants.ROLE_ADMIN.equals(authenticatedUser)) {

      userServicePort.saveUser(userRequestMapper.addRequestToUser(request));
      return ResponseEntity.status(HttpStatus.CREATED).build();

    }
    if (RoleConstants.ESTUDIANTE.equals(roleRequest)
        && (RoleConstants.ROLE_TUTOR.equals(authenticatedUser)
              || RoleConstants.ROLE_ADMIN.equals(authenticatedUser))) {

      userServicePort.saveUser(userRequestMapper.addRequestToUser(request));
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
  }

  @PostMapping("/login")
  @PreAuthorize("permitAll()")
  @Operation(summary = "Endpoint for user login")
  public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthLoginRequest request) {
    return ResponseEntity.ok(userDetailServiceImpl.login(request));

  }

}
