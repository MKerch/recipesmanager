package com.recipesmanager.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private boolean vegetarian;
    @Min(1)
    @Max(5)
    private int numberOfServings;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "ingredient")
    private Set<String> ingredients = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "instruction")
    private Set<String> instructions = new HashSet<>();

    public Recipe(boolean vegetarian, int numberOfServings) {
        this.vegetarian = vegetarian;
        this.numberOfServings = numberOfServings;
    }

    public Recipe() {

    }


    public Long getId() {
        return id;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getNumberOfServings() {
        return numberOfServings;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public Set<String> getInstructions() {
        return instructions;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public void setNumberOfServings(int numberOfServings) {
        this.numberOfServings = numberOfServings;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setInstructions(Set<String> instructions) {
        this.instructions = instructions;
    }

}
