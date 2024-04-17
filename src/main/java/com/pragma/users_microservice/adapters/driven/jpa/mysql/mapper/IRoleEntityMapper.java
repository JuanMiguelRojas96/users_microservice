package com.pragma.users_microservice.adapters.driven.jpa.mysql.mapper;

import com.pragma.users_microservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.users_microservice.domain.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRoleEntityMapper {

  Role toModel(RoleEntity roleEntity);
  RoleEntity toEntity(Role role);
}
