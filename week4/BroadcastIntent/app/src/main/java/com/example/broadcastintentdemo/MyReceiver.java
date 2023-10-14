package com.example.broadcastintentdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");

        String action = intent.getAction();

        String message = intent.getStringExtra("someKey")
                +" "+intent.getAction();


        Toast.makeText(context, "Message received: "+message,
                Toast.LENGTH_LONG).show();

        String url = intent.getStringExtra("someKey");
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        context.startActivity(i);

    }
}