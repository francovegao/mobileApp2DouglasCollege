package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //method that reads the UI touch events
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

    //Method on click add the infro from the edit texts
    public void onClickAddDetails(View view) {

        // class to add values in the database
        ContentValues values = new ContentValues();

        // fetching text from user
        //Added the age and program information to content values
        values.put(MyContentProvider.name, ((EditText) findViewById(R.id.textName)).getText().toString());
        values.put(MyContentProvider.age, ((EditText) findViewById(R.id.textAge)).getText().toString());
        values.put(MyContentProvider.program, ((EditText) findViewById(R.id.textProgram)).getText().toString());

        // inserting into database through content URI
        //Insert the values from edit text to the uri
        getContentResolver().insert(MyContentProvider.CONTENT_URI, values);

        // displaying a toast message
        Toast.makeText(getBaseContext(), "New Record Inserted", Toast.LENGTH_LONG).show();
    }

    public void onClickShowDetails(View view) {
        // inserting complete table details in this text field
        TextView resultView= (TextView) findViewById(R.id.res);

        // creating a cursor object of the
        // content URI
        Cursor cursor = getContentResolver().query(Uri.parse("content://com.demo.user.provider/users"), null, null, null, null);

        // iteration of the cursor
        // to print whole table
        if(cursor.moveToFirst()) {
            StringBuilder strBuild=new StringBuilder();
            while (!cursor.isAfterLast()) {
                strBuild.append("\n"+cursor.getString(cursor.getColumnIndex("id"))+ " - "+ cursor.getString(cursor.getColumnIndex("name"))+" - "+ cursor.getString(cursor.getColumnIndex("age"))+" - "+ cursor.getString(cursor.getColumnIndex("program")));
                cursor.moveToNext();
            }
            resultView.setText(strBuild);
        }
        else {
            resultView.setText("No Records Found");
        }
    }
}