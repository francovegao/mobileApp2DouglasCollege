package com.example.mealsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class CalorieTracker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_tracker);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.calorieTracker);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if(id==R.id.mealPlanner){
                    startActivity(new Intent(getApplicationContext(),MealPlanner.class));
                    overridePendingTransition(0,0);
                    return true;
                }else if(id==R.id.calorieTracker){
                    return true;
                }else if(id==R.id.home){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                }
                return false;
            }
        });
    }

    public boolean openLogCaloriesActivity(View view) {
        Intent i = new Intent(getApplicationContext(),LogCaloriesActivity.class);
        int id = view.getId();
        if(id==R.id.breakfastCard){
            i.putExtra("mealType", "Breakfast");
            startActivity(i);
            overridePendingTransition(0,0);
            return true;
        }else if(id==R.id.lunchCard){
            i.putExtra("mealType", "Lunch");
            startActivity(i);
            overridePendingTransition(0,0);
            return true;
        }else if(id==R.id.dinnerCard){
            i.putExtra("mealType", "Dinner");
            startActivity(i);
            overridePendingTransition(0,0);
            return true;
        } else if(id==R.id.snackCard){
            i.putExtra("mealType", "Snack");
            startActivity(i);
            overridePendingTransition(0,0);
            return true;
        }
        return false;
    }
}