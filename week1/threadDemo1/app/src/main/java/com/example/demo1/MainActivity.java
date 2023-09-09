package com.example.demo1;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText et_query;
    EditText et_query2;
    TextView textView;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_query = findViewById(R.id.et_query);
        et_query2 = findViewById(R.id.et_query2);
        textView = findViewById(R.id.text);
        textView2 = findViewById(R.id.text2);
        findViewById(R.id.bt_click)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        runthread();
                    }
                });
        findViewById(R.id.bt_click2)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        runthread2();
                    }
                });

        try{
            Thread.sleep(10000); //delay the start of the app
        }catch(Exception e){
        }
    }

    private void runthread2() {
        final String s2 = et_query2.getText()
                .toString();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView2.setText(s2);
                    }
                });
            }
        }, 5000);
    }

    private void runthread() {
        final String s1 = et_query.getText()
                .toString();
        Handler handler = new Handler();
        try{
            Thread.sleep(5000); //delay the app and loose control of the app
        }catch(Exception e){
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(s1);
                    }
                });
            }
        }, 5000);
    }
}