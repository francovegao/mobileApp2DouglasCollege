package com.example.mealsapp;

public class CalorieFood {
    private String name;
    private String type;
    private String date;
    private String quantity;
    private String totalCalories;

    public CalorieFood(String name, String type, String date, String quantity, String totalCalories) {
        this.name = name;
        this.type = type;
        this.date = date;
        this.quantity = quantity;
        this.totalCalories = totalCalories;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getTotalCalories() {
        return totalCalories;
    }
    public String getDate() {
        return date;
    }
}
