package com.pragma.users_microservice.configuration;

import com.pragma.users_microservice.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class BeanConfigurationTest {

  @Mock
  private PasswordEncoder mockPasswordEncoder;

  @Mock
  private IRoleRepository mockRoleRepository;

  @Mock
  private IRoleEntityMapper mockRoleEntityMapper;

  @Mock
  private IUserRepository mockUserRepository;

  @Mock
  private IUserEntityMapper mockUserEntityMapper;

  @InjectMocks
  private BeanConfiguration beanConfiguration;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testRolePersistencePortBean() {
    assertNotNull(beanConfiguration.rolePersistencePort());
  }

  @Test
  void testRoleServicePortBean() {
    assertNotNull(beanConfiguration.roleServicePort());
  }

  @Test
  void testUserPersistencePortBean() {
    assertNotNull(beanConfiguration.userPersistencePort());
  }

  @Test
  void testUserServicePortBean() {
    assertNotNull(beanConfiguration.userServicePort());
  }

}