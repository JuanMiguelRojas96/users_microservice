package com.pragma.users_microservice.adapters.driving.http.mapper;

import com.pragma.users_microservice.adapters.driving.http.dto.request.AddUserRequest;
import com.pragma.users_microservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUserRequestMapper {

  @Mapping(target = "id", ignore = true)
  User addRequestToUser(AddUserRequest addUserRequest);
}
