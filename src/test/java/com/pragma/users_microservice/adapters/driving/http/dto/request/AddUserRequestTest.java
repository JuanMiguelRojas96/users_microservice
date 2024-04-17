package com.pragma.users_microservice.adapters.driving.http.dto.request;

import com.pragma.users_microservice.domain.model.Role;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AddUserRequestTest {

  private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
  private final Validator validator = factory.getValidator();

  @Test
  void testConstructorAndGetters() {

    String name = "name";
    String lastName = "lastName";
    String identification = "identification";
    String phoneNumber = "phoneNumber";
    String email = "email";
    String password = "password";
    Role role = new Role(1L,"name", "description");

    AddUserRequest addUserRequest = new AddUserRequest(name, lastName, identification, phoneNumber, email, password, role);

    assertEquals("name", addUserRequest.getName());
    assertEquals("lastName", addUserRequest.getLastName());
    assertEquals("identification", addUserRequest.getIdentification());
    assertEquals("phoneNumber", addUserRequest.getPhoneNumber());
    assertEquals("email", addUserRequest.getEmail());
    assertEquals("password", addUserRequest.getPassword());
    assertEquals("name", addUserRequest.getRole().getName());
    assertEquals("description", addUserRequest.getRole().getDescription());

  }

  @Test
  void testValidationNotBlank() {

    AddUserRequest addUserRequest = new AddUserRequest("", "", "", "", "", "", null);

    Set<ConstraintViolation<AddUserRequest>> violations = validator.validate(addUserRequest);

    assertEquals(7, violations.size());

  }

  @Test
  void testValidationSize() {

    AddUserRequest addUserRequest = new AddUserRequest("a".repeat(21), "a".repeat(21), "a".repeat(21), "a".repeat(21), "a".repeat(21), "a".repeat(21), null);

    Set<ConstraintViolation<AddUserRequest>> violations = validator.validate(addUserRequest);

    assertEquals(6, violations.size());
  }

}