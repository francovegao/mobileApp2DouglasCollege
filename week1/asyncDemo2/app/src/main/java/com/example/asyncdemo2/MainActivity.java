package com.example.asyncdemo2;


import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get reference to start button and result text view
        Button startButton = findViewById(R.id.start_button);
        resultTextView = findViewById(R.id.result_text_view);

        // Set an OnClickListener for the start button
        startButton.setOnClickListener(view -> new BackgroundTask().execute());
    }

    // BackgroundTask inner class to perform the background task
    private class BackgroundTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            // Perform background task
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Task Completed";
        }

        @Override
        protected void onPostExecute(String result) {
            // Update UI with the results
            resultTextView.setText(result);
        }
    }
}