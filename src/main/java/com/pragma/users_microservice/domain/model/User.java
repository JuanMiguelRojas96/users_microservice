package com.pragma.users_microservice.domain.model;

public class User {

  private Long id;
  private String name;
  private String lastName;
  private String identification;
  private String phoneNumber;
  private String email;
  private String password;
  private Role role;

  public User(Long id, String name, String lastName, String identification, String phoneNumber, String email, String password, Role role) {
    this.id = id;
    this.name = name;
    this.lastName = lastName;
    this.identification = identification;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.password = password;
    this.role = role;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getIdentification() {
    return identification;
  }

  public void setIdentification(String identification) {
    this.identification = identification;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
