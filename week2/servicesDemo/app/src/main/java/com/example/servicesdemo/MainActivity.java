package com.example.servicesdemo;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.transition.Fade;
import androidx.transition.Slide;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // declaring objects of Button class
    private Button start, stop;
    private ImageView bagpipeImage;

    private LinearLayout linearLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        bagpipeImage = findViewById(R.id.bagpipeImage);
        linearLayout=findViewById(R.id.linearLayout);

        // assigning ID of startButton
        // to the object start
        start = (Button) findViewById( R.id.startButton );

        // assigning ID of stopButton
        // to the object stop
        stop = (Button) findViewById( R.id.stopButton );

        // declaring listeners for the
        // buttons to make them respond
        // correctly according to the process
        start.setOnClickListener( this );
        stop.setOnClickListener( this );
    }

    public void onClick(View view) {

        // process to be performed
        // if start button is clicked
        if(view == start){

            // starting the service
            startService(new Intent( this, NewService.class ) );

            imageTransition();

            //bagpipeImage.setVisibility(View.VISIBLE);

            //Transition transition = new Slide(Gravity.BOTTOM);
           // transition.setDuration(900);
            //transition.addTarget(R.id.bagpipeImage);

            //TransitionManager.beginDelayedTransition(linearLayout, transition);
            //bagpipeImage.setVisibility(View.VISIBLE);
        }

        // process to be performed
        // if stop button is clicked
        else if (view == stop){

            // stopping the service
            stopService(new Intent( this, NewService.class ) );

            imageTransition();
            //bagpipeImage.setVisibility(View.GONE);
        }
    }

    private void imageTransition() {
        Transition transition = new Slide(Gravity.BOTTOM);
        //Transition transition = new Fade();
        transition.setDuration(900);
        transition.addTarget(R.id.bagpipeImage);

        TransitionManager.beginDelayedTransition(linearLayout, transition);
        if(bagpipeImage.getVisibility()==View.VISIBLE)
            bagpipeImage.setVisibility(View.INVISIBLE);
        else
            bagpipeImage.setVisibility(View.VISIBLE);
    }
}