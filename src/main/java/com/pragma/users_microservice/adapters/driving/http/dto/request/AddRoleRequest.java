package com.pragma.users_microservice.adapters.driving.http.dto.request;

import com.pragma.users_microservice.adapters.driving.http.exceptions.HttpsConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class AddRoleRequest {

  @NotBlank(message = HttpsConstants.FIELD_NAME_NULL_MESSAGE)
  @Size(min = 3, max = 20, message = HttpsConstants.FIELD_NAME_SIZE_MESSAGE)
  private String name;
  @NotBlank(message = HttpsConstants.FIELD_DESCRIPTION_NULL_MESSAGE)
  @Size(max = 90, message = HttpsConstants.FIELD_DESCRIPTION_SIZE_MESSAGE)
  private String description;
}
