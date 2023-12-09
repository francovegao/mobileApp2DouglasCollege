package com.example.mealsapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class CaloriesCursorAdapter extends CursorAdapter {

    public CaloriesCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_cell, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView foodNameTextView = view.findViewById(R.id.cellFood);
        TextView quantityTextView = view.findViewById(R.id.cellQuantity);
        TextView totalCaloriesTextView = view.findViewById(R.id.cellTotalCalories);

        // Extract properties from cursor
        String _id = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
        String foodName = cursor.getString(cursor.getColumnIndexOrThrow("foodName"));
        String quantity = cursor.getString(cursor.getColumnIndexOrThrow("foodQuantity"));
        String totalCalories = cursor.getString(cursor.getColumnIndexOrThrow("caloriesTotal"));

        // Populate fields with extracted properties
        foodNameTextView.setText(foodName);
        quantityTextView.setText(quantity);
        totalCaloriesTextView.setText(totalCalories);
    }
}
