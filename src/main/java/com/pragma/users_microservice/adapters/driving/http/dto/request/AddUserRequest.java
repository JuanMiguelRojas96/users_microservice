package com.pragma.users_microservice.adapters.driving.http.dto.request;


import com.pragma.users_microservice.adapters.driving.http.exceptions.HttpsConstants;
import com.pragma.users_microservice.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class AddUserRequest {

  @NotBlank(message = HttpsConstants.FIELD_NAME_NULL_MESSAGE)
  @Size(max = 20, message = HttpsConstants.FIELD_NAME_SIZE_MESSAGE)
  private String name;
  @NotBlank(message = HttpsConstants.FIELD_LAST_NAME_NULL_MESSAGE)
  @Size(max = 20, message = HttpsConstants.FIELD_LAST_NAME_SIZE_MESSAGE)
  private String lastName;
  @NotBlank(message = HttpsConstants.FIELD_IDENTIFICATION_NULL_MESSAGE)
  @Size(max = 20, message = HttpsConstants.FIELD_IDENTIFICATION_SIZE_MESSAGE)
  private String identification;
  @NotBlank(message = HttpsConstants.FIELD_PHONE_NUMBER_NULL_MESSAGE)
  @Size(max = 20, message = HttpsConstants.FIELD_PHONE_NUMBER_SIZE_MESSAGE)
  private String phoneNumber;
  @NotBlank(message = HttpsConstants.FIELD_EMAIL_NULL_MESSAGE)
  private String email;
  @NotBlank(message = HttpsConstants.FIELD_PASSWORD_NULL_MESSAGE)
  @Size(max = 20, message = HttpsConstants.FIELD_PASSWORD_SIZE_MESSAGE)
  private String password;
  @NotNull(message = HttpsConstants.FIELD_ROLE_NULL_MESSAGE)
  private Role role;

}
