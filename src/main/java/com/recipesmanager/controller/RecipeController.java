package com.recipesmanager.controller;

import com.recipesmanager.model.Recipe;
import com.recipesmanager.model.SearchCriteria;
import com.recipesmanager.service.RecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class RecipeController {

    @Autowired
    RecipesService recipesService;

    @PostMapping("/search")
    public List<Recipe> findAllRecipe(@RequestBody List<@Valid SearchCriteria> criteria) {
        return recipesService.findAll(criteria);
    }

    @PostMapping("/create")
    public void createRecipe(@RequestBody Recipe recipe) {
        recipesService.upsert(recipe);
    }

    @PutMapping("/update")
    public void updateRecipe(@RequestBody Recipe recipe) {
        recipesService.upsert(recipe);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipesService.delete(id);
    }

    @GetMapping("/{id}")
    public Recipe getById(@PathVariable Long id) {
        return recipesService.getById(id);
    }

}
