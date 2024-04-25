package com.pragma.users_microservice.domain.api.usecase;

import com.pragma.users_microservice.domain.model.Role;
import com.pragma.users_microservice.domain.model.User;
import com.pragma.users_microservice.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class UserUseCaseTest {

  @Mock
  private IUserPersistencePort userPersistencePort;
  @InjectMocks
  private UserUseCase userUseCase;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void saveUser() {
    User user = new User(1L, "admin", "admin",
        "admin", "admin", "admin",
        "admin",new Role( 1L, "admin", "admin"));

    userUseCase.saveUser(user);

    verify(userPersistencePort, times(1)).saveUser(user);
  }
}