package com.pragma.users_microservice.adapters.security.service;

import com.pragma.users_microservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.exceptions.JpaMySQLConstants;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceMySQL implements UserDetailsService {


  @Autowired
  private IUserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    UserEntity userEntity = userRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException(JpaMySQLConstants.USER_NOT_FOUND));

    List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

    RoleEntity role = userEntity.getRole();
    authorityList.add(new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()));

    return new User(userEntity.getEmail(),
        userEntity.getPassword(),
        userEntity.isEnabled(),
        userEntity.isAccountNonExpired(),
        userEntity.isCredentialsNonExpired(),
        userEntity.isAccountNonLocked(),
        authorityList);
  }
}
