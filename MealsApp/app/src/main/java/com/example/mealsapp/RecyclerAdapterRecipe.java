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
import androidx.recyclerview.widget.RecyclerView;
import androidx.cardview.widget.CardView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapterRecipe extends RecyclerView.Adapter<RecyclerAdapterRecipe.ViewHolder> {

    private ArrayList<Recipe> recipeArrayList;
    private Context context;

    public RecyclerAdapterRecipe(Context context, ArrayList<Recipe> recipes){
        this.context = context;
        recipeArrayList = recipes;
    }

    @NonNull
    @Override
    public RecyclerAdapterRecipe.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterRecipe.ViewHolder holder, int position) {
        Recipe recipe = recipeArrayList.get(position);
        holder.title.setText((recipe.getTitle().toString()));
        Picasso.get().load(recipe.getImage()).into(holder.image);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RecipeDetail.class);

                Bundle bundle = new Bundle();
                bundle.putString("id", recipe.getId());
                bundle.putString("title", recipe.getTitle());
                bundle.putString("category", recipe.getCategory());
                bundle.putString("instructions", recipe.getInstructions());
                bundle.putString("image", recipe.getImage());
                bundle.putString("ing1", recipe.getIng1());
                bundle.putString("measure1", recipe.getMeasure1());

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recipeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView image;
        CardView cardView;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.recipe_name);
            image = itemView.findViewById(R.id.food_image);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}
