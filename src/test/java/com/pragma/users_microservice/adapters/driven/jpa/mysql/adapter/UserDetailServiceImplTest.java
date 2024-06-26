package com.pragma.users_microservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.users_microservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.pragma.users_microservice.configuration.security.jwt.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserDetailServiceImplTest {

  @Mock
  private IUserRepository userRepository;
  @Mock
  private PasswordEncoder passwordEncoder;
  @Mock
  private JwtUtils jwtUtils;
  @InjectMocks
  private UserDetailServiceImpl userDetailServiceMySQL;

  @BeforeEach
  void setUp(){
    MockitoAnnotations.openMocks(this);
  }


  @Test
  void loadUserByUsername_UserFound_ReturnsUserDetails() {

    String username = "test@example.com";
    String password = "password";
    RoleEntity roleEntity = new RoleEntity();
    roleEntity.setName("USER");
    UserEntity userEntity = new UserEntity();
    userEntity.setEmail(username);
    userEntity.setPassword(password);
    userEntity.setRole(roleEntity);
    userEntity.setEnabled(true);
    when(userRepository.findByEmail(username)).thenReturn(Optional.of(userEntity));

    UserDetails userDetails = userDetailServiceMySQL.loadUserByUsername(username);

    assertNotNull(userDetails);
    assertEquals(username, userDetails.getUsername());
    assertEquals(password, userDetails.getPassword());
    assertTrue(userDetails.isEnabled());
    assertTrue(userDetails.isAccountNonExpired());
    assertTrue(userDetails.isCredentialsNonExpired());
    assertTrue(userDetails.isAccountNonLocked());
    assertEquals(1, userDetails.getAuthorities().size());
    assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
  }

  @Test
  void loadUserByUsername_UserNotFound_ThrowsUsernameNotFoundException() {

    String username = "nonexistent@example.com";
    when(userRepository.findByEmail(username)).thenReturn(Optional.empty());

    assertThrows(UsernameNotFoundException.class, () -> {
      userDetailServiceMySQL.loadUserByUsername(username);
    });
  }
}