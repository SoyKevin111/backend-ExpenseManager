package com.example.expenseManager.personalFinance.infraestructure.adapter.in.rest;

import com.example.expenseManager.core.application.validations.IdExistenceValidator;
import com.example.expenseManager.personalFinance.infraestructure.adapter.in.dto.request.CreateCategoryRequest;
import com.example.expenseManager.core.application.mappers.RequestGeneralMapper;
import com.example.expenseManager.personalFinance.domain.models.Category;
import com.example.expenseManager.personalFinance.domain.port.in.usecases.ICategoryUseCase;
import com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.entities.CategoryEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager/request")
public class CategoryController {

   //usecases
   @Autowired
   private ICategoryUseCase categoryUseCase;

   //mappers
   @Autowired
   private RequestGeneralMapper requestMapper;

   //validations
   @Autowired
   private IdExistenceValidator idExistenceValidator;

   @PostMapping("/categories")
   public ResponseEntity<?> create(@RequestBody @Valid CreateCategoryRequest createCategoryRequest) {
      Category category = this.requestMapper.toDomain(createCategoryRequest, Category.class);
      Category categoryResponse = this.categoryUseCase.create(category);
      return ResponseEntity.ok().body(categoryResponse);
   }

   @GetMapping("/categories")
   public List<Category> findAll() {
      return this.categoryUseCase.findAll();
   }


   @GetMapping("/categories/{id}")
   public String verificacion(@PathVariable Long id) {
      idExistenceValidator.validateExistsException(CategoryEntity.class, id);
      return "El id esta disponible";
   }


}
