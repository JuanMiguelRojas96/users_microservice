package com.pragma.users_microservice.adapters.driving.http.exceptions;

public class HttpsConstants {

  private HttpsConstants() {
    throw new IllegalStateException("Utility class");
  }

  public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null";
  public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' cannot be null";
  public static final String FIELD_NAME_SIZE_MESSAGE = "Field 'name' cannot exceed the maximum number of characters (20) or less than 3";
  public static final String FIELD_DESCRIPTION_SIZE_MESSAGE = "Field 'description' cannot exceed the maximum number of characters (90) or less than 3";
}
