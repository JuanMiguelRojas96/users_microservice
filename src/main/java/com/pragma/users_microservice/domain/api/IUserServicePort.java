package com.pragma.users_microservice.domain.api;

import com.pragma.users_microservice.domain.model.User;

public interface IUserServicePort {

  void saveUser(User user);
}
