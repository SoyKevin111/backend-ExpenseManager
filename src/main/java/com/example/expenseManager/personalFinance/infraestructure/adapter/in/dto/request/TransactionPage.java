package com.example.expenseManager.personalFinance.infraestructure.adapter.in.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionPage {

   @NotNull(message = "Page number cannot be null")
   int page;

   //opcional
   int size;
   String sortBy;
   String sortDirection;

   public int getSize() {
      if (this.size < 1 || this.size > 5) {
         return 5;
      }
      return this.size;
   }

   public String getSortBy() {
      return this.sortBy == null || this.sortBy.isBlank() ? "id" : this.sortBy;
   }

   public String getSortDirection() {
      return this.sortDirection == null || this.sortDirection.isBlank() ? "asc" : this.sortDirection;
   }

}


/*
    {
        "page": 0,
        "size": 5,
        "sortBy": "id"
    }
 */
