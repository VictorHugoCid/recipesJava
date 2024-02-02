package com.tastecamp.api.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.catalina.mbeans.UserMBean;
import org.springframework.stereotype.Service;

import com.tastecamp.api.dtos.RecipeDTO;
import com.tastecamp.api.errors.RecipeAlreadyExistsException;
import com.tastecamp.api.errors.RecipeNotFoundException;
import com.tastecamp.api.models.RecipeModel;
import com.tastecamp.api.models.UserModel;
import com.tastecamp.api.repository.RecipeRepository;
import com.tastecamp.api.repository.UserRepository;

@Service
public class RecipeService {
    
    final RecipeRepository recipeRepository;
    final UserRepository userRepository;

    RecipeService(RecipeRepository recipeRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    public List<RecipeModel> findAllRecipes() {
        return recipeRepository.findAll();
    }

    public Optional<RecipeModel> findById(Long id) {
        return recipeRepository.findById(id);
    }

    public Optional<RecipeModel> createRecipe(RecipeDTO body) {
        // if(userRepository.existsBy){

        // }
        
        if(recipeRepository.existsByTitle(body.getTitle())){
            throw new RecipeAlreadyExistsException("Já existe uma receita com esse título");
        }

        Optional<UserModel> user = userRepository.findById(body.getAuthorId());
        if(!user.isPresent()){
            return user.empty();
        }

        RecipeModel newRecipe = new RecipeModel(body, user.get());
        return Optional.of(recipeRepository.save(newRecipe));
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
