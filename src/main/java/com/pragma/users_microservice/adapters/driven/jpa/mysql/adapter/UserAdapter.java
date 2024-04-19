package com.pragma.users_microservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.users_microservice.adapters.driven.jpa.mysql.exceptions.NoDataFoundException;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.exceptions.UserAlreadyExistsException;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.pragma.users_microservice.domain.model.User;
import com.pragma.users_microservice.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserAdapter implements IUserPersistencePort {

  private final PasswordEncoder passwordEncoder;
  private final IUserRepository userRepository;
  private final IRoleRepository roleRepository;
  private final IUserEntityMapper userEntityMapper;

  @Override
  public void saveUser(User user) {

    String normalizedEmail = user.getEmail().trim().toLowerCase();

    if (userRepository.findByEmail(normalizedEmail).isPresent()) {
      throw new UserAlreadyExistsException();
    }
    if (roleRepository.findById(user.getRole().getId()).isEmpty()) {
      throw new NoDataFoundException();
    }

    user.setEmail(normalizedEmail);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(userEntityMapper.toEntity(user));

  }
}
