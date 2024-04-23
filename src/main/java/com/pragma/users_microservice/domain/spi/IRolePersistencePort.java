package com.pragma.users_microservice.domain.spi;

import com.pragma.users_microservice.domain.model.Role;

public interface IRolePersistencePort {

  void saveRole(Role role);

  String getRoleNameById(Long id);

}
