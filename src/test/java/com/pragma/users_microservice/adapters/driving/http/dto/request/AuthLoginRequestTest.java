package com.pragma.users_microservice.adapters.driving.http.dto.request;

import com.pragma.users_microservice.adapters.driving.http.exceptions.HttpsConstants;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AuthLoginRequestTest {


  private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
  private final Validator validator = factory.getValidator();

  @Test
  void testConstructorAndGetters(){

    String username = "username";
    String password = "password";

    AuthLoginRequest authLoginRequest = new AuthLoginRequest(username,password);

    assertEquals("username",authLoginRequest.getUsername());
    assertEquals("password",authLoginRequest.getPassword());

  }

  @Test
  void testValidationNotBlank() {
    AuthLoginRequest request = new AuthLoginRequest("", "");

    Set<ConstraintViolation<AuthLoginRequest>> violations = validator.validate(request);
    System.out.println(violations);

    assertEquals(2, violations.size());

    for (ConstraintViolation<AuthLoginRequest> violation : violations) {
      assertTrue(violation.getMessage().contains(HttpsConstants.FIELD_EMAIL_NULL_MESSAGE) ||
          violation.getMessage().contains(HttpsConstants.FIELD_EMAIL_NULL_MESSAGE));
    }
  }


}