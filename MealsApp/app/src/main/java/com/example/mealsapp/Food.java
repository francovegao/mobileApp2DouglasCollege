package com.example.mealsapp;

public class Food {
    private String name;
    private String calories;

    public Food(String name, String calories) {
        this.name = name;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public String getCalories() {
        return calories;
    }
}
