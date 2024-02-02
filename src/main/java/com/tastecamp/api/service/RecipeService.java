package com.tastecamp.api.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tastecamp.api.dtos.RecipeDTO;
import com.tastecamp.api.errors.RecipeAlreadyExistsException;
import com.tastecamp.api.errors.RecipeNotFoundException;
import com.tastecamp.api.models.RecipeModel;
import com.tastecamp.api.repository.RecipeRepository;

@Service
public class RecipeService {
    
    final RecipeRepository recipeRepository;

    RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<RecipeModel> findAllRecipes() {
        return recipeRepository.findAll();
    }

    public Optional<RecipeModel> findById(Long id) {
        return recipeRepository.findById(id);
    }

    public RecipeModel createRecipe(RecipeDTO body) {
        
        if(recipeRepository.existsByTitle(body.getTitle())){
            throw new RecipeAlreadyExistsException("Já existe uma receita com esse título");
        }
        RecipeModel newRecipe = new RecipeModel(body);
        return recipeRepository.save(newRecipe);
    }

    public void deleteRecipeById(Long id) {
        Optional<RecipeModel> recipe = recipeRepository.findById(id);
        if(!recipe.isPresent()){
            throw new RecipeNotFoundException("Receita não encontrada");
        }
        recipeRepository.deleteById(id);
        // return recipe;
    }

    public RecipeModel updateRecipeById(Long id, RecipeDTO body) {
        Optional<RecipeModel> recipe = recipeRepository.findById(id);
        if(!recipe.isPresent()){
            throw new NoSuchElementException();
        }
        RecipeModel newRecipe = new RecipeModel(body);
        newRecipe.setId(id);
        recipeRepository.save(newRecipe);
        return newRecipe;
    }



}
