package com.pragma.users_microservice.adapters.driving.http.controller;

import com.pragma.users_microservice.adapters.driven.jpa.mysql.adapter.UserDetailServiceImpl;
import com.pragma.users_microservice.adapters.driving.http.dto.request.AddUserRequest;
import com.pragma.users_microservice.adapters.driving.http.dto.request.AuthLoginRequest;
import com.pragma.users_microservice.adapters.driving.http.dto.response.AuthResponse;
import com.pragma.users_microservice.adapters.driving.http.exceptions.RoleConstants;
import com.pragma.users_microservice.adapters.driving.http.mapper.IUserRequestMapper;
import com.pragma.users_microservice.domain.api.IRoleServicePort;
import com.pragma.users_microservice.domain.api.IUserServicePort;
import com.pragma.users_microservice.domain.model.Role;
import com.pragma.users_microservice.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthRestControllerAdapterTest {

  @Mock
  private UserDetailServiceImpl userDetailServiceImpl;

  @Mock
  private IRoleServicePort roleServicePort;

  @Mock
  private IUserServicePort userServicePort;

  @Mock
  private IUserRequestMapper userRequestMapper;

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

    when(roleServicePort.getRoleNameById(1L)).thenReturn(RoleConstants.TUTOR);
    when(userRequestMapper.addRequestToUser(request)).thenReturn(new User(1L,"admin", "admin",
        "admin", "admin","admin","admin",
        new Role(1L, "admin", "admin")));

    Authentication authentication = new Authentication() {
      @Override
      public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
      }

      @Override
      public Object getCredentials() {
        return null;
      }

      @Override
      public Object getDetails() {
        return null;
      }

      @Override
      public Object getPrincipal() {
        return null;
      }

      @Override
      public boolean isAuthenticated() {
        return false;
      }

      @Override
      public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

      }

      @Override
      public String getName() {
        return null;
      }
    };

    ResponseEntity<Void> response = authRestControllerAdapter.addUser(request,authentication);

    ResponseEntity<Void> expectedResponse = ResponseEntity.status(HttpStatus.CREATED).build();

    assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
    verify(userServicePort, times(1)).saveUser(any());
  }

  @Test
  void testLogin() {

    AuthLoginRequest mockRequest = new AuthLoginRequest("username","password");

    AuthResponse mockResponse = new AuthResponse("username","message","token",true);
    when(userDetailServiceImpl.login(mockRequest)).thenReturn(mockResponse);

    ResponseEntity<AuthResponse> response = authRestControllerAdapter.login(mockRequest);

    verify(userDetailServiceImpl).login(mockRequest);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("token", Objects.requireNonNull(response.getBody()).getToken());
  }




}