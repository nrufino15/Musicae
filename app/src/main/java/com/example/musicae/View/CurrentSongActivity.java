package com.example.musicae.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicae.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.IOException;

public class CurrentSongActivity extends AppCompatActivity {

//    private View parent_view;
//    private AppCompatSeekBar seekBar;
    private TextView song_current_duration, song_total_duration, songTitle, songLength;
    private FloatingActionButton playBtn, pauseBtn;
    private ImageButton nextBtn, prevBtn;
    private ImageView stopBtn;
//    private CircularImageView image;
    private MediaPlayer player;
//    private Handler mHandler;
//    private Runnable runnable;
    private String URI;
    private String Title;
    private String Duration;

//    private MusicUtilsActivity utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_song);
        getIntentData();
        prepareSong();
    }

    public void getIntentData() {
        Intent intent = getIntent();
        URI = intent.getStringExtra("URI");
        Title = intent.getStringExtra("SONG_TITLE");
        Duration = intent.getStringExtra("SONG_DURATION");
    }

    public void prepareSong() {
        player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            player.setDataSource(this, Uri.parse(URI));
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        songTitle = findViewById(R.id.songTitle);
        songTitle.setText(Title);

        playBtn = findViewById(R.id.playBtn);
        pauseBtn = findViewById(R.id.pauseBtn);
        songLength = findViewById(R.id.totalDuration);
        songLength.setText(Duration);
        stopBtn = findViewById(R.id.stopBtn);
        nextBtn = findViewById(R.id.nextBtn);
        prevBtn = findViewById(R.id.prevBtn);


        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                pauseSong(v);
                pauseBtn.setVisibility(View.INVISIBLE);
                playBtn.setVisibility(View.VISIBLE);
            }
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                playSong(v);
                playBtn.setVisibility(View.INVISIBLE);
                pauseBtn.setVisibility(View.VISIBLE);
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                stopSong(v);
                playBtn.setVisibility(View.VISIBLE);
                pauseBtn.setVisibility(View.INVISIBLE);
            }
        });


    }


    public void playSong(View v) {
        if (player == null) {
            player = MediaPlayer.create(this, Uri.parse(URI));
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    releasePlayer();
                }
            });
        }
        player.start();
    }

    public void pauseSong(View v) {
        if (player != null) {
            player.pause();
        }
    }

    public void stopSong(View v) {
        releasePlayer();
    }

    public void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
            Toast.makeText(getApplicationContext(), "MediaPlayer released", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        releasePlayer();
    }

    //    private void rotateDisk() {
//        if (!player.isPlaying()) return;
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
