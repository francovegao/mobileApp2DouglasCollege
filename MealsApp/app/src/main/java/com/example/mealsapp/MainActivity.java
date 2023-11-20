package com.example.mealsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.mealsapp.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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