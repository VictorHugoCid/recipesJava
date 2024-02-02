package com.tastecamp.api.models;

import com.tastecamp.api.dtos.RecipeDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //cria getters e setters genéricos
@NoArgsConstructor
@AllArgsConstructor

@Entity//diz q é uma entidade mapeada no banco de dados
@Table(name = "recipes")
public class RecipeModel {
    @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String title;

    @Column(nullable = false)
    private String ingredients;

    @Column(nullable = false)
    private String steps;


    public RecipeModel(RecipeDTO recipeDTO){
        this.title = recipeDTO.getTitle();
        this.ingredients = recipeDTO.getIngredients();
        this.steps = recipeDTO.getSteps();
    }
}
