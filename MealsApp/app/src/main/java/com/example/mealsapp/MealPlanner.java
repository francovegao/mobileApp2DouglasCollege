package com.example.mealsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mealsapp.databinding.ActivityMealPlannerBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MealPlanner extends AppCompatActivity {

    private ActivityMealPlannerBinding binding;

    private RequestQueue requestQueue;
    private ArrayList<Recipe> randomRecipeArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMealPlannerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();
        randomRecipeArrayList=new ArrayList<>();

        runthread();
        runthread2();

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.mealPlanner);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if(id==R.id.home){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                }else if(id==R.id.mealPlanner){
                    return true;
                }else if(id==R.id.calorieTracker){
                    startActivity(new Intent(getApplicationContext(),CalorieTracker.class));
                    overridePendingTransition(0,0);
                    return true;
                }
                return false;
            }
        });
    }

    private void runthread() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fetchRecipes(binding.randomTitleTxtView, binding.randomRecipeImg1, binding.randomRecipeCard1);
                    }
                });
            }
        }, 500);
    }

    private void runthread2() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fetchRecipes(binding.randomTitleTxtView2, binding.randomRecipeImg2, binding.randomRecipeCard2);
                    }
                });
            }
        }, 1000);
    }

    public void openFindRecipeActivity(View view) {
        startActivity(new Intent(getApplicationContext(), FindRecipes.class));
        overridePendingTransition(0,0);
    }

    private void fetchRecipes(TextView randomRecipeTitle, ImageView randomRecipeImage, CardView cardView){
        String url = "https://www.themealdb.com/api/json/v1/1/random.php";

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

                    Recipe recipe = new Recipe(id, title, category, instructions, image, ing1,ing2,ing3,ing4,ing5,ing6,ing7,ing8,ing9,ing10,ing11,ing12,ing13,ing14,ing15,ing16,ing17,
                            measure1,measure2,measure3,measure4,measure5,measure6,measure7,measure8,measure9,measure10,measure11,measure12,measure13,measure14,measure15,measure16,measure17);
                    randomRecipeArrayList.add(recipe);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //All data saved in recipeArrayList

            Recipe recipe = randomRecipeArrayList.get(randomRecipeArrayList.size()-1);
            randomRecipeTitle.setText(recipe.getTitle());
            Picasso.get().load(recipe.getImage()).into(randomRecipeImage);
            addListenerToCard(cardView, recipe);

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MealPlanner.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void addListenerToCard(CardView cardView, Recipe recipe){
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecipeDetail.class);

                Bundle bundle = new Bundle();
                bundle.putString("title", recipe.getTitle());
                bundle.putString("category", recipe.getCategory());
                bundle.putString("instructions", recipe.getInstructions());
                bundle.putString("image", recipe.getImage());
                bundle.putString("ing1", recipe.getIng1());
                bundle.putString("ing2", recipe.getIng2());
                bundle.putString("ing3", recipe.getIng3());
                bundle.putString("ing4", recipe.getIng4());
                bundle.putString("ing5", recipe.getIng5());
                bundle.putString("ing6", recipe.getIng6());
                bundle.putString("ing7", recipe.getIng7());
                bundle.putString("ing8", recipe.getIng8());
                bundle.putString("ing9", recipe.getIng9());
                bundle.putString("ing10", recipe.getIng10());
                bundle.putString("ing11", recipe.getIng11());
                bundle.putString("ing12", recipe.getIng12());
                bundle.putString("ing13", recipe.getIng13());
                bundle.putString("ing14", recipe.getIng14());
                bundle.putString("ing15", recipe.getIng15());
                bundle.putString("ing16", recipe.getIng16());
                bundle.putString("ing17", recipe.getIng17());
                bundle.putString("measure1", recipe.getMeasure1());
                bundle.putString("measure2", recipe.getMeasure2());
                bundle.putString("measure3", recipe.getMeasure3());
                bundle.putString("measure4", recipe.getMeasure4());
                bundle.putString("measure5", recipe.getMeasure5());
                bundle.putString("measure6", recipe.getMeasure6());
                bundle.putString("measure7", recipe.getMeasure7());
                bundle.putString("measure8", recipe.getMeasure8());
                bundle.putString("measure9", recipe.getMeasure9());
                bundle.putString("measure10", recipe.getMeasure10());
                bundle.putString("measure11", recipe.getMeasure11());
                bundle.putString("measure12", recipe.getMeasure12());
                bundle.putString("measure13", recipe.getMeasure13());
                bundle.putString("measure14", recipe.getMeasure14());
                bundle.putString("measure15", recipe.getMeasure15());
                bundle.putString("measure16", recipe.getMeasure16());
                bundle.putString("measure17", recipe.getMeasure17());

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public void openCreateMealPlanActivity(View view) {
        startActivity(new Intent(getApplicationContext(),CreateMealPlan.class));
        overridePendingTransition(0,0);
    }

    public void openViewCreatedMealPLansActivity(View view) {
        startActivity(new Intent(getApplicationContext(),ViewCreatedMealPlans.class));
        overridePendingTransition(0,0);
    }
}