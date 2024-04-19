package com.pragma.users_microservice.domain.spi;

import com.pragma.users_microservice.domain.model.User;

public interface IUserPersistencePort {

  void saveUser(User user);
}
