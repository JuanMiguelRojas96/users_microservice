package com.pragma.users_microservice.adapters.driving.http.controller;

import com.pragma.users_microservice.adapters.driving.http.dto.request.AddUserRequest;
import com.pragma.users_microservice.adapters.driving.http.mapper.IUserRequestMapper;
import com.pragma.users_microservice.domain.api.IUserServicePort;
import com.pragma.users_microservice.domain.model.Role;
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

class UserRestControllerAdapterTest {

  @Mock
  private IUserServicePort userServicePort;
  @Mock
  private IUserRequestMapper userRequestMapper;

  @InjectMocks
  private UserRestControllerAdapter userRestControllerAdapter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testAddUser() {

    AddUserRequest request = new AddUserRequest("admin", "admin",
        "admin", "admin","admin","admin",
        new Role(1L, "admin", "admin"));

    when(userRequestMapper.addRequestToUser(request)).thenReturn(any());

    ResponseEntity<Void> response = userRestControllerAdapter.addUser(request);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    verify(userServicePort, times(1)).saveUser(any());
  }

}