package com.example.broadcastintentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    BroadcastReceiver receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureReceiver();
    }

    private void configureReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.broadcastintentdemo");
        filter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        filter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        receiver = new MyReceiver();
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    public void broadcastIntent(View view) {
        String url = "https://www.youtube.com";
        Intent intent = new Intent();
        intent.putExtra("someKey", url);
        intent.setAction("com.example.broadcastintentdemo");
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
    }
}