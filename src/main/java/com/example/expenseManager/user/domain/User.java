package com.example.expenseManager.user.domain;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
  Long id;
  String username;
  String email;
  String password;
  RoleEnum role;

  public void xddd() {
    var x = this.getId();
    System.out.println("xddd: " + x);

  }

}
