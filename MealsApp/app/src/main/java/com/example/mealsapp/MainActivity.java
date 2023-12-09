package com.example.mealsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.mealsapp.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Double time = 0.0;
    private Intent timerServiceIntent;
    private boolean timerStarted = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        
        //Load graph
        loadGraphInfo();

        //Initialize timer service
        timerServiceIntent = new Intent(getApplicationContext(), TimerService.class);
        registerReceiver(updateTime, new IntentFilter(TimerService.TIMER_UPDATED));
        //new Intent( this, TimerService.class );

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if(id==R.id.mealPlanner){
                    startActivity(new Intent(getApplicationContext(),MealPlanner.class));
                    overridePendingTransition(0,0);
                    return true;
                }else if(id==R.id.home){
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
    private void loadGraphInfo() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadTodayCalories();
                    }

                    private void loadTodayCalories() {
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
                                if (date.equals(getsTodayDate())) {
                                    selectedDateItems.add(calorieFood);
                                }
                            }
                        }

                        Double breakfastSumCalories=0.0;
                        for(CalorieFood calorieFood : selectedDateItems){
                            if(calorieFood.getType().equals("Breakfast"))
                                breakfastSumCalories= Double.parseDouble(calorieFood.getTotalCalories());
                        }

                        Double lunchSumCalories=0.0;
                        for(CalorieFood calorieFood : selectedDateItems){
                            if(calorieFood.getType().equals("Lunch"))
                                lunchSumCalories= Double.parseDouble(calorieFood.getTotalCalories());
                        }

                        Double dinnerSumCalories=0.0;
                        for(CalorieFood calorieFood : selectedDateItems){
                            if(calorieFood.getType().equals("Dinner"))
                                dinnerSumCalories= Double.parseDouble(calorieFood.getTotalCalories());
                        }

                        Double snackSumCalories=0.0;
                        for(CalorieFood calorieFood : selectedDateItems){
                            if(calorieFood.getType().equals("Snack"))
                                snackSumCalories= Double.parseDouble(calorieFood.getTotalCalories());
                        }

                        populateGraph(breakfastSumCalories, lunchSumCalories, dinnerSumCalories, snackSumCalories);
                    }

                    private void populateGraph(Double breakfastSumCalories, Double lunchSumCalories, Double dinnerSumCalories, Double snackSumCalories) {
                        BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(new DataPoint[] {
                                new DataPoint(1, breakfastSumCalories),
                                new DataPoint(2, lunchSumCalories),
                                new DataPoint(3, dinnerSumCalories),
                                new DataPoint(4, snackSumCalories)
                        });


                        binding.graphView.setTitle("Calories per Meal (Today)");
                        binding.graphView.setTitleTextSize(60);

                        GridLabelRenderer gridLabel = binding.graphView.getGridLabelRenderer();
                        gridLabel.setHorizontalAxisTitle("Meal Type");
                        gridLabel.setVerticalAxisTitle("Calories");

                        // use static labels for horizontal and vertical labels
                        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(binding.graphView);
                        staticLabelsFormatter.setHorizontalLabels(new String[] {"breakfast","lunch", "dinner", "snack", ""});
                        binding.graphView.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);


                        binding.graphView.getGridLabelRenderer().setVerticalLabelsVisible(false);
                        binding.graphView.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);

                        // set manual X bounds
                        binding.graphView.getViewport().setXAxisBoundsManual(true);
                        binding.graphView.getViewport().setMinX(0.5);
                        binding.graphView.getViewport().setMaxX(4.5);

                        // set manual Y bounds
                        binding.graphView.getViewport().setYAxisBoundsManual(true);
                        binding.graphView.getViewport().setMinY(0);
                        binding.graphView.getViewport().setMaxY(1000);

                        series.setSpacing(50);
                        series.setDrawValuesOnTop(true);
                        series.setValuesOnTopSize(70);
                        series.setValuesOnTopColor(ContextCompat.getColor(getApplicationContext(), R.color.my_red));
                        series.setColor(ContextCompat.getColor(getApplicationContext(), R.color.my_blue));
                        binding.graphView.addSeries(series);
                    }

                    private String getsTodayDate() {
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
                });
            }
        }, 100);
    }
    public void openMealPlannerActivity(View view) {
        startActivity(new Intent(getApplicationContext(),MealPlanner.class));
        overridePendingTransition(0,0);
    }
    public void openCalorieTrackerActivity(View view) {
        startActivity(new Intent(getApplicationContext(),CalorieTracker.class));
        overridePendingTransition(0,0);
    }
    public void StartStopTimer(View view){
        if(timerStarted)
            stopTimer();
        else
            startTimer();
    }
    public void ResetTimer(View view){
        stopTimer();

        //show dialog
        String totalTime = binding.timerClock.getText().toString();
        endFasting(totalTime);
        //reset
        time=0.0;
        binding.timerClock.setText(getTimeStringFromDouble(time));
    }
    private void startTimer(){
        timerServiceIntent.putExtra(TimerService.TIME_EXTRA, time);
        startService(timerServiceIntent );
        binding.startStopButton.setText("Pause");
        timerStarted=true;
    }
    public void stopTimer() {
        // stopping the service
        stopService(timerServiceIntent);
        binding.startStopButton.setText("Start");
        timerStarted=false;
    }
    private BroadcastReceiver updateTime = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            time = intent.getDoubleExtra(TimerService.TIME_EXTRA, 0.0);
            binding.timerClock.setText(getTimeStringFromDouble(time));
        }
    };
    private String getTimeStringFromDouble(double time) {
        int resultInt = (int) Math.round(time);
        int hours = resultInt % 86400 / 3600;
        int minutes = resultInt % 86400 % 3600 / 60;
        int seconds = resultInt % 86400 % 3600 % 60;
        return makeTimeString(hours, minutes, seconds);
    }
    private String makeTimeString(int hour, int min, int sec) {
        return String.format(Locale.getDefault(), "%02d:%02d:%02d", hour, min, sec);
    }
    public void endFasting(String fastingTotalTime) {
        // Create the object of AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        // Set the message show for the Alert time
        builder.setMessage("You fasted for " +fastingTotalTime+" , well done!");

        // Set Alert Title
        builder.setTitle("Fasting Completed, Congratulations!");

        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Done", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the user click yes button then app will close
            dialog.cancel();
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }
}