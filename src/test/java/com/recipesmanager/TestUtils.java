package com.recipesmanager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipesmanager.model.Recipe;
import com.recipesmanager.model.SearchCriteria;
import com.recipesmanager.service.RecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TestUtils {

    @Autowired
    ObjectMapper objectMapper;

    public void loadEntities(RecipesService service) {
        //Create first Recipe
        Recipe pastaRecipe = new Recipe(false, 4);
        Set<String> pastaIngredients = new HashSet<>();
        pastaIngredients.add("pasta");
        pastaRecipe.setIngredients(pastaIngredients);

        Set<String> pastaInstructions = new HashSet<>();
        pastaInstructions.add("plate");
        pastaInstructions.add("fork");
        pastaRecipe.setInstructions(pastaInstructions);

        service.upsert(pastaRecipe);

        //Create second Recipe
        Recipe soupRecipe = new Recipe(true, 2);
        Set<String> soupIngredients = new HashSet<>();
        soupIngredients.add("tomatoes");
        soupIngredients.add("potatoes");
        soupRecipe.setIngredients(soupIngredients);

        Set<String> soupInstructions = new HashSet<>();
        soupInstructions.add("pan");
        soupRecipe.setInstructions(soupInstructions);

        service.upsert(soupRecipe);

        //Create third Recipe
        Recipe saladRecipe = new Recipe(true, 5);
        Set<String> saladIngredients = new HashSet<>();
        saladIngredients.add("tomatoes");
        saladIngredients.add("potatoes");
        saladRecipe.setIngredients(saladIngredients);

        Set<String> saladInstructions = new HashSet<>();
        saladInstructions.add("pan");
        saladRecipe.setInstructions(saladInstructions);

        service.upsert(saladRecipe);
    }

    public Recipe createNewEntity() {
        Recipe meatballsRecipe = new Recipe(false, 5);
        Set<String> meatballsIngredients = new HashSet<>();
        meatballsIngredients.add("beef");
        meatballsIngredients.add("garlic");
        meatballsRecipe.setIngredients(meatballsIngredients);

        Set<String> meatballsInstructions = new HashSet<>();
        meatballsInstructions.add("pan");
        meatballsRecipe.setInstructions(meatballsInstructions);
        return meatballsRecipe;
    }

    public Recipe updateEntity() {
        Recipe meatballsRecipe = new Recipe(true, 5);
        meatballsRecipe.setId(2);
        Set<String> meatballsIngredients = new HashSet<>();
        meatballsIngredients.add("beef");
        meatballsIngredients.add("garlic");
        meatballsIngredients.add("basil");
        meatballsIngredients.add("sugar");
        meatballsRecipe.setIngredients(meatballsIngredients);

        Set<String> meatballsInstructions = new HashSet<>();
        meatballsInstructions.add("pan");
        meatballsRecipe.setInstructions(meatballsInstructions);
        return meatballsRecipe;
    }

    public String asJsonString(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public List<Recipe> readManyEntity(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, new TypeReference<>() {
        });
    }

    public List<SearchCriteria> getAllVegetarian() {
        List<SearchCriteria> list = new ArrayList<>();
        SearchCriteria criteria = new SearchCriteria();
        criteria.setKey("vegetarian");
        criteria.setOperation("=");
        criteria.setValue(true);
        list.add(criteria);
        return list;
    }


    public List<SearchCriteria> getByNumberOfServingsAndIngredient() {
        List<SearchCriteria> list = new ArrayList<>();
        SearchCriteria criteria1 = new SearchCriteria();
        criteria1.setKey("numberOfServings");
        criteria1.setOperation("=");
        criteria1.setValue(2);
        list.add(criteria1);

        SearchCriteria criteria2 = new SearchCriteria();
        criteria2.setKey("ingredients");
        criteria2.setOperation("=");
        criteria2.setValue("tomatoes");
        list.add(criteria2);
        return list;
    }

    public List<SearchCriteria> getWithOutIngredientAndWithInstructions() {
        List<SearchCriteria> list = new ArrayList<>();
        SearchCriteria criteria = new SearchCriteria();
        criteria.setKey("ingredients");
        criteria.setOperation("!=");
        criteria.setValue("tomatoes");
        list.add(criteria);

        SearchCriteria criteria2 = new SearchCriteria();
        criteria2.setKey("instructions");
        criteria2.setOperation("=");
        criteria2.setValue("fork");
        list.add(criteria2);

        return list;
    }


}
