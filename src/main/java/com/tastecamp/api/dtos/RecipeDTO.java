package com.tastecamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RecipeDTO {

    @NotBlank
    @Size(max = 150)
    private String title;

    @NotBlank
    private String ingredients;

    @NotBlank
    private String steps;
    
}