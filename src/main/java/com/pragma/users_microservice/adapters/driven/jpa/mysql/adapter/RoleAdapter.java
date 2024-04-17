package com.pragma.users_microservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.users_microservice.adapters.driven.jpa.mysql.exceptions.RoleAlreadyExistsException;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.pragma.users_microservice.domain.model.Role;
import com.pragma.users_microservice.domain.spi.IRolePersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleAdapter implements IRolePersistencePort {

  private final IRoleRepository roleRepository;
  private final IRoleEntityMapper roleEntityMapper;
  @Override
  public void saveRole(Role role) {

    String normalizedName = role.getName().trim().toLowerCase();

    if (roleRepository.findByName(normalizedName).isPresent()) {
      throw new RoleAlreadyExistsException();
    }

    role.setName(normalizedName);
    roleRepository.save(roleEntityMapper.toEntity(role));
  }
}
