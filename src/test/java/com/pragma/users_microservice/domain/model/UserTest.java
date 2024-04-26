package com.pragma.users_microservice.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

  @Mock
  private Role mockRole;

  private User user;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    user = new User(1L, "John", "Doe", "123456", "123-456-7890", "john.doe@example.com", "password", mockRole);
  }

  @Test
  void testGetters() {
    assertEquals(1L, user.getId());
    assertEquals("John", user.getName());
    assertEquals("Doe", user.getLastName());
    assertEquals("123456", user.getIdentification());
    assertEquals("123-456-7890", user.getPhoneNumber());
    assertEquals("john.doe@example.com", user.getEmail());
    assertEquals("password", user.getPassword());
    assertEquals(mockRole, user.getRole());
  }

  @Test
  void testSetters() {
    user.setName("Jane");
    user.setLastName("Smith");
    user.setIdentification("654321");
    user.setPhoneNumber("987-654-3210");
    user.setEmail("jane.smith@example.com");
    user.setPassword("newpassword");
    user.setRole(mockRole);

    assertEquals("Jane", user.getName());
    assertEquals("Smith", user.getLastName());
    assertEquals("654321", user.getIdentification());
    assertEquals("987-654-3210", user.getPhoneNumber());
    assertEquals("jane.smith@example.com", user.getEmail());
    assertEquals("newpassword", user.getPassword());
    assertEquals(mockRole, user.getRole());
  }



}