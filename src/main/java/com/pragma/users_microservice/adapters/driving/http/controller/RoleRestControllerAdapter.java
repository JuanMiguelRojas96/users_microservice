package com.pragma.users_microservice.adapters.driving.http.controller;

import com.pragma.users_microservice.adapters.driving.http.dto.request.AddRoleRequest;
import com.pragma.users_microservice.adapters.driving.http.mapper.IRoleRequestMapper;
import com.pragma.users_microservice.domain.api.IRoleServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@Validated
@Tag(name = "Add Role", description = "Endpoints for add Role")
public class RoleRestControllerAdapter {

  private final IRoleServicePort roleServicePort;
  private final IRoleRequestMapper roleRequestMapper;

  @PostMapping("/")
  @Operation(summary = "Endpoint to add a new role")
  public ResponseEntity<Void> addRole(@Valid @RequestBody AddRoleRequest request) {
    roleServicePort.saveRole(roleRequestMapper.addRequestToRole(request));
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }


}
