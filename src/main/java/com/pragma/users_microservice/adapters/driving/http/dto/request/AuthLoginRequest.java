package com.pragma.users_microservice.adapters.driving.http.dto.request;

import com.pragma.users_microservice.adapters.driving.http.exceptions.HttpsConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class AuthLoginRequest {

  @NotBlank(message = HttpsConstants.FIELD_EMAIL_NULL_MESSAGE)
  private final String username;
  @NotBlank(message = HttpsConstants.FIELD_USERNAME_NULL_MESSAGE)
  private final String password;
}
