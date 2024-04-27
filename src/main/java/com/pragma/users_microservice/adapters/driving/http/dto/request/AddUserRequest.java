package com.pragma.users_microservice.adapters.driving.http.dto.request;


import com.pragma.users_microservice.adapters.driving.http.exceptions.HttpsConstants;
import com.pragma.users_microservice.domain.model.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class AddUserRequest {

  @Schema(description = "Nombre del usuario", example = "John")
  @NotBlank(message = HttpsConstants.FIELD_NAME_NULL_MESSAGE)
  @Size(max = 20, message = HttpsConstants.FIELD_NAME_SIZE_MESSAGE)
  private String name;
  @Schema(description = "Apellido del usuario", example = "Doe")
  @NotBlank(message = HttpsConstants.FIELD_LAST_NAME_NULL_MESSAGE)
  @Size(max = 20, message = HttpsConstants.FIELD_LAST_NAME_SIZE_MESSAGE)
  private String lastName;
  @Schema(description = "Identificación del usuario", example = "123456789")
  @NotBlank(message = HttpsConstants.FIELD_IDENTIFICATION_NULL_MESSAGE)
  @Size(max = 20, message = HttpsConstants.FIELD_IDENTIFICATION_SIZE_MESSAGE)
  private String identification;
  @Schema(description = "Número de teléfono del usuario", example = "1234567890")
  @NotBlank(message = HttpsConstants.FIELD_PHONE_NUMBER_NULL_MESSAGE)
  @Size(max = 20, message = HttpsConstants.FIELD_PHONE_NUMBER_SIZE_MESSAGE)
  private String phoneNumber;
  @Schema(description = "Correo electrónico del usuario", example = "john@example.com")
  @NotBlank(message = HttpsConstants.FIELD_EMAIL_NULL_MESSAGE)
  @Email(message = HttpsConstants.NOT_IS_A_EMAIL)
  private String email;
  @Schema(description = "Contraseña del usuario", example = "password123")
  @NotBlank(message = HttpsConstants.FIELD_PASSWORD_NULL_MESSAGE)
  @Size(max = 20, message = HttpsConstants.FIELD_PASSWORD_SIZE_MESSAGE)
  private String password;

  @Schema(description = "Id del Rol del usuario", example = "1")
  @NotNull(message = HttpsConstants.FIELD_ROLE_NULL_MESSAGE)
  private Role role;

}
