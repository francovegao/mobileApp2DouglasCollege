package com.example.mealsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mealsapp.databinding.ActivityMealPlannerBinding;
import com.example.mealsapp.databinding.ActivityRecipeDetailBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetail extends AppCompatActivity {

    private RequestQueue requestQueue;

    private ActivityRecipeDetailBinding binding;

    private ArrayList<String> ingredientsList;
    private ArrayList<String> measuresList;

    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRecipeDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Get info from recipes
        Bundle bundle = getIntent().getExtras();
        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        //Recycler View for recipes list
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerViewIngredients.setLayoutManager(layoutManager);
        ingredientsList = new ArrayList<>();
        measuresList = new ArrayList<>();

        if(bundle.getString("instructions")==null){
            fetchRecipeById(bundle.getString("id"));
        }else{
            String recipeTitle = bundle.getString("title");
            String recipeCategory = bundle.getString("category");
            String recipeInstructions = bundle.getString("instructions");
            String recipeImage = bundle.getString("image");
            String ing1 = bundle.getString("ing1");
            String ing2 = bundle.getString("ing2");
            String ing3 = bundle.getString("ing3");
            String ing4 = bundle.getString("ing4");
            String ing5 = bundle.getString("ing5");
            String ing6 = bundle.getString("ing6");
            String ing7 = bundle.getString("ing7");
            String ing8 = bundle.getString("ing8");
            String ing9 = bundle.getString("ing9");
            String ing10 = bundle.getString("ing10");
            String ing11 = bundle.getString("ing11");
            String ing12 = bundle.getString("ing12");
            String ing13 = bundle.getString("ing13");
            String ing14 = bundle.getString("ing14");
            String ing15 = bundle.getString("ing15");
            String ing16 = bundle.getString("ing16");
            String ing17 = bundle.getString("ing17");
            String measure1 = bundle.getString("measure1");
            String measure2 = bundle.getString("measure2");
            String measure3 = bundle.getString("measure3");
            String measure4 = bundle.getString("measure4");
            String measure5 = bundle.getString("measure5");
            String measure6 = bundle.getString("measure6");
            String measure7 = bundle.getString("measure7");
            String measure8 = bundle.getString("measure8");
            String measure9 = bundle.getString("measure9");
            String measure10 = bundle.getString("measure10");
            String measure11 = bundle.getString("measure11");
            String measure12 = bundle.getString("measure12");
            String measure13 = bundle.getString("measure13");
            String measure14 = bundle.getString("measure14");
            String measure15 = bundle.getString("measure15");
            String measure16 = bundle.getString("measure16");
            String measure17 = bundle.getString("measure17");

            Picasso.get().load(recipeImage).into(binding.recipeImageView);
            binding.titleTxtView.setText(recipeTitle);
            binding.recipeInstTxtView.setText(recipeInstructions);

            if(ing1!=null){ingredientsList.add(ing1);}
            if(ing2!=null){ingredientsList.add(ing2);}
            if(ing3!=null){ingredientsList.add(ing3);}
            if(ing4!=null){ingredientsList.add(ing4);}
            if(ing5!=null){ingredientsList.add(ing5);}
            if(ing6!=null){ingredientsList.add(ing6);}
            if(ing7!=null){ingredientsList.add(ing7);}
            if(ing8!=null){ingredientsList.add(ing8);}
            if(ing9!=null){ingredientsList.add(ing9);}
            if(ing10!=null){ ingredientsList.add(ing10);}
            if(ing11!=null){ingredientsList.add(ing11);}
            if(ing12!=null){ingredientsList.add(ing12);}
            if(ing13!=null){ingredientsList.add(ing13);}
            if(ing14!=null){ingredientsList.add(ing14);}
            if(ing15!=null){ingredientsList.add(ing15);}
            if(ing16!=null){ingredientsList.add(ing16);}
            if(ing17!=null){ingredientsList.add(ing17);}
            if(measure1!=null){measuresList.add(measure1);}
            if(measure2!=null){measuresList.add(measure2);}
            if(measure3!=null){measuresList.add(measure3);}
            if(measure4!=null){measuresList.add(measure4);}
            if(measure5!=null){ measuresList.add(measure5);}
            if(measure6!=null){measuresList.add(measure6);}
            if(measure7!=null){measuresList.add(measure7);}
            if(measure8!=null){measuresList.add(measure8);}
            if(measure9!=null){measuresList.add(measure9);}
            if(measure10!=null){measuresList.add(measure10);}
            if(measure11!=null){measuresList.add(measure11);}
            if(measure12!=null){measuresList.add(measure12);}
            if(measure13!=null){measuresList.add(measure13);}
            if(measure14!=null){measuresList.add(measure14);}
            if(measure15!=null){measuresList.add(measure15);}
            if(measure16!=null){measuresList.add(measure16);}
            if(measure17!=null){measuresList.add(measure17);}
            HorizontalRecyclerAdapter ingredientsAdapter = new HorizontalRecyclerAdapter(RecipeDetail.this, ingredientsList, measuresList);
            binding.recyclerViewIngredients.setAdapter(ingredientsAdapter);
       }
    }

    private void fetchRecipeById(String searchId) {
        String url = "https://www.themealdb.com/api/json/v1/1/lookup.php?i="+searchId;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONArray jsonArray = response.getJSONArray("meals");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String id = jsonObject.getString("idMeal");
                    String title = jsonObject.getString("strMeal");
                    String category = jsonObject.getString("strCategory");
                    String instructions = jsonObject.getString("strInstructions");
                    String image = jsonObject.getString("strMealThumb");
                    String ing1 = jsonObject.getString("strIngredient1");
                    String ing2 = jsonObject.getString("strIngredient2");
                    String ing3 = jsonObject.getString("strIngredient3");
                    String ing4 = jsonObject.getString("strIngredient4");
                    String ing5 = jsonObject.getString("strIngredient5");
                    String ing6 = jsonObject.getString("strIngredient6");
                    String ing7 = jsonObject.getString("strIngredient7");
                    String ing8 = jsonObject.getString("strIngredient8");
                    String ing9 = jsonObject.getString("strIngredient9");
                    String ing10 = jsonObject.getString("strIngredient10");
                    String ing11 = jsonObject.getString("strIngredient11");
                    String ing12 = jsonObject.getString("strIngredient12");
                    String ing13 = jsonObject.getString("strIngredient13");
                    String ing14 = jsonObject.getString("strIngredient14");
                    String ing15 = jsonObject.getString("strIngredient15");
                    String ing16 = jsonObject.getString("strIngredient16");
                    String ing17 = jsonObject.getString("strIngredient17");
                    String measure1 = jsonObject.getString("strMeasure1");
                    String measure2 = jsonObject.getString("strMeasure2");
                    String measure3 = jsonObject.getString("strMeasure3");
                    String measure4 = jsonObject.getString("strMeasure4");
                    String measure5 = jsonObject.getString("strMeasure5");
                    String measure6 = jsonObject.getString("strMeasure6");
                    String measure7 = jsonObject.getString("strMeasure7");
                    String measure8 = jsonObject.getString("strMeasure8");
                    String measure9 = jsonObject.getString("strMeasure9");
                    String measure10 = jsonObject.getString("strMeasure10");
                    String measure11 = jsonObject.getString("strMeasure11");
                    String measure12 = jsonObject.getString("strMeasure12");
                    String measure13 = jsonObject.getString("strMeasure13");
                    String measure14 = jsonObject.getString("strMeasure14");
                    String measure15 = jsonObject.getString("strMeasure15");
                    String measure16 = jsonObject.getString("strMeasure16");
                    String measure17 = jsonObject.getString("strMeasure17");

                    Recipe recipe = new Recipe(id, title, category, instructions, image, ing1, measure1);

                    Picasso.get().load(recipe.getImage()).into(binding.recipeImageView);
                    binding.titleTxtView.setText(recipe.getTitle());
                    binding.recipeInstTxtView.setText(recipe.getInstructions());

                    if(ing1!=null){ingredientsList.add(ing1);}
                    if(ing2!=null){ingredientsList.add(ing2);}
                    if(ing3!=null){ingredientsList.add(ing3);}
                    if(ing4!=null){ingredientsList.add(ing4);}
                    if(ing5!=null){ingredientsList.add(ing5);}
                    if(ing6!=null){ingredientsList.add(ing6);}
                    if(ing7!=null){ingredientsList.add(ing7);}
                    if(ing8!=null){ingredientsList.add(ing8);}
                    if(ing9!=null){ingredientsList.add(ing9);}
                   if(ing10!=null){ ingredientsList.add(ing10);}
                    if(ing11!=null){ingredientsList.add(ing11);}
                    if(ing12!=null){ingredientsList.add(ing12);}
                    if(ing13!=null){ingredientsList.add(ing13);}
                    if(ing14!=null){ingredientsList.add(ing14);}
                    if(ing15!=null){ingredientsList.add(ing15);}
                    if(ing16!=null){ingredientsList.add(ing16);}
                    if(ing17!=null){ingredientsList.add(ing17);}
                    if(measure1!=null){measuresList.add(measure1);}
                    if(measure2!=null){measuresList.add(measure2);}
                    if(measure3!=null){measuresList.add(measure3);}
                    if(measure4!=null){measuresList.add(measure4);}
                   if(measure5!=null){ measuresList.add(measure5);}
                    if(measure6!=null){measuresList.add(measure6);}
                    if(measure7!=null){measuresList.add(measure7);}
                    if(measure8!=null){measuresList.add(measure8);}
                    if(measure9!=null){measuresList.add(measure9);}
                    if(measure10!=null){measuresList.add(measure10);}
                    if(measure11!=null){measuresList.add(measure11);}
                    if(measure12!=null){measuresList.add(measure12);}
                    if(measure13!=null){measuresList.add(measure13);}
                    if(measure14!=null){measuresList.add(measure14);}
                    if(measure15!=null){measuresList.add(measure15);}
                    if(measure16!=null){measuresList.add(measure16);}
                    if(measure17!=null){measuresList.add(measure17);}
                    HorizontalRecyclerAdapter ingredientsAdapter = new HorizontalRecyclerAdapter(RecipeDetail.this, ingredientsList, measuresList);
                    binding.recyclerViewIngredients.setAdapter(ingredientsAdapter);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RecipeDetail.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}