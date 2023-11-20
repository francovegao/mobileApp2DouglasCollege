package com.example.mealsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.mealsapp.databinding.ActivityLogCaloriesBinding;

public class LogCaloriesActivity extends AppCompatActivity {
    private ActivityLogCaloriesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLogCaloriesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Set the meal type to log depending what card was selected to open this activity
        binding.mealTypeTxtView.setText(getIntent().getStringExtra("mealType"));

    }
}