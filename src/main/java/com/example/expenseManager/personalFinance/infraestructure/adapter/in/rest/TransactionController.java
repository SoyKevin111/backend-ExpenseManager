package com.example.expenseManager.personalFinance.infraestructure.adapter.in.rest;

import com.example.expenseManager.personalFinance.infraestructure.adapter.in.dto.request.CreateTransactionRequest;
import com.example.expenseManager.personalFinance.infraestructure.adapter.in.dto.request.TransactionPage;
import com.example.expenseManager.personalFinance.infraestructure.adapter.in.dto.response.FinancialStatusResponse;
import com.example.expenseManager.personalFinance.infraestructure.adapter.in.dto.response.TransactionLoadResponse;
import com.example.expenseManager.personalFinance.application.mapping.CreateTransactionMapping;
import com.example.expenseManager.personalFinance.domain.models.Transaction;
import com.example.expenseManager.personalFinance.domain.models.TypeTransaction;
import com.example.expenseManager.personalFinance.domain.port.in.usecases.ITransactionUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;


@RestController
@RequestMapping("/manager/request")
public class TransactionController {

   @Autowired
   ITransactionUseCase transactionUseCase;
   @Autowired
   CreateTransactionMapping createTransactionMapping;


   @PostMapping("/transactions")
   public ResponseEntity<?> saveTransaction(@RequestBody @Valid CreateTransactionRequest transactionRequest, Authentication authentication) {
      Transaction transactionResponse = this.createTransactionMapping.toDomainModel(transactionRequest, authentication.getName());
      Transaction transaction = this.transactionUseCase.save(transactionResponse);
      return ResponseEntity.ok(
         TransactionLoadResponse.builder()
            .id(transaction.getId())
            .amount(transaction.getAmount())
            .time(String.valueOf(transaction.getCreatedDateTime().toLocalTime()))
            .date(String.valueOf(transaction.getCreatedDateTime().toLocalDate()))
            .typeTransaction(transaction.getTypeTransaction())
            .userId(transaction.getUser().getId())
            .category(transaction.getCategory())
            .build()
      );
   }

   @GetMapping("transactions/balance-and-savings")
   public ResponseEntity<?> findBalanceAndSavings(Authentication authentication) {
      Map<String, BigDecimal> responseSavingsBalance = Map.of(
         "currentBalance", this.transactionUseCase.findCurrentBalance(authentication.getName()),
         "availableSavings", this.transactionUseCase.findAvailableSavings(authentication.getName())
      );
      return ResponseEntity.ok(responseSavingsBalance);
   }

   @GetMapping("transactions/finance-status-monthly")
   public ResponseEntity<?> findFinanceStatusByMonthly(
      @RequestParam(defaultValue = "#{T(java.time.LocalDate).now().getMonthValue()}") int month,
      Authentication authentication) {
      return ResponseEntity.ok(
         FinancialStatusResponse.builder()
            .income(this.transactionUseCase.findFinancialStatusByMonthly(TypeTransaction.INCOME, authentication.getName(), month))
            .expense(this.transactionUseCase.findFinancialStatusByMonthly(TypeTransaction.EXPENSE, authentication.getName(), month))
            .savingsIn(this.transactionUseCase.findFinancialStatusByMonthly(TypeTransaction.SAVINGS_IN, authentication.getName(), month))
            .savingsOut(this.transactionUseCase.findFinancialStatusByMonthly(TypeTransaction.SAVINGS_OUT, authentication.getName(), month))
            .build()
      );
   }


   //http://localhost:8080/manager/request/transactions/page
   @PostMapping("transactions/page")
   public ResponseEntity<?> findAllPage(@RequestBody @Valid TransactionPage transactionPage) {
      Sort sort = Sort.by(Sort.Order.by(transactionPage.getSortBy()).with(Sort.Direction.fromString(transactionPage.getSortDirection())));
      Page<TransactionLoadResponse> transactionPageable = this.transactionUseCase.findAllPage(PageRequest.of(transactionPage.getPage(), transactionPage.getSize(), sort))
         .map(
            transaction -> TransactionLoadResponse.builder()
               .typeTransaction(transaction.getTypeTransaction())
               .amount(transaction.getAmount())
               .time(String.valueOf(transaction.getCreatedDateTime().toLocalTime()))
               .date(String.valueOf(transaction.getCreatedDateTime().toLocalDate()))
               .userId(transaction.getUser().getId())
               .category(transaction.getCategory())
               .id(transaction.getId())
               .build()
         );
      return ResponseEntity.ok(transactionPageable);
   }
}


/*

A hacer:
 ya tenemos
    Obtener monto total por tipo y mes
    Obtener todos las transacciones en paginacion (Historial)
    Crear cualquier tipo de transaccion

 por hacer:
   obtener todas las transacciones por tipo

   BigDecimal findAmounthBytype(TypeTransaction typeTransaction); //INCOME


    Logica de entrada, salida, ahorro, salidaAhorro:
    Ingreso , es el total
    Gasto, se resta del total
    Entrada de Ahorro, se resta del total se suma al ahorro
    Salida de ahorro, se resta del ahorro

 */