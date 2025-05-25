package com.example.expenseManager.user.infraestructure.adapter.in.dto.response;

import com.example.expenseManager.user.domain.RoleEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoadResponse {
   Long id;
   String username;
   String email;
   RoleEnum role;
}
