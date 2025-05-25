package com.example.expenseManager.user.infraestructure.adapter.in.dto.request;

import com.example.expenseManager.user.domain.RoleEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
   String username;
   String email;
   String password;
   RoleEnum role;

   public boolean isValidUsername() {
      if (username == null || username.isBlank()) {
         throw new IllegalArgumentException("The username cannot be empty");
      }
      return true;
   }

   public boolean isValidEmail() {
      if (email == null || email.isBlank()) {
         throw new IllegalArgumentException("The email cannot be empty");
      }
      if (!email.endsWith("@gmail.com")) {
         throw new IllegalArgumentException("The email must be a @gmail.com address");
      }
      return true;
   }

   public boolean isValidPassword() {
      if (password == null || password.isBlank()) {
         throw new IllegalArgumentException("The password cannot be empty");
      }
      if (password.length() < 8 || password.length() > 20) {
         throw new IllegalArgumentException("The password must be between 8 and 20 characters");
      }
      return true;
   }

   public boolean isValidRole() {
      if (role == null) {
         throw new IllegalArgumentException("The role cannot be null");
      }
      return true;
   }
}
