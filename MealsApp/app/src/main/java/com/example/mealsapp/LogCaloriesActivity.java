package com.example.mealsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mealsapp.databinding.ActivityLogCaloriesBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LogCaloriesActivity extends AppCompatActivity implements FoodListItemClickListener{
    private ActivityLogCaloriesBinding binding;

    private RequestQueue requestQueue;
    private ArrayList<Food> foodArrayList;

    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLogCaloriesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Set the meal type to log depending what card was selected to open this activity
        binding.dateTxtView.setText(getIntent().getStringExtra("date"));
        binding.mealTypeTxtView.setText(getIntent().getStringExtra("mealType"));

        //Recycler View for recipes list
        layoutManager = new LinearLayoutManager(this);
        binding.caloriesApiRecyclerView.setLayoutManager(layoutManager);

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();
        foodArrayList=new ArrayList<>();

        //Listener for total calories
        binding.calQtyEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                calculateTotalCalories();
                return false;
            }
        });
    }
    public void saveCaloriesInfo(View view) {
        // class to add values in the database
        ContentValues values = new ContentValues();

        // fetching text from user
        //Added the age and program information to content values
        values.put(CaloriesContentProvider.date, binding.dateTxtView.getText().toString());
        values.put(CaloriesContentProvider.foodType, binding.mealTypeTxtView.getText().toString());
        values.put(CaloriesContentProvider.foodName, binding.foodNameEditText.getText().toString());
        values.put(CaloriesContentProvider.caloriesPerServing, binding.calPerServingEditText.getText().toString());
        values.put(CaloriesContentProvider.foodQuantity, binding.calQtyEditText.getText().toString());
        values.put(CaloriesContentProvider.caloriesTotal, binding.totalCalTxtView.getText().toString());

        // inserting into database through content URI
        //Insert the values from edit text to the uri
        getContentResolver().insert(CaloriesContentProvider.CONTENT_URI, values);

        // displaying a toast message
        Toast.makeText(getBaseContext(), "Calories Logged", Toast.LENGTH_LONG).show();

        Intent i = new Intent(getApplicationContext(),CalorieTracker.class);
        i.putExtra("date", binding.dateTxtView.getText().toString());
        startActivity(i);
        overridePendingTransition(0,0);
    }

    public void searchFoods(View view) {
        String searchString= binding.calorieSearchEditText.getText().toString();
        foodArrayList.clear();

        fetchFoods(searchString);
    }

    public void fetchFoods(String searchString){
        //String url = "https://api.api-ninjas.com/v1/nutrition?query="+searchString;
        String url="https://api.edamam.com/api/food-database/v2/parser?app_id=8dbe2d2b&app_key=f63008b2d5de379e6d1aa8aa2d76ccdc&ingr="+searchString+"&nutrition-type=cooking";



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONArray jsonArray = response.getJSONArray("hints");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("food");
                    String name = jsonObject1.getString("label");
                    JSONObject jsonObject2 = jsonObject1.getJSONObject("nutrients");
                    Double caloriesDouble= jsonObject2.getDouble("ENERC_KCAL");
                    String calories = String.format("%.2f",caloriesDouble);

                    Food food = new Food(name, calories);
                    foodArrayList.add(food);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //All data saved in foodArrayList

            RecyclerAdapterCalories caloriesAdapter = new RecyclerAdapterCalories(LogCaloriesActivity.this, foodArrayList);
            binding.caloriesApiRecyclerView.setAdapter(caloriesAdapter);
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LogCaloriesActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void calculateTotalCalories() {
        //Get calories
        String stringCalories = binding.calPerServingEditText.getText().toString();
        Double doubleCalories = 0.0;
        if(stringCalories.equals("")){
            doubleCalories = 0.0;
        }else{
            doubleCalories=Double.parseDouble(stringCalories);
        }

        //Calculate calories
        Double totalCalories = doubleCalories*(Double.parseDouble(binding.calQtyEditText.getText().toString()));

        // display calories
        binding.totalCalTxtView.setText(totalCalories.toString());
    }
    @Override
    public void selectFoodItem(Food food) {
        binding.foodNameEditText.setText(food.getName());
        binding.calQtyEditText.setText("1");
        binding.calPerServingEditText.setText(food.getCalories().toString());
        calculateTotalCalories();
    }
}