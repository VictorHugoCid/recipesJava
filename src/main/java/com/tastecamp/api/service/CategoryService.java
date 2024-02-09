package com.tastecamp.api.service;

import java.util.List;

import com.tastecamp.api.dtos.CategoryDTO;
import com.tastecamp.api.models.CategoryModel;
import com.tastecamp.api.models.RecipeModel;
import com.tastecamp.api.repository.CategoryRepository;
import com.tastecamp.api.repository.RecipeRepository;

public class CategoryService {

    final CategoryRepository categoryRepository;
    final RecipeRepository recipeRepository;

    public CategoryService(CategoryRepository categoryRepository, RecipeRepository recipeRepository){
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    public CategoryModel createCategory(CategoryDTO dto){

        CategoryModel category = new CategoryModel(dto);
        return categoryRepository.save(category);
    }

    public List<RecipeModel> findByCategoryId(Long id){
        return recipeRepository.findByCategoryId(id);
    }
    
}
