package com.pragma.users_microservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.users_microservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.exceptions.RoleNoFoundException;
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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserAdapterTest {

  @Mock
  private PasswordEncoder passwordEncoder;

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
    when(passwordEncoder.encode(user.getPassword())).thenReturn("admin");

    assertDoesNotThrow(() -> userAdapter.saveUser(user));

    verify(userRepository, times(1)).save(userEntity);
    verify(userRepository, times(1)).findByEmail(user.getEmail());
    verify(roleRepository, times(1)).findById(user.getRole().getId());
    verify(passwordEncoder, times(1)).encode(user.getPassword());
    verify(userEntityMapper, times(1)).toEntity(user);
  }

  @Test
  void saveUserAlreadyExists() {

    User user = new User(1L,"admin", "admin",
        "admin", "admin","admin","admin",
        new Role(1L, "admin", "admin"));

    UserEntity userEntity = new UserEntity(1L,"admin", "admin",
        "admin", "admin","admin","admin",true,true,true,true,
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

    assertThrows(RoleNoFoundException.class, () -> userAdapter.saveUser(user));
  }



}