package com.example.expenseManager.personalFinance.infraestructure.adapter.in.dto.request;

import com.example.expenseManager.personalFinance.domain.models.TypeTransaction;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTransactionRequest {

   @NotNull(message = "Amount cannot be null")
   private BigDecimal amount;

   @NotNull(message = "Transaction type cannot be null")
   private TypeTransaction typeTransaction;

   @NotNull(message = "Category ID cannot be null")
   private Long categoryId;
}

/*
{
    "amount": 100.00,
    "typeTransaction": "INCOME",
    "userId": 1,
    "categoryId": 1
}
 */
