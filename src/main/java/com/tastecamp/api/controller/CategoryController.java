package com.tastecamp.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tastecamp.api.dtos.CategoryDTO;
import com.tastecamp.api.models.CategoryModel;
import com.tastecamp.api.models.RecipeModel;
import com.tastecamp.api.service.CategoryService;

import jakarta.validation.Valid;

public class CategoryController {

    final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getRecipeByCategoryId(@PathVariable("id") Long id ){
        List<RecipeModel> recipes = categoryService.findByCategoryId(id);
        return ResponseEntity.status(HttpStatus.OK).body(recipes);
    }
    
    @PostMapping
    public ResponseEntity createCategory(@RequestBody @Valid CategoryDTO categoryDTO ){
        try {
            CategoryModel category = categoryService.createCategory(categoryDTO);
            return ResponseEntity.status(HttpStatus.OK).body(category);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("deu ruim aí, campeão");
        }
    }
}
