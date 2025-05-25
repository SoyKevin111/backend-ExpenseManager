package com.example.expenseManager.user.application;

import com.example.expenseManager.core.application.exceptions.models.ServerInternalError;
import com.example.expenseManager.user.infraestructure.adapter.in.dto.request.UpdateProfileRequest;
import com.example.expenseManager.user.domain.User;
import com.example.expenseManager.user.domain.port.out.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateProfileMapping {

   @Autowired
   IUserRepository userRepository;

   public User toDomainModel(UpdateProfileRequest updateProfileRequest, Long id) {
      User user = userRepository.findById(id).orElseThrow(() -> new ServerInternalError("User not found"));
      user.setUsername(updateProfileRequest.getUsername());
      return user;
   }
}
