package com.pragma.users_microservice.configuration;

import com.pragma.users_microservice.adapters.driven.jpa.mysql.adapter.RoleAdapter;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.adapter.UserAdapter;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.pragma.users_microservice.domain.api.IRoleServicePort;
import com.pragma.users_microservice.domain.api.IUserServicePort;
import com.pragma.users_microservice.domain.api.usecase.RoleUseCase;
import com.pragma.users_microservice.domain.api.usecase.UserUseCase;
import com.pragma.users_microservice.domain.spi.IRolePersistencePort;
import com.pragma.users_microservice.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

  private final PasswordEncoder passwordEncoder;
  private final IRoleRepository roleRepository;
  private final IRoleEntityMapper roleEntityMapper;
  private final IUserRepository userRepository;
  private final IUserEntityMapper userEntityMapper;

  @Bean
  public IRolePersistencePort rolePersistencePort() {
    return new RoleAdapter(roleRepository, roleEntityMapper);
  }

  @Bean
  public IRoleServicePort roleServicePort() {
    return new RoleUseCase(rolePersistencePort());
  }

  @Bean
  public IUserPersistencePort userPersistencePort() {
    return new UserAdapter(passwordEncoder,userRepository,roleRepository, userEntityMapper);
  }
  @Bean
  public IUserServicePort userServicePort() {
    return new UserUseCase(userPersistencePort());
  }

}
