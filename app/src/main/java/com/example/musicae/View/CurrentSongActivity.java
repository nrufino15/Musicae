package com.example.musicae.View;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.musicae.MusicUtilsActivity;
import com.example.musicae.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.IOException;

public class CurrentSongActivity extends AppCompatActivity {

    private View parent_view;
    private AppCompatSeekBar seekBar;
    private TextView song_current_duration, song_total_duration;
    private FloatingActionButton playBtn;
    private ImageButton nextBtn, prevBtn;
    private CircularImageView image;
    private MediaPlayer mp;
    private Handler mHandler;
    private Runnable runnable;
    private String URI;
    private String Title;
    private String Duration;

    private MusicUtilsActivity utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_song);
        getIntentData();
    }

    public void getIntentData() {
        Intent intent = getIntent();
        URI = intent.getStringExtra("URI");
        Title = intent.getStringExtra("SONG_TITLE");
        Duration = intent.getStringExtra("SONG_DURATION");
    }

    public void prepareSong() {
        mp = new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mp.setDataSource(this, Uri.parse(URI));
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void playSong() {

    }


//    private void rotateDisk() {
//        if (!mp.isPlaying()) return;
//        image.animate().setDuration(100).rotation(image.getRotation()+2f).setListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                rotateDisk();
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
//    }
}
