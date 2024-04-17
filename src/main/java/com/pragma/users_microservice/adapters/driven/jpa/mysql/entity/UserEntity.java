package com.pragma.users_microservice.adapters.driven.jpa.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(length = 20)
  private String name;
  @Column(length = 20)
  private String lastName;
  @Column(length = 20)
  private String identification;
  @Column(length = 20)
  private String phoneNumber;
  @Column
  private String email;
  @Column(length = 20)
  private String password;

  @ManyToOne
  @JoinColumn(name = "role_id")
  private RoleEntity role;
}
