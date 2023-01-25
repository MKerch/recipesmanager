package com.recipesmanager.repository;

import com.recipesmanager.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipesRepo extends JpaRepository<Recipe, Long>, JpaSpecificationExecutor<Recipe> {


}
