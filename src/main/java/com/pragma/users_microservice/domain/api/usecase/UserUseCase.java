package com.pragma.users_microservice.domain.api.usecase;

import com.pragma.users_microservice.domain.api.IUserServicePort;
import com.pragma.users_microservice.domain.model.User;
import com.pragma.users_microservice.domain.spi.IUserPersistencePort;

public class UserUseCase implements IUserServicePort {

  private IUserPersistencePort userPersistencePort;

  public UserUseCase(IUserPersistencePort userPersistencePort) {
    this.userPersistencePort = userPersistencePort;
  }
  @Override
  public void saveUser(User user) {
    userPersistencePort.saveUser(user);
  }
}
