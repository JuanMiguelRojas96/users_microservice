package com.pragma.users_microservice.adapters.driving.http.dto.request;

import com.pragma.users_microservice.adapters.driving.http.exceptions.HttpsConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class AuthLoginRequest {

  @Schema(description = "Correo electrónico o nombre de usuario del usuario", example = "john@example.com")
  @NotBlank(message = HttpsConstants.FIELD_EMAIL_NULL_MESSAGE)
  private final String username;
  @Schema(description = "Contraseña del usuario", example = "password123")
  @NotBlank(message = HttpsConstants.FIELD_EMAIL_NULL_MESSAGE)
  private final String password;
}
