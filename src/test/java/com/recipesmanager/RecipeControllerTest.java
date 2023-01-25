package com.recipesmanager;

import com.recipesmanager.model.Recipe;
import com.recipesmanager.service.RecipesService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RecipeControllerTest {

    @Autowired
    MockMvc mvc;
    @Autowired
    TestUtils testUtils;
    @Autowired
    RecipesService service;

    @BeforeAll
    public void init() {
        testUtils.loadEntities(service);
    }


    @Test
    public void getById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numberOfServings", is(4)));
    }

    @Test
    public void searchCriteriaCheckAllVegetarian() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/search")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(testUtils.asJsonString(testUtils.getAllVegetarian())))
                .andReturn();

        List<Recipe> recipes = testUtils.readManyEntity(mvcResult.getResponse().getContentAsString());
        assertEquals(recipes.size(), 2);
    }

    @Test
    public void searchCriteriaCheckNumberOfServingsAndIngredients() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/search")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(testUtils.asJsonString(testUtils.getByNumberOfServingsAndIngredient())))
                .andReturn();

        List<Recipe> recipes = testUtils.readManyEntity(mvcResult.getResponse().getContentAsString());

        assertEquals(recipes.size(), 1);
    }

    @Test
    public void searchCriteriaCheckWithOutIngredientAndWithInstructions() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/search")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(testUtils.asJsonString(testUtils.getWithOutIngredientAndWithInstructions())))
                .andReturn();

        List<Recipe> recipes = testUtils.readManyEntity(mvcResult.getResponse().getContentAsString());
        assertEquals(recipes.size(), 1);
    }

    @Test
    public void createCheck() throws Exception {
        Recipe newEntity = testUtils.createNewEntity();

        mvc.perform(MockMvcRequestBuilders.post("/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(testUtils.asJsonString(newEntity)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateCheck() throws Exception {
        Recipe recipe = testUtils.updateEntity();

        mvc.perform(MockMvcRequestBuilders.put("/update")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(testUtils.asJsonString(testUtils.updateEntity())))
                .andExpect(status().isOk());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/" + recipe.getId()))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        assertTrue(contentAsString.contains("sugar"));
    }

    @Test
    public void deleteById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/1"))
                .andExpect(status().isOk());
    }

}
