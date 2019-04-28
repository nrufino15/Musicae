package com.example.musicae;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;

import java.io.IOException;
import java.util.IllegalFormatCodePointException;

public class MediaPlayerActivity {

    public static final int MAX_PROGRESS=10000;

    public String milliSecodsToTime(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        int hours = (int) (milliseconds/(1000 * 60 * 60));
        int minutes = (int) (milliseconds%(1000 * 60 * 60))/(1000*60);
        int seconds = (int) ((milliseconds%(1000 * 60 * 60))%(1000*60)/1000);

        if (hours > 0){
            finalTimerString=hours+":";
        }
        if (seconds < 10){
            secondsString="0"+seconds;
        } else {
            secondsString=""+seconds;
        }
        finalTimerString=finalTimerString+minutes+":"+secondsString;

        return finalTimerString;
    }

    public int getProgressSeekBar(long currentDuration, long totalDuration) {
        Double progress = (double) 0;
        progress=(((double)currentDuration)/totalDuration)*MAX_PROGRESS;

        return progress.intValue();
    }

    public int progressToTimer(int progress, int totalDuration) {
        int currentDuration = 0;
        totalDuration = (int) (totalDuration/1000);
        currentDuration = (int) ((double) progress/MAX_PROGRESS)*totalDuration;

        return currentDuration*1000;
    }
}
