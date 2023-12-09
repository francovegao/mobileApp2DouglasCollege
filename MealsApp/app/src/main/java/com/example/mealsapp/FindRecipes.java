package com.example.mealsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mealsapp.databinding.ActivityFindRecipesBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FindRecipes extends AppCompatActivity {
    private ActivityFindRecipesBinding binding;
    private RequestQueue requestQueue;
    private ArrayList<Recipe> recipeArrayList;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFindRecipesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Spinner for search recipe options
        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.recipe_categories, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        binding.categoriesSpinner.setAdapter(adapter);

        //Recycler View for recipes list
        layoutManager = new LinearLayoutManager(this);
        binding.recipesApiRecyclerView.setLayoutManager(layoutManager);

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();
        recipeArrayList=new ArrayList<>();

    }

    private void fetchRecipesByIngredient(String ingredient){
        String url = "https://www.themealdb.com/api/json/v1/1/filter.php?i="+ingredient;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONArray jsonArray = response.getJSONArray("meals");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String id = jsonObject.getString("idMeal");
                    String title = jsonObject.getString("strMeal");
                    //String category = jsonObject.getString("strCategory");
                    //String instructions = jsonObject.getString("strInstructions");
                    String image = jsonObject.getString("strMealThumb");
                    //String ing1 = jsonObject.getString("strIngredient1");
                    //String measure1 = jsonObject.getString("strMeasure1");

                    Recipe recipe = new Recipe(id, title, image);
                    recipeArrayList.add(recipe);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //All data saved in recipeArrayList

            RecyclerAdapterRecipe recipeAdapter = new RecyclerAdapterRecipe(FindRecipes.this, recipeArrayList);
            binding.recipesApiRecyclerView.setAdapter(recipeAdapter);
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FindRecipes.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void fetchRecipesByCategory(String category) {
        String url = "https://www.themealdb.com/api/json/v1/1/filter.php?c="+category;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONArray jsonArray = response.getJSONArray("meals");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String id = jsonObject.getString("idMeal");
                    String title = jsonObject.getString("strMeal");
                    //String category = jsonObject.getString("strCategory");
                    //String instructions = jsonObject.getString("strInstructions");
                    String image = jsonObject.getString("strMealThumb");
                    //String ing1 = jsonObject.getString("strIngredient1");
                    //String measure1 = jsonObject.getString("strMeasure1");

                    Recipe recipe = new Recipe(id, title, image);
                    recipeArrayList.add(recipe);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //All data saved in recipeArrayList

            RecyclerAdapterRecipe recipeAdapter = new RecyclerAdapterRecipe(FindRecipes.this, recipeArrayList);
            binding.recipesApiRecyclerView.setAdapter(recipeAdapter);
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FindRecipes.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    public void searchByIngredient(View view) {
        String searchString= binding.foodSearchEditText.getText().toString();
        recipeArrayList.clear();

        fetchRecipesByIngredient(searchString);
    }
    public void searchByCategory(View view) {
        String searchString= binding.categoriesSpinner.getSelectedItem().toString();
        recipeArrayList.clear();
        fetchRecipesByCategory(searchString);
    }
}