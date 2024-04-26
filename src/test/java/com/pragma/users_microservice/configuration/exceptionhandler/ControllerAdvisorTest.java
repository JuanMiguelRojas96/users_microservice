package com.pragma.users_microservice.configuration.exceptionhandler;

import com.pragma.users_microservice.adapters.driven.jpa.mysql.exceptions.RoleAlreadyExistsException;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.exceptions.UserAlreadyExistsException;
import com.pragma.users_microservice.configuration.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ControllerAdvisorTest {

  private ControllerAdvisor controllerAdvisor;

  @Mock
  private FieldError mockFieldError;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    controllerAdvisor = new ControllerAdvisor();

  }

  @Test
  void testGetErrorMessageNull() {
    String errorMessage = controllerAdvisor.getErrorMessage(null);
    assertEquals("Validation error", errorMessage);
  }

  @Test
  void testGetErrorMessageNotBlank() {
    when(mockFieldError.getCode()).thenReturn("NotBlank");
    String errorMessage = controllerAdvisor.getErrorMessage(mockFieldError);
    assertEquals(Constants.EMPTY_FIELD_EXCEPTION_MESSAGE, errorMessage);
  }

  @Test
  void testGetErrorMessageNotNull() {
    when(mockFieldError.getCode()).thenReturn("NotNull");
    String errorMessage = controllerAdvisor.getErrorMessage(mockFieldError);
    assertEquals(Constants.EMPTY_FIELD_EXCEPTION_MESSAGE, errorMessage);
  }

  @Test
  void testGetErrorMessageSize() {
    when(mockFieldError.getCode()).thenReturn("Size");
    String errorMessage = controllerAdvisor.getErrorMessage(mockFieldError);
    assertEquals(Constants.MAX_CHAR_EXCEPTION_MESSAGE, errorMessage);
  }

  @Test
  void testGetErrorMessageDefault() {
    when(mockFieldError.getCode()).thenReturn("SomeOtherCode");
    when(mockFieldError.getDefaultMessage()).thenReturn("Custom error message");
    String errorMessage = controllerAdvisor.getErrorMessage(mockFieldError);
    assertEquals("Custom error message", errorMessage);
  }

  @Test
  void testHandleValidationExceptions() {
    MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
    BindingResult result = mock(BindingResult.class);
    when(ex.getBindingResult()).thenReturn(result);
    when(result.hasFieldErrors()).thenReturn(true);
    when(result.getFieldError()).thenReturn(mockFieldError);
    when(mockFieldError.getCode()).thenReturn("NotBlank");
    when(mockFieldError.getDefaultMessage()).thenReturn(Constants.EMPTY_FIELD_EXCEPTION_MESSAGE);

    ResponseEntity<ExceptionResponse> responseEntity = controllerAdvisor.handleValidationExceptions(ex);

    assertNotNull(responseEntity);
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertEquals(Constants.EMPTY_FIELD_EXCEPTION_MESSAGE, responseEntity.getBody().getMessage());
  }



  @Test
  void testHandleValidationExceptionsWithoutFieldError() {
    MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
    BindingResult result = mock(BindingResult.class);
    when(ex.getBindingResult()).thenReturn(result);
    when(result.hasFieldErrors()).thenReturn(false);

    ResponseEntity<ExceptionResponse> responseEntity = controllerAdvisor.handleValidationExceptions(ex);

    assertNotNull(responseEntity);
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertNull(responseEntity.getBody());
  }


  @Test
  void testHandleRoleAlreadyExistsException() {
    RoleAlreadyExistsException ex = new RoleAlreadyExistsException();

    ResponseEntity<ExceptionResponse> responseEntity = controllerAdvisor.handleRoleAlreadyExistsException(ex);

    assertNotNull(responseEntity);
    assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertEquals(responseEntity.getBody().getStatus(), responseEntity.getStatusCode().toString() );
    assertEquals(responseEntity.getBody().getTimestamp().toLocalDate(), LocalDateTime.now().toLocalDate());
    assertEquals(Constants.ROLE_ALREADY_EXISTS_EXCEPTION_MESSAGE, responseEntity.getBody().getMessage());
  }

  @Test
  void testHandleUserAlreadyExistsException() {
    UserAlreadyExistsException ex = new UserAlreadyExistsException();

    ResponseEntity<ExceptionResponse> responseEntity = controllerAdvisor.handleUserAlreadyExistsException(ex);

    assertNotNull(responseEntity);
    assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertEquals(Constants.USER_ALREADY_EXISTS_EXCEPTION_MESSAGE, responseEntity.getBody().getMessage());
  }

  @Test
  void testHandleRoleNoDataFoundException() {
    ResponseEntity<ExceptionResponse> responseEntity = controllerAdvisor.handleRoleNoDataFoundException();

    assertNotNull(responseEntity);
    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertEquals(Constants.ROLE_NOT_FOUND_EXCEPTION_MESSAGE, responseEntity.getBody().getMessage());
  }

}