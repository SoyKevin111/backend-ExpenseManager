package com.example.expenseManager.personalFinance.infraestructure.adapter.in.dto.request;

import com.example.expenseManager.personalFinance.domain.models.TypeTransaction;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MonthlySummaryRequest {
   @NotNull(message = "userId cannot be null")
   private Long userId;
   @NotNull(message = "year cannot be null")
   @Min(1)
   @Max(12)
   private int month;
   @NotNull(message = "type cannot be null")
   private TypeTransaction type; // ingreso, gasto, entrada de ahorro o salida de ahorro.
}

/*
{
    "userId": 1,
    "month": 1,
    "type": "INCOME"
}
 */