package com.pragma.users_microservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.users_microservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.exceptions.NoDataFoundException;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.exceptions.UserAlreadyExistsException;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.pragma.users_microservice.domain.model.Role;
import com.pragma.users_microservice.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserAdapterTest {

  @Mock
  private IUserRepository userRepository;
  @Mock
  private IRoleRepository roleRepository;
  @Mock
  private IUserEntityMapper userEntityMapper;
  @InjectMocks
  private UserAdapter userAdapter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void saveUser() {

    User user = new User(1L,"admin", "admin",
        "admin", "admin","admin","admin",
        new Role(1L, "admin", "admin"));

    UserEntity userEntity = new UserEntity();

    when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
    when(roleRepository.findById(user.getRole().getId())).thenReturn(Optional.of(new RoleEntity()));
    when(userEntityMapper.toEntity(user)).thenReturn(userEntity);

    assertDoesNotThrow(() -> userAdapter.saveUser(user));

    verify(userRepository, times(1)).save(userEntity);
  }

  @Test
  void saveUserAlreadyExists() {

    User user = new User(1L,"admin", "admin",
        "admin", "admin","admin","admin",
        new Role(1L, "admin", "admin"));

    UserEntity userEntity = new UserEntity(1L,"admin", "admin",
        "admin", "admin","admin","admin",
        new RoleEntity(1L, "admin", "admin"));

    when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(userEntity));

    assertThrows(UserAlreadyExistsException.class, () -> userAdapter.saveUser(user));
  }

  @Test
  void saveUserRoleNoDataFound() {

    User user = new User(1L,"admin", "admin",
        "admin", "admin","admin","admin",
        new Role(1L, "admin", "admin"));

    when(roleRepository.findById(user.getRole().getId())).thenReturn(Optional.empty());

    assertThrows(NoDataFoundException.class, () -> userAdapter.saveUser(user));
  }



}