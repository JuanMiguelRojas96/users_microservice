package com.pragma.users_microservice.adapters.driving.http.exceptions;

public class RoleConstants {
  private RoleConstants(){
    throw new IllegalStateException("Utility class");
  }

  public static final String TUTOR = "tutor";
  public static final String ADMIN = "admin";
  public static final String ESTUDIANTE = "estudiante";

  public static final String ROLE_TUTOR = "ROLE_TUTOR";
  public static final String ROLE_ADMIN = "ROLE_ADMIN";
  public static final String ROLE_ESTUDIANTE = "ROLE_ESTUDIANTE";
}
