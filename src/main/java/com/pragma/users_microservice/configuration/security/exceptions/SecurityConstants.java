package com.pragma.users_microservice.configuration.security.exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SecurityConstants {

  private SecurityConstants() {
    throw new UsernameNotFoundException("Utility class");
  }


  public static final String TOKEN_INVALID = "Token invalid, not authorized";
}
