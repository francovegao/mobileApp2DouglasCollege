package com.example.mealsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mealsapp.databinding.ActivityCreateMealPlanBinding;
import com.example.mealsapp.databinding.ActivityMainBinding;

public class CreateMealPlan extends AppCompatActivity {

    private ActivityCreateMealPlanBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCreateMealPlanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void saveMealPlan(View view) {
        // class to add values in the database
        ContentValues values = new ContentValues();

        // fetching text from user
        //Added the age and program information to content values
        values.put(MealPlanContentProvider.mealPlanName, binding.newMealPlanName.getText().toString());
        values.put(MealPlanContentProvider.breakfastDay1, binding.breakfastDay1EditTxt.getText().toString());
        values.put(MealPlanContentProvider.lunchDay1, binding.lunchDay1EditTxt.getText().toString());
        values.put(MealPlanContentProvider.dinnerDay1, binding.dinnerDay1EditTxt.getText().toString());
        values.put(MealPlanContentProvider.snackDay1, binding.snackDay1EditTxt.getText().toString());
        values.put(MealPlanContentProvider.breakfastDay2, binding.breakfastDay2EditTxt.getText().toString());
        values.put(MealPlanContentProvider.lunchDay2, binding.lunchDay2EditTxt.getText().toString());
        values.put(MealPlanContentProvider.dinnerDay2, binding.dinnerDay2EditTxt.getText().toString());
        values.put(MealPlanContentProvider.snackDay2, binding.snackDay2EditTxt.getText().toString());
        values.put(MealPlanContentProvider.breakfastDay3, binding.breakfastDay3EditTxt.getText().toString());
        values.put(MealPlanContentProvider.lunchDay3, binding.lunchDay3EditTxt.getText().toString());
        values.put(MealPlanContentProvider.dinnerDay3, binding.dinnerDay3EditTxt.getText().toString());
        values.put(MealPlanContentProvider.snackDay3, binding.snackDay3EditTxt.getText().toString());
        values.put(MealPlanContentProvider.breakfastDay4, binding.breakfastDay4EditTxt.getText().toString());
        values.put(MealPlanContentProvider.lunchDay4, binding.lunchDay4EditTxt.getText().toString());
        values.put(MealPlanContentProvider.dinnerDay4, binding.dinnerDay4EditTxt.getText().toString());
        values.put(MealPlanContentProvider.snackDay4, binding.snackDay4EditTxt.getText().toString());
        values.put(MealPlanContentProvider.breakfastDay5, binding.breakfastDay5EditTxt.getText().toString());
        values.put(MealPlanContentProvider.lunchDay5, binding.lunchDay5EditTxt.getText().toString());
        values.put(MealPlanContentProvider.dinnerDay5, binding.dinnerDay5EditTxt.getText().toString());
        values.put(MealPlanContentProvider.snackDay5, binding.snackDay5EditTxt.getText().toString());

        // inserting into database through content URI
        //Insert the values from edit text to the uri
        getContentResolver().insert(MealPlanContentProvider.CONTENT_URI, values);

        // displaying a toast message
        Toast.makeText(getBaseContext(), "New Meal Plan Created", Toast.LENGTH_LONG).show();
    }
}