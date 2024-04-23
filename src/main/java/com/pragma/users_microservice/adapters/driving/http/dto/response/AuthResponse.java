package com.pragma.users_microservice.adapters.driving.http.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthResponse {
  private final String username;
  private final String message;
  private final String token;
  private final boolean status;

}
