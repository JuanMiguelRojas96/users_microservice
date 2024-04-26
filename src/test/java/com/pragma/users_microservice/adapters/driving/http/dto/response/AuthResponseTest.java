package com.pragma.users_microservice.adapters.driving.http.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class AuthResponseTest {

  @Test
  void testAuthResponseConstructorAndGetters() {

    String username = "testUser";
    String message = "Test message";
    String token = "testToken";
    boolean status = true;

    AuthResponse authResponse = new AuthResponse(username,message,token,status);

    assertEquals(username, authResponse.getUsername());
    assertEquals(message, authResponse.getMessage());
    assertEquals(token, authResponse.getToken());
    assertEquals(status, authResponse.isStatus());
  }

}