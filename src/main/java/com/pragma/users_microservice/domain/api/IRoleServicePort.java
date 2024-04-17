package com.pragma.users_microservice.domain.api;

import com.pragma.users_microservice.domain.model.Role;

public interface IRoleServicePort {

  void saveRole(Role role);

}
