package com.example.expenseManager.user.infraestructure.adapter.in.rest;

import com.example.expenseManager.core.application.mappers.RequestGeneralMapper;
import com.example.expenseManager.user.application.UpdateProfileMapping;
import com.example.expenseManager.user.application.UpdateUserMapping;
import com.example.expenseManager.user.infraestructure.adapter.in.dto.request.UpdateProfileRequest;
import com.example.expenseManager.user.infraestructure.adapter.in.dto.response.UserLoadResponse;
import com.example.expenseManager.user.domain.User;
import com.example.expenseManager.user.domain.port.in.IUserUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager/request/users")
public class UserController {

   @Autowired
   IUserUseCase userUseCase;
   @Autowired
   private RequestGeneralMapper requestMapper;
   @Autowired
   private UpdateUserMapping updateUserMapping;
   @Autowired
   private UpdateProfileMapping updateProfileMapping;

   @GetMapping("/profile/load") //ADMIN, USER
   public ResponseEntity<?> loadProfile(Authentication auth) { // encontrar por email
      User user = this.userUseCase.findByEmail(auth.getName());
      return ResponseEntity.ok().body(
         UserLoadResponse.builder()
            .id(user.getId())
            .username(user.getUsername())
            .email(user.getEmail())
            .role(user.getRole())
            .build()
      );
   }

   @PatchMapping("/profile/{id}") //rol: user, admin
   public ResponseEntity<?> update(@RequestBody @Valid UpdateProfileRequest updateProfileRequest, @PathVariable Long id) { //actualizar perfil
      User userResponse = this.updateProfileMapping.toDomainModel(updateProfileRequest, id);
      User user = this.userUseCase.updateProfile(userResponse);
      return ResponseEntity.ok().body(
         UserLoadResponse.builder()
            .id(user.getId())
            .username(user.getUsername())
            .email(user.getEmail())
            .role(user.getRole())
            .build()
      );
   }
}
