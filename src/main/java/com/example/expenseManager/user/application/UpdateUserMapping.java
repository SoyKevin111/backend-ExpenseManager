package com.example.expenseManager.user.application;

import com.example.expenseManager.core.application.exceptions.models.ServerInternalError;
import com.example.expenseManager.user.infraestructure.adapter.in.dto.request.UpdateUserRequest;
import com.example.expenseManager.user.domain.User;
import com.example.expenseManager.user.domain.port.out.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserMapping {

   @Autowired
   IUserRepository userRepository;

   public User toDomainModel(UpdateUserRequest updateUserRequest, Long userId) {
      User user = userRepository.findById(userId).orElseThrow(() -> new ServerInternalError("User not found"));

      if (updateUserRequest.isValidUsername()) {
         user.setUsername(updateUserRequest.getUsername());
      }
      if (updateUserRequest.isValidEmail()) {
         user.setEmail(updateUserRequest.getEmail());
      }
      if (updateUserRequest.isValidPassword()) {
         user.setPassword(updateUserRequest.getPassword());
      }
      if (updateUserRequest.isValidRole()) {
         user.setRole(updateUserRequest.getRole());
      }
      return user;
   }
}