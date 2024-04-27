package com.pragma.users_microservice.adapters.driving.http.exceptions;

public class HttpsConstants {

  private HttpsConstants() {
    throw new IllegalStateException("Utility class");
  }

//Fields Null
  public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null";
  public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' cannot be null";
  public static final String FIELD_LAST_NAME_NULL_MESSAGE = "Field 'lastName' cannot be null";
  public static final String FIELD_IDENTIFICATION_NULL_MESSAGE = "Field 'identification' cannot be null";
  public static final String FIELD_PHONE_NUMBER_NULL_MESSAGE = "Field 'phoneNumber' cannot be null";
  public static final String FIELD_EMAIL_NULL_MESSAGE = "Field 'email' cannot be null";
  public static final String FIELD_PASSWORD_NULL_MESSAGE = "Field 'password' cannot be null";
  public static final String FIELD_ROLE_NULL_MESSAGE = "Field 'role' cannot be null";
  public static final String FIELD_USERNAME_NULL_MESSAGE = "Field 'username' cannot be null";

  //Fields Size
  public static final String FIELD_NAME_SIZE_MESSAGE = "Field 'name' cannot exceed the maximum number of characters (20) or less than 3";
  public static final String FIELD_DESCRIPTION_SIZE_MESSAGE = "Field 'description' cannot exceed the maximum number of characters (90) or less than 3";
  public static final String FIELD_LAST_NAME_SIZE_MESSAGE = "Field 'lastName' cannot exceed the maximum number of characters (20)";
  public static final String FIELD_IDENTIFICATION_SIZE_MESSAGE = "Field 'identification' cannot exceed the maximum number of characters (20)";
  public static final String FIELD_PHONE_NUMBER_SIZE_MESSAGE = "Field 'phoneNumber' cannot exceed the maximum number of characters (20)";
  public static final String FIELD_PASSWORD_SIZE_MESSAGE = "Field 'password' cannot exceed the maximum number of characters (20)";

  public static final String NOT_IS_A_EMAIL = "Not is a valid Email";

}
