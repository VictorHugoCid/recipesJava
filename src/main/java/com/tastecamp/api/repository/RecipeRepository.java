package com.tastecamp.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tastecamp.api.models.RecipeModel;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeModel, Long> {
    Boolean existsByTitle(String title);

    List<RecipeModel> findByCategoryId(@Param("categoryId") Long categoryId);
}
