package com.pragma.users_microservice.adapters.driving.http.dto.request;

import com.pragma.users_microservice.adapters.driving.http.exceptions.HttpsConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class AddRoleRequest {

  @Schema(description = "Nombre del rol", example = "ADMIN")
  @NotBlank(message = HttpsConstants.FIELD_NAME_NULL_MESSAGE)
  @Size(min = 3, max = 20, message = HttpsConstants.FIELD_NAME_SIZE_MESSAGE)
  private String name;

  @Schema(description = "Descripción del rol", example = "Administrador del sistema")
  @NotBlank(message = HttpsConstants.FIELD_DESCRIPTION_NULL_MESSAGE)
  @Size(max = 90, message = HttpsConstants.FIELD_DESCRIPTION_SIZE_MESSAGE)
  private String description;
}
