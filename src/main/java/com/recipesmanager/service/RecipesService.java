package com.recipesmanager.service;

import com.recipesmanager.model.Recipe;
import com.recipesmanager.model.SearchCriteria;
import com.recipesmanager.repository.RecipesRepo;
import com.recipesmanager.specification.RecipeSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipesService {

    @Autowired
    RecipesRepo recipesRepo;

    @Autowired
    RecipeSpecification specification;

    public List<Recipe> findAll(List<SearchCriteria> criteria) {
        Specification searchSpecification = specification.getSearchSpecification(criteria);
        List<Recipe> recipes = recipesRepo.findAll(searchSpecification);
        return recipes;
    }

    public Recipe getById(Long id) {
        Optional<Recipe> recipe = recipesRepo.findById(id);
        return recipe.orElse(null);
    }

    public void upsert(Recipe recipe) {
        Optional<Recipe> dbRecipe = recipesRepo.findById(recipe.getId());
        if (dbRecipe.isPresent()) {
            Recipe updateRecipe = new Recipe();
            updateRecipe.setId(recipe.getId());
            updateRecipe.setVegetarian(recipe.isVegetarian());
            updateRecipe.setNumberOfServings(recipe.getNumberOfServings());
            updateRecipe.setInstructions(recipe.getInstructions());
            updateRecipe.setIngredients(recipe.getIngredients());
            recipesRepo.save(updateRecipe);
        } else {
            recipesRepo.save(recipe);
        }
    }

    public void delete(Long id) {
        recipesRepo.deleteById(id);
    }

    public void deleteAll() {
        recipesRepo.deleteAll();
    }


}
