package com.example.mealsapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class TimerService extends Service {
    private Timer timer;
    @Override
    // execution of service will start
    // on calling this method
    public int onStartCommand(Intent intent, int flags, int startId) {

        // creating a media player which
        // will play the audio of Default
        // ringtone in android device
        //player=MediaPlayer.create(this,Settings.System.DEFAULT_RINGTONE_URI);
        timer = new Timer();
        double time = intent.getDoubleExtra(TIME_EXTRA, 0.0);
        timer.scheduleAtFixedRate(new TimeTask(time), 0, 1000);


        // providing the boolean
        // value as true to play
        // the audio on loop
        //player.setLooping( true );

        // starting the process
        //player.start();

        // returns the status
        // of the program
        return START_NOT_STICKY;
    }

    private class TimeTask extends TimerTask {
        private double time;

        public TimeTask(double time){
            this.time=time;
        }

        @Override
                public void run(){
            Intent intent = new Intent(TIMER_UPDATED);
            time++;
            intent.putExtra(TIME_EXTRA,time);
            sendBroadcast(intent);
        }
    }

    @Override

    // execution of the service will
    // stop on calling this method
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();

        // stopping the process
        //player.stop();
    }

    public static final String TIMER_UPDATED = "timerUpdated";
    public static final String TIME_EXTRA = "timeExtra";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
