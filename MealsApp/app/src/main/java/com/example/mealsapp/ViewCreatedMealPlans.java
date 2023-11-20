package com.example.mealsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.mealsapp.databinding.ActivityMainBinding;
import com.example.mealsapp.databinding.ActivityViewCreatedMealPlansBinding;

public class ViewCreatedMealPlans extends AppCompatActivity {

    ActivityViewCreatedMealPlansBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewCreatedMealPlansBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // creating a cursor object of the
        // content URI
        Cursor cursor = getContentResolver().query(Uri.parse("content://com.demo.mealplans.provider/mealplans"), null, null, null, null);

        //Setup Adapter
        MealPlanCursorAdapter mealPlanCursorAdapter = new MealPlanCursorAdapter(this, cursor);
        binding.savedMealPlansListView.setAdapter(mealPlanCursorAdapter);
    }
}