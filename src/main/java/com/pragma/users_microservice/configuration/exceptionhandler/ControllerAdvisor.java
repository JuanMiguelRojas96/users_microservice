package com.pragma.users_microservice.configuration.exceptionhandler;

import com.pragma.users_microservice.adapters.driven.jpa.mysql.exceptions.RoleAlreadyExistsException;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.exceptions.RoleNoFoundException;
import com.pragma.users_microservice.adapters.driven.jpa.mysql.exceptions.UserAlreadyExistsException;
import com.pragma.users_microservice.configuration.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {

  public String getErrorMessage(FieldError error) {
    if (error == null) {
      return "Validation error";
    }

    String code = error.getCode();

    if (code != null && code.equals("NotBlank")) {
      return Constants.EMPTY_FIELD_EXCEPTION_MESSAGE;
    } else if (code != null && code.equals("NotNull")) {
      return Constants.EMPTY_FIELD_EXCEPTION_MESSAGE;
    } else if (code != null && code.equals("Size")) {
      return Constants.MAX_CHAR_EXCEPTION_MESSAGE;
    } else {
      return error.getDefaultMessage();
    }
  }


  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
    BindingResult result = ex.getBindingResult();

    if (result.hasFieldErrors()) {
      FieldError error = result.getFieldError();

      if (error != null) {
        String errorMessage = getErrorMessage(error);

        return ResponseEntity.badRequest().body(new ExceptionResponse(
            errorMessage, HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
      }
    }
    return ResponseEntity.badRequest().build();
  }

  @ExceptionHandler(RoleAlreadyExistsException.class)
  public ResponseEntity<ExceptionResponse> handleRoleAlreadyExistsException(RoleAlreadyExistsException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(
        String.format(Constants.ROLE_ALREADY_EXISTS_EXCEPTION_MESSAGE, ex.getMessage()),
        HttpStatus.CONFLICT.toString(), LocalDateTime.now()));
  }

  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<ExceptionResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(
        String.format(Constants.USER_ALREADY_EXISTS_EXCEPTION_MESSAGE, ex.getMessage()),
        HttpStatus.CONFLICT.toString(), LocalDateTime.now()));
  }

  @ExceptionHandler(RoleNoFoundException.class)
  public ResponseEntity<ExceptionResponse> handleRoleNoDataFoundException() {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
        Constants.ROLE_NOT_FOUND_EXCEPTION_MESSAGE, HttpStatus.NOT_FOUND.toString(), LocalDateTime.now()));
  }


}
