package com.example.mealsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CaloriesFoodAdapter extends ArrayAdapter<CalorieFood> {

    public CaloriesFoodAdapter(Context context, List<CalorieFood> calorieFoodsItems) {
        super(context, 0, calorieFoodsItems);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        CalorieFood calorieFoodItem = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_cell, parent, false);

        TextView foodName = convertView.findViewById(R.id.cellFood);
        TextView quantity = convertView.findViewById(R.id.cellQuantity);
        TextView totalCalories = convertView.findViewById(R.id.cellTotalCalories);

        // Populate fields with extracted properties
        foodName.setText(calorieFoodItem.getName());
        quantity.setText(calorieFoodItem.getQuantity());
        totalCalories.setText(calorieFoodItem.getTotalCalories());

        return convertView;
    }
}
