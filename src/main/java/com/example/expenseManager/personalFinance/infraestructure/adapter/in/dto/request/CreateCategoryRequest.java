package com.example.expenseManager.personalFinance.infraestructure.adapter.in.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCategoryRequest {
   @NotBlank(message = "No puede estar vacío ni en blanco")
   @Size(max = 30, message = "No debe exceder los 30 caracteres")
   String name;

   @NotBlank(message = "No puede estar vacío ni en blanco")
   @Size(max = 100, message = "No debe exceder los 100 caracteres")
   String description;
}
