package com.pragma.users_microservice.adapters.driven.jpa.mysql.mapper;

import com.pragma.users_microservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.users_microservice.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserEntityMapper {

  UserEntity toEntity(User user);

  User toModel(UserEntity userEntity);
}
