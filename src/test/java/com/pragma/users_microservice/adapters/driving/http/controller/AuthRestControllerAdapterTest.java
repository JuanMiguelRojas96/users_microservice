package com.pragma.users_microservice.adapters.driving.http.controller;

import com.pragma.users_microservice.adapters.driven.jpa.mysql.adapter.UserDetailServiceImpl;
import com.pragma.users_microservice.adapters.driving.http.dto.request.AddUserRequest;
import com.pragma.users_microservice.adapters.driving.http.mapper.IUserRequestMapper;
import com.pragma.users_microservice.domain.api.IRoleServicePort;
import com.pragma.users_microservice.domain.api.IUserServicePort;
import com.pragma.users_microservice.domain.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mock.*;
import org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;

class AuthRestControllerAdapterTest {

  @Mock
  private UserDetailServiceImpl userDetailServiceImpl;

  @Mock
  private IRoleServicePort roleServicePort;

  @Mock
  private IUserServicePort userServicePort;

  @Mock
  private IUserRequestMapper userRequestMapper;

  @Mock
  private Authentication authentication;

  private AuthRestControllerAdapter authRestControllerAdapter;



  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    authRestControllerAdapter = new AuthRestControllerAdapter(userDetailServiceImpl, roleServicePort, userServicePort, userRequestMapper);
  }

  @Test
  void  testAddUser_AdminRole_Success(){

    AddUserRequest request = new AddUserRequest("admin", "admin",
        "admin", "admin","admin","admin",
        new Role(1L, "admin", "admin"));

    when(authentication.getAuthorities().iterator().next().getAuthority()).thenReturn("ROLE_ADMIN");
    when(roleServicePort.getRoleNameById(1L)).thenReturn("admin");

    ResponseEntity<Void> response = authRestControllerAdapter.addUser(request, authentication);



  }




}