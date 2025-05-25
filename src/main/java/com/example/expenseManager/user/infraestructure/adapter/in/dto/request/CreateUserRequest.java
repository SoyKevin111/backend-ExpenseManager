package com.example.expenseManager.user.infraestructure.adapter.in.dto.request;


import com.example.expenseManager.user.domain.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateUserRequest { //Only ADMIN

   @NotBlank(message = "Username cannot be blank")
   @Size(min = 5, max = 30, message = "Username must be between 5 and 30 characters")
   private String username;

   @NotBlank(message = "Email cannot be blank")
   @Email(message = "Email should be valid")
    @Size(max = 50, message = "Email must be less than 50 characters")
   private String email;

   @NotBlank(message = "Password cannot be blank")
   @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
   private String password;

   private RoleEnum role;

}
