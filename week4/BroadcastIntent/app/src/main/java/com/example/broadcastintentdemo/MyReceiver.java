package com.example.broadcastintentdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");

        String message = intent.getStringExtra("someKey")
                +" "+intent.getAction();


        Toast.makeText(context, "Message received: "+message,
                Toast.LENGTH_LONG).show();
    }
}