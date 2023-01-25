package com.recipesmanager;

import com.recipesmanager.model.Recipe;
import com.recipesmanager.service.RecipesService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RecipeServiceTest {
    @Autowired
    RecipesService service;

    @Autowired
    TestUtils testUtils;

    @BeforeAll
    public void init() {
        testUtils.loadEntities(service);
    }

    @Test
    public void getByIdCheck() {
        Recipe recipe = service.getById(1L);
        assertNotNull(recipe);
    }

    @Test
    public void deleteCheck() {
        service.delete(1L);
        Recipe recipe = service.getById(1L);
        assertNull(recipe);
    }

    @Test
    public void createCheck() {
        Recipe newRecipe = testUtils.createNewEntity();
        service.upsert(newRecipe);
        assertNotNull(service.getById(newRecipe.getId()));
    }

    @Test
    public void updateCheck() {
        Recipe updateRecipe = testUtils.updateEntity();
        service.upsert(updateRecipe);
        Recipe updatedRecipe = service.getById(updateRecipe.getId());
        assertTrue(updatedRecipe.getIngredients().contains("basil"));
        assertTrue(updatedRecipe.getInstructions().contains("pan"));
        assertEquals(1, updatedRecipe.getInstructions().size());
    }

}
