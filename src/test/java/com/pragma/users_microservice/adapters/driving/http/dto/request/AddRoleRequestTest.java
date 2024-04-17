package com.pragma.users_microservice.adapters.driving.http.dto.request;

import com.pragma.users_microservice.adapters.driving.http.exceptions.HttpsConstants;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AddRoleRequestTest {

  private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
  private final Validator validator = factory.getValidator();

  @Test
  void testConstructorAndGetters() {
    String name = "name";
    String description = "description";

    AddRoleRequest addRoleRequest = new AddRoleRequest(name, description);

    assertEquals("name", addRoleRequest.getName());
    assertEquals("description", addRoleRequest.getDescription());

  }

  @Test
  void testValidationNotBlank() {
    AddRoleRequest request = new AddRoleRequest("", "");

    Set<ConstraintViolation<AddRoleRequest>> violations = validator.validate(request);
    System.out.println(violations);

    assertEquals(3, violations.size());

    for (ConstraintViolation<AddRoleRequest> violation : violations) {
      assertTrue(violation.getMessage().contains(HttpsConstants.FIELD_NAME_NULL_MESSAGE) ||
          violation.getMessage().contains(HttpsConstants.FIELD_DESCRIPTION_NULL_MESSAGE) || violation.getMessage().contains(HttpsConstants.FIELD_NAME_SIZE_MESSAGE));
    }
  }


  @Test
  void testValidationSize() {

    AddRoleRequest request = new AddRoleRequest("a".repeat(21), "a".repeat(91));

    Set<ConstraintViolation<AddRoleRequest>> violations = validator.validate(request);

    assertEquals(2, violations.size());

    for (ConstraintViolation<AddRoleRequest> violation : violations) {
      assertTrue(violation.getMessage().contains(HttpsConstants.FIELD_NAME_SIZE_MESSAGE) ||
          violation.getMessage().contains(HttpsConstants.FIELD_DESCRIPTION_SIZE_MESSAGE));
    }

  }

}