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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HorizontalRecyclerAdapter extends RecyclerView.Adapter<HorizontalRecyclerAdapter.ViewHolder> {
    // List with String type
    private ArrayList<String> ingredientsList;
    private ArrayList<String> measuresList;
    private Context context;
    public HorizontalRecyclerAdapter(Context context, ArrayList<String> ingredients, ArrayList<String> measurements){
        this.context = context;
        ingredientsList = ingredients;
        measuresList = measurements;
    }

    @NonNull
    @Override
    public HorizontalRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cell_horizontal, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalRecyclerAdapter.ViewHolder holder, int position) {
        String ingredient = ingredientsList.get(position);
        String measurement = measuresList.get(position);

        holder.ingredient.setText(ingredient);
        holder.measurements.setText(measurement);
    }

    @Override
    public int getItemCount() {
        int count = 0;

        for (int i = 0; i < ingredientsList.size(); i++){
            if(ingredientsList.get(i)!=null)
                count++;
    }
        return count;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView ingredient;
        TextView measurements;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            ingredient = itemView.findViewById(R.id.ingredientTxtView);
            measurements = itemView.findViewById(R.id.measureTxtView);

        }
    }
}
