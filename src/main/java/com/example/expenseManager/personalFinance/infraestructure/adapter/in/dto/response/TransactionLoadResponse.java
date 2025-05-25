package com.example.expenseManager.personalFinance.infraestructure.adapter.in.dto.response;

import com.example.expenseManager.personalFinance.domain.models.Category;
import com.example.expenseManager.personalFinance.domain.models.TypeTransaction;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionLoadResponse {
   Long id;
   BigDecimal amount;
   String time;
   String date;
   TypeTransaction typeTransaction;
   Long userId;
   Category category;
}
