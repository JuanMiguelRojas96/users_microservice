package com.pragma.users_microservice.adapters.driving.http.controller;

import com.pragma.users_microservice.adapters.driving.http.dto.request.AddRoleRequest;
import com.pragma.users_microservice.adapters.driving.http.mapper.IRoleRequestMapper;
import com.pragma.users_microservice.domain.api.IRoleServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RoleRestControllerAdapterTest {

  @Mock
  private IRoleServicePort roleServicePort;
  @Mock
  private IRoleRequestMapper roleRequestMapper;

  @InjectMocks
  private RoleRestControllerAdapter roleRestControllerAdapter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testAddRole() {

    AddRoleRequest request = new AddRoleRequest("admin", "admin");
    when(roleRequestMapper.addRequestToRole(request)).thenReturn(any());

    ResponseEntity<Void> response = roleRestControllerAdapter.addRole(request);


    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    verify(roleServicePort, times(1)).saveRole(any());
  }



}