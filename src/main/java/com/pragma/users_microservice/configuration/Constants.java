package com.pragma.users_microservice.configuration;

public class Constants {

  private Constants() {
    throw new IllegalStateException("Utility class");
  }

  public static final String EMPTY_FIELD_EXCEPTION_MESSAGE = "The Field indicated is Empty or Null";
  public static final String MAX_CHAR_EXCEPTION_MESSAGE = "The Field indicated exceed the maximum number of characters";
  public static final String ROLE_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The role you want to create already exists";

}
