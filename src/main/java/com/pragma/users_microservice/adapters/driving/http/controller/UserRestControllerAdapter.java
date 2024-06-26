package com.pragma.users_microservice.adapters.driving.http.controller;

import com.pragma.users_microservice.adapters.driving.http.dto.request.AddUserRequest;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
@PreAuthorize("denyAll()")
@Tag(name = "Register Role Admin", description = "Endpoints for register Admin")
public class UserRestControllerAdapter {
  private final IUserServicePort userServicePort;
  private final IRoleServicePort roleServicePort;
  private final IUserRequestMapper userRequestMapper;


  @PostMapping("/")
  @PreAuthorize("permitAll()")
  @Operation(summary = "Endpoint to add a new user with Role Admin", security = @SecurityRequirement(name = "bearerAuth"))
  public ResponseEntity<Void> addUser(@Valid @RequestBody AddUserRequest request) {

    String roleRequest = roleServicePort.getRoleNameById(request.getRole().getId());

    if (RoleConstants.ADMIN.equals(roleRequest)) {
      userServicePort.saveUser(userRequestMapper.addRequestToUser(request));
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

  }

}
