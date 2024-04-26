package com.pragma.users_microservice.domain.api.usecase;

import com.pragma.users_microservice.domain.model.Role;
import com.pragma.users_microservice.domain.spi.IRolePersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;


class RoleUseCaseTest {

  @Mock
  private IRolePersistencePort rolePersistencePort;
  @InjectMocks
  private RoleUseCase roleUseCase;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void saveRole() {
    Role role = new Role(1L, "admin","admin");

    roleUseCase.saveRole(role);

   verify(rolePersistencePort, times(1)).saveRole(role);
  }

  @Test
  void testGetRoleNameById() {
    Long roleId = 1L;
    String expectedRoleName = "Admin";

    when(rolePersistencePort.getRoleNameById(roleId)).thenReturn(expectedRoleName);

    String actualRoleName = roleUseCase.getRoleNameById(roleId);

    assertEquals(expectedRoleName, actualRoleName);
  }


}