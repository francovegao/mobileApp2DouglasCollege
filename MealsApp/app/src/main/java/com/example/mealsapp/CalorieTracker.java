package com.example.mealsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.mealsapp.databinding.ActivityCalorieTrackerBinding;
import com.example.mealsapp.databinding.ActivityLogCaloriesBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Calendar;


public class CalorieTracker extends AppCompatActivity {
    private ActivityCalorieTrackerBinding binding;
    //date Picker
    private static DatePickerDialog datePickerDialog;
    private static int progr = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalorieTrackerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //date Picker
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            datePickerDialog = new DatePickerDialog(CalorieTracker.this);
        }
        initDatePicker();
        binding.datePickerButtonMain.setText(getTodaysDate());

        if(getIntent().hasExtra("date"))
            binding.datePickerButtonMain.setText(getIntent().getStringExtra("date"));

        loadFromContentProvider();

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.calorieTracker);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if(id==R.id.mealPlanner){
                    startActivity(new Intent(getApplicationContext(),MealPlanner.class));
                    overridePendingTransition(0,0);
                    return true;
                }else if(id==R.id.calorieTracker){
                    return true;
                }else if(id==R.id.home){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                }
                return false;
            }
        });
    }
    private void loadFromContentProvider() {
        ArrayList<CalorieFood> selectedDateItems = new ArrayList<>();
        // creating a cursor object of the
        // content URI
        Cursor cursor = getContentResolver().query(Uri.parse("content://com.demo.calories.provider/calories"), null, null, null, null);

        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                // Extract properties from cursor
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                String type = cursor.getString(cursor.getColumnIndexOrThrow("foodType"));
                String foodName = cursor.getString(cursor.getColumnIndexOrThrow("foodName"));
                String quantity = cursor.getString(cursor.getColumnIndexOrThrow("foodQuantity"));
                String totalCalories = cursor.getString(cursor.getColumnIndexOrThrow("caloriesTotal"));

                // Populate fields with extracted properties

                CalorieFood calorieFood = new CalorieFood(foodName, type, date, quantity, totalCalories);
                //if (date == binding.datePickerButtonMain.getText().toString()) {
                    if (date.equals(binding.datePickerButtonMain.getText().toString())) {
                    selectedDateItems.add(calorieFood);
                }
            }
        }

        loadBreakfast(selectedDateItems);
        loadLunch(selectedDateItems);
        loadDinner(selectedDateItems);
        loadSnack(selectedDateItems);
        updateProgressBar(selectedDateItems);

    }
    private void loadBreakfast(ArrayList<CalorieFood> calorieFoodItems){
        ArrayList<CalorieFood> breakfastItems = new ArrayList<>();
        for(CalorieFood calorieFood : calorieFoodItems){
            if(calorieFood.getType().equals("Breakfast"))
                breakfastItems.add(calorieFood);
        }

        //Setup Adapter
        CaloriesFoodAdapter caloriesFoodAdapter = new CaloriesFoodAdapter(this, breakfastItems);
        binding.mealPlanListViewBreakfast.setAdapter(caloriesFoodAdapter);
    }
    private void loadLunch(ArrayList<CalorieFood> calorieFoodItems){
        ArrayList<CalorieFood> lunchItems = new ArrayList<>();
        for(CalorieFood calorieFood : calorieFoodItems){
            if(calorieFood.getType().equals("Lunch"))
                lunchItems.add(calorieFood);
        }

        //Setup Adapter
        CaloriesFoodAdapter caloriesFoodAdapter = new CaloriesFoodAdapter(this, lunchItems);
        binding.mealPlanListViewLunch.setAdapter(caloriesFoodAdapter);
    }
    private void loadSnack(ArrayList<CalorieFood> calorieFoodItems){
        ArrayList<CalorieFood> snackItems = new ArrayList<>();
        for(CalorieFood calorieFood : calorieFoodItems){
            if(calorieFood.getType().equals("Snack"))
                snackItems.add(calorieFood);
        }

        //Setup Adapter
        CaloriesFoodAdapter caloriesFoodAdapter = new CaloriesFoodAdapter(this, snackItems);
        binding.mealPlanListViewSnack.setAdapter(caloriesFoodAdapter);
    }
    private void loadDinner(ArrayList<CalorieFood> calorieFoodItems){
        ArrayList<CalorieFood> dinnerItems = new ArrayList<>();
        for(CalorieFood calorieFood : calorieFoodItems){
            if(calorieFood.getType().equals("Dinner"))
                dinnerItems.add(calorieFood);
        }

        //Setup Adapter
        CaloriesFoodAdapter caloriesFoodAdapter = new CaloriesFoodAdapter(this, dinnerItems);
        binding.mealPlanListViewDinner.setAdapter(caloriesFoodAdapter);
    }
    private void updateProgressBar(ArrayList<CalorieFood> calorieFoodItems) {
        Double dayCalories=0.0;
        if(calorieFoodItems.isEmpty()){
            binding.progressBar.setProgress(0);
            binding.textViewProgress.setText("0");
        }else{
            for(CalorieFood calorieFoodItem : calorieFoodItems){
                if(!calorieFoodItem.getTotalCalories().equals(""))
                    dayCalories+=Double.parseDouble(calorieFoodItem.getTotalCalories());
            }
            progr= (int) (dayCalories*100)/2200;
            binding.progressBar.setProgress(progr);
            String formattedCalories = String.format("%.2f",dayCalories);
            binding.textViewProgress.setText(formattedCalories);
        }
    }
    public boolean openLogCaloriesActivity(View view) {
        Intent i = new Intent(getApplicationContext(),LogCaloriesActivity.class);
        i.putExtra("date", binding.datePickerButtonMain.getText().toString());
        int id = view.getId();
        if(id==R.id.breakfastCard){
            i.putExtra("mealType", "Breakfast");
            startActivity(i);
            overridePendingTransition(0,0);
            return true;
        }else if(id==R.id.lunchCard){
            i.putExtra("mealType", "Lunch");
            startActivity(i);
            overridePendingTransition(0,0);
            return true;
        }else if(id==R.id.dinnerCard){
            i.putExtra("mealType", "Dinner");
            startActivity(i);
            overridePendingTransition(0,0);
            return true;
        } else if(id==R.id.snackCard){
            i.putExtra("mealType", "Snack");
            startActivity(i);
            overridePendingTransition(0,0);
            return true;
        }
        return false;
    }
    //Date picker methods
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                binding.datePickerButtonMain.setText(date);
                loadFromContentProvider();
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            datePickerDialog.setOnDateSetListener(dateSetListener);
        }
        datePickerDialog.updateDate(year, month, day);
        //datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }
    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month=month+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month)+" "+day+" "+year;
    }

    private String getMonthFormat(int month) {
        switch (month){
            case 1:
                return "Jan";
            case 2:
                return "Feb";
            case 3:
                return "Mar";
            case 4:
                return "Apr";
            case 5:
                return "May";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Aug";
            case 9:
                return "Sep";
            case 10:
                return "Oct";
            case 11:
                return "Nov";
            case 12:
                return "Dec";
            default:
                return "Jan";
        }
    }

    public void selectDate(View view) {
        datePickerDialog.show();
        loadFromContentProvider();
    }
}