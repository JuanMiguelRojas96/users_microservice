package com.pragma.users_microservice.configuration;

import com.pragma.users_microservice.adapters.driven.jpa.mysql.adapter.RoleAdapter;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.pragma.users_microservice.domain.api.IRoleServicePort;
import com.pragma.users_microservice.domain.api.usecase.RoleUseCase;
import com.pragma.users_microservice.domain.spi.IRolePersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {


  private final IRoleRepository roleRepository;
  private final IRoleEntityMapper roleEntityMapper;

  @Bean
  public IRolePersistencePort rolePersistencePort() {
    return new RoleAdapter(roleRepository, roleEntityMapper);
  }

  @Bean
  public IRoleServicePort roleServicePort() {
    return new RoleUseCase(rolePersistencePort());
  }


}
