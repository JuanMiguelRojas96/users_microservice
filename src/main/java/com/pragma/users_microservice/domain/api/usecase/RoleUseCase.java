package com.pragma.users_microservice.domain.api.usecase;

import com.pragma.users_microservice.domain.api.IRoleServicePort;
import com.pragma.users_microservice.domain.model.Role;
import com.pragma.users_microservice.domain.spi.IRolePersistencePort;

public class RoleUseCase implements IRoleServicePort {

  private IRolePersistencePort rolePersistencePort;

  public RoleUseCase(IRolePersistencePort rolePersistencePort) {
    this.rolePersistencePort = rolePersistencePort;
  }

  @Override
  public void saveRole(Role role) {
    rolePersistencePort.saveRole(role);
  }

  public String getRoleNameById(Long id){
    return rolePersistencePort.getRoleNameById(id);
  }

}
