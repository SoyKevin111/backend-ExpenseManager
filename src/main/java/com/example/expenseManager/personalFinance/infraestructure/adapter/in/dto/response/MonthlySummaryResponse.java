package com.example.expenseManager.personalFinance.infraestructure.adapter.in.dto.response;

import com.example.expenseManager.personalFinance.domain.models.TypeTransaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MonthlySummaryResponse {
   TypeTransaction type;
   BigDecimal totalMonthlyAmount;
}
