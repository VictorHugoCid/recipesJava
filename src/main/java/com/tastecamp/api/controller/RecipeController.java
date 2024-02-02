package com.tastecamp.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tastecamp.api.dtos.RecipeDTO;
import com.tastecamp.api.errors.RecipeAlreadyExistsException;
import com.tastecamp.api.errors.RecipeNotFoundException;
import com.tastecamp.api.models.RecipeModel;
import com.tastecamp.api.service.RecipeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    final RecipeService recipeService;

    RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping()
    public  ResponseEntity<List<RecipeModel>> getRecipes() {
        return ResponseEntity.status(HttpStatus.OK).body(recipeService.findAllRecipes());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<RecipeModel> getRecipesById(@PathVariable Long id) {
        // Optional<RecipeModel> recipe = recipeRepository.findById(id);
        Optional<RecipeModel> recipe = recipeService.findById(id);
        if(!recipe.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe uma receita com esse id" );
        }
        return ResponseEntity.status(HttpStatus.OK).body(recipe.get());
    }

    //USAR ESSE AQUI COMO REFERENCIA
    //uma opção é deixar o response sem tipo
    @PostMapping()
    public ResponseEntity createRecipe(@RequestBody @Valid RecipeDTO body) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.createRecipe(body));
        } catch (RecipeAlreadyExistsException error) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    //esse Void aqui tem q ser maiúsculo
    // ResponseEntity<Void>
    public ResponseEntity<String> deleteRecipe(@PathVariable Long id){

        try {
            recipeService.deleteRecipeById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (RecipeNotFoundException error) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }
    }

    @PutMapping("/{id}")
    public RecipeModel updateRecipe(@PathVariable Long id, @RequestBody RecipeDTO body) {
        return recipeService.updateRecipeById(id, body);
    }
}
