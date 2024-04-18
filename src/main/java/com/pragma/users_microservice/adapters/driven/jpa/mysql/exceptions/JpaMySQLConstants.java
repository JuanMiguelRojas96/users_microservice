package com.pragma.users_microservice.adapters.driven.jpa.mysql.exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class JpaMySQLConstants {

  private JpaMySQLConstants() {
    throw new UsernameNotFoundException("Utility class");
  }

  public static final String USER_NOT_FOUND = "User not found";
}
