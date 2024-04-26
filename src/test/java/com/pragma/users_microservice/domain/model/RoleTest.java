package com.pragma.users_microservice.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

  private Role role;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    role = new Role(1L, "Admin", "Administrator role");
  }

  @Test
  void testGetters() {
    assertEquals(1L, role.getId());
    assertEquals("Admin", role.getName());
    assertEquals("Administrator role", role.getDescription());
  }

  @Test
  void testSetters() {
    role.setName("User");
    role.setDescription("Regular user role");

    assertEquals("User", role.getName());
    assertEquals("Regular user role", role.getDescription());
  }

}