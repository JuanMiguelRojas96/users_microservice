package com.pragma.users_microservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.users_microservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.exceptions.RoleAlreadyExistsException;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.exceptions.RoleNoFoundException;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.pragma.users_microservice.domain.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoleAdapterTest {

  @Mock
  private IRoleRepository roleRepository;
  @Mock
  private IRoleEntityMapper roleEntityMapper;
  @InjectMocks
  private RoleAdapter roleAdapter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void saveRole() {

    Role role = new Role(2L,"admin","admin");
    RoleEntity roleEntity = new RoleEntity();

    when(roleRepository.findByName(role.getName())).thenReturn(Optional.empty());
    when(roleEntityMapper.toEntity(role)).thenReturn(roleEntity);

    assertDoesNotThrow(() -> roleAdapter.saveRole(role));

    verify(roleRepository, times(1)).save(roleEntity);

  }

  @Test
  void saveRoleAlreadyExists() {
    Role role = new Role(2L,"admin","admin");
    RoleEntity roleEntity = new RoleEntity(2L,"admin","admin");

    when(roleRepository.findByName(role.getName())).thenReturn(Optional.of(roleEntity));

    assertThrows(RoleAlreadyExistsException.class, () -> roleAdapter.saveRole(role));
  }

  @Test
  void testGetRoleNameById_Successful() {
    Role role = new Role(2L,"admin","admin");
    RoleEntity roleEntity = new RoleEntity(2L,"admin","admin");

    when(roleRepository.findById(role.getId())).thenReturn(Optional.of(roleEntity));

    String roleName = roleAdapter.getRoleNameById(role.getId());

    assertEquals("admin", roleName);
  }

  @Test
  void testGetRoleNameById_RoleNoFoundException() {
    Long roleId = 1L;

    when(roleRepository.findById(roleId)).thenReturn(Optional.empty());

    assertThrows(RoleNoFoundException.class, () -> roleAdapter.getRoleNameById(roleId));
  }






}