package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AccessContentActivity extends AppCompatActivity {

    Uri CONTENT_URI = Uri.parse("content://com.demo.user.midtermprovider/users");

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_content);
    }

    public void goBack(View view) {
        Intent backScreen = new Intent (this, MainActivity.class);
        startActivity(backScreen);
    }

    public void startThread(View view) {
        runthread();
    }

    private void runthread() {
        Toast.makeText(getBaseContext(), "Thread Starting", Toast.LENGTH_LONG).show();
        //read data from content provider
        TextView resultView= (TextView) findViewById(R.id.res);

        Cursor cursor = getContentResolver().query(Uri.parse("content://com.demo.user.midtermprovider/users"), null, null, null, null);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //put data in listview
                        // to print whole table
                        if(cursor.moveToFirst()) {
                            StringBuilder strBuild=new StringBuilder();
                            while (!cursor.isAfterLast()) {
                                strBuild.append("\n"+cursor.getString(cursor.getColumnIndex("id"))+ " - "+ cursor.getString(cursor.getColumnIndex("name"))  + " - "+ cursor.getString(cursor.getColumnIndex("college")) + " - "+ cursor.getString(cursor.getColumnIndex("department")));
                                cursor.moveToNext();
                            }
                            resultView.setText(strBuild);

                        }
                        else {
                            resultView.setText("No Records Found");
                        }
                    }
                });
            }
        }, 5000);

        Toast.makeText(getBaseContext(), "Thread Ended", Toast.LENGTH_LONG).show();
    }
}