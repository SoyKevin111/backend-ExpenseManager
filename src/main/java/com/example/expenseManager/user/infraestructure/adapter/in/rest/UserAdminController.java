package com.example.expenseManager.user.infraestructure.adapter.in.rest;


import com.example.expenseManager.core.application.mappers.RequestGeneralMapper;
import com.example.expenseManager.user.application.UpdateProfileMapping;
import com.example.expenseManager.user.application.UpdateUserMapping;
import com.example.expenseManager.user.domain.User;
import com.example.expenseManager.user.domain.port.in.IUserUseCase;
import com.example.expenseManager.user.infraestructure.adapter.in.dto.request.CreateUserRequest;
import com.example.expenseManager.user.infraestructure.adapter.in.dto.request.UpdateUserRequest;
import com.example.expenseManager.user.infraestructure.adapter.in.dto.response.UserLoadResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manager/request/admin")
public class UserAdminController {

   @Autowired
   IUserUseCase userUseCase;
   @Autowired
   private RequestGeneralMapper requestMapper;
   @Autowired
   private UpdateUserMapping updateUserMapping;
   @Autowired
   private UpdateProfileMapping updateProfileMapping;


   @PostMapping("/users") //rol: admin
   public ResponseEntity<?> create(@RequestBody @Valid CreateUserRequest createUserRequest) {
      User userResponse = this.requestMapper.toDomain(createUserRequest, User.class); //valida y mapea datos.
      User user = this.userUseCase.save(userResponse);
      return ResponseEntity.ok().body(UserLoadResponse.builder()
         .id(user.getId())
         .username(user.getUsername())
         .email(user.getEmail())
         .role(user.getRole())
         .build());
   }

   @PutMapping("users/{id}") //rol: admin
   public ResponseEntity<?> update(@RequestBody UpdateUserRequest updateUserRequest, @PathVariable Long id) {
      User userResponse = this.updateUserMapping.toDomainModel(updateUserRequest, id);
      User user = this.userUseCase.save(userResponse); //with id
      return ResponseEntity.ok().body(UserLoadResponse.builder()
         .id(user.getId())
         .username(user.getUsername())
         .email(user.getEmail())
         .role(user.getRole())
         .build());
   }

   @DeleteMapping("users/{id}") //rol: admin
   public ResponseEntity<?> delete(@PathVariable Long id) {
      this.userUseCase.delete(id);
      return ResponseEntity.ok().build();
   }

   @GetMapping("users/{id}") //rol: admin
   public ResponseEntity<?> findById(@PathVariable Long id) { // encontrar por id
      Optional<User> userOptional = this.userUseCase.findById(id);
      if (userOptional.isPresent()) {
         User user = userOptional.get();
         return ResponseEntity.ok(UserLoadResponse.builder()
            .id(user.getId())
            .username(user.getUsername())
            .email(user.getEmail())
            .role(user.getRole())
            .build());
      }
      return ResponseEntity.notFound().build();
   }

   @GetMapping("/users") //rol: admin
   public ResponseEntity<?> findAll() { //todos
      List<UserLoadResponse> userLoadResponses = new ArrayList<>();
      this.userUseCase.findAll().forEach(
         user -> userLoadResponses.add(
            UserLoadResponse.builder()
               .id(user.getId())
               .username(user.getUsername())
               .email(user.getEmail())
               .role(user.getRole())
               .build()
         )
      );
      return ResponseEntity.ok().body(userLoadResponses);
   }

}
