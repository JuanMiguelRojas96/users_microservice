package com.pragma.users_microservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.users_microservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.users_microservice.adapters.driving.http.dto.request.AuthLoginRequest;
import com.pragma.users_microservice.adapters.driving.http.dto.response.AuthResponse;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.exceptions.AuthConstants;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.pragma.users_microservice.configuration.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private IUserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    UserEntity userEntity = userRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException(AuthConstants.USER_NOT_FOUND));

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

  public AuthResponse login(AuthLoginRequest request) {

    String username = request.getUsername();
    String password = request.getPassword();

    Authentication authentication = authenticate(username, password);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String token = jwtUtils.createToken(authentication);

    AuthResponse authResponse = new AuthResponse(username, AuthConstants.LOGIN_SUCCESS, token, true);

    return authResponse;

  }

  public Authentication authenticate(String username, String password) {

    UserDetails userDetails = loadUserByUsername(username);

    if (userDetails == null) {
      throw new BadCredentialsException(AuthConstants.INVALID_CREDENTIALS);
    }

    if (!passwordEncoder.matches(password, userDetails.getPassword())) {
      throw new BadCredentialsException(AuthConstants.INVALID_CREDENTIALS);
    }

    return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

  }
}
