package com.example.mealsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class RecyclerAdapterCalories extends RecyclerView.Adapter<RecyclerAdapterCalories.ViewHolder> {

    private ArrayList<Food> foodArrayList;
    private Context context;
    private FoodListItemClickListener foodListItemClickListener;

    public RecyclerAdapterCalories(Context context, ArrayList<Food> foods){
        this.context = context;
        this.foodArrayList = foods;
        this.foodListItemClickListener= (FoodListItemClickListener)context;
    }

    @NonNull
    @Override
    public RecyclerAdapterCalories.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cell_calories, parent, false);
        RecyclerAdapterCalories.ViewHolder viewHolder = new RecyclerAdapterCalories.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterCalories.ViewHolder holder, int position) {
        Food food = foodArrayList.get(position);
        holder.foodName.setText((food.getName().toString()));
        holder.calories.setText((food.getCalories().toString()));


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodListItemClickListener.selectFoodItem(food);

                //Intent intent = new Intent(context, DetailActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("name", food.getName());
                bundle.putString("calories", food.getCalories());

            }
        });

    }

    @Override
    public int getItemCount() {
        return foodArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView foodName;
        TextView calories;
        CardView cardView;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            foodName = itemView.findViewById(R.id.food_name);
            calories = itemView.findViewById(R.id.caloriesTextView);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}