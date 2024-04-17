package com.pragma.users_microservice.adapters.driving.http.mapper;

import com.pragma.users_microservice.adapters.driving.http.dto.request.AddRoleRequest;
import com.pragma.users_microservice.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IRoleRequestMapper {

  @Mapping(target = "id", ignore = true)
  Role addRequestToRole(AddRoleRequest addRoleRequest);
}
