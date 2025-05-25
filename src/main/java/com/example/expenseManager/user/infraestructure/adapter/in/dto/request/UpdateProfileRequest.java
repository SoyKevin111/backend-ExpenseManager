package com.example.expenseManager.user.infraestructure.adapter.in.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UpdateProfileRequest {

   @NotBlank(message = "username cannot be blank")
   @Size(min = 5, max = 30, message = "username must be between 3and 30 characters")
   String username;
}
