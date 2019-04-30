package com.example.musicae.View;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.musicae.MusicUtilsActivity;
import com.example.musicae.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import java.io.IOException;

public class CurrentSongActivity extends AppCompatActivity {

    private AppCompatSeekBar seekBar;
    private TextView song_current_duration;
    private TextView song_total_duration;
    private FloatingActionButton playBtn, pauseBtn;
    private ImageButton nextBtn, prevBtn;
    private ImageView stopBtn;
    private CircularImageView image;
    private MediaPlayer player;
    private Handler mHandler = new Handler();
    private String URI;
    private String Title;
    private MusicUtilsActivity utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_song);
        getIntentData();
        playSong();
        setComponents();
        setSeekBar();
        updateTimeAndSeekBar();
        buttonActions();
        rotateDisk();
    }

    public void getIntentData() {
        Intent intent = getIntent();
        URI = intent.getStringExtra("URI");
        Title = intent.getStringExtra("SONG_TITLE");
    }

    public void playSong() {
        player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            player.setDataSource(this, Uri.parse(URI));
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setComponents() {
        TextView songTitle = findViewById(R.id.songTitle);
        songTitle.setText(Title);
        song_total_duration = findViewById(R.id.totalDuration);
        song_current_duration = findViewById(R.id.currentDuration);
        image = findViewById(R.id.image);
        seekBar = findViewById(R.id.songSeekBar);
        playBtn = findViewById(R.id.playBtn);
        pauseBtn = findViewById(R.id.pauseBtn);
        stopBtn = findViewById(R.id.stopBtn);
        nextBtn = findViewById(R.id.nextBtn);
        prevBtn = findViewById(R.id.prevBtn);
    }

    public void setSeekBar() {
        utils = new MusicUtilsActivity();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mHandler.removeCallbacks(mUpdateTimeTask);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mHandler.removeCallbacks(mUpdateTimeTask);
                int totalDuration = player.getDuration();
                int currentPosition = utils.progressToTimer(seekBar.getProgress(), totalDuration);
                player.seekTo(currentPosition);
                mHandler.post(mUpdateTimeTask);
            }
        });
        updateTimeAndSeekBar();
    }

    public void buttonActions() {
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
                rotateDisk();
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

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Siguiente Cancion", Toast.LENGTH_SHORT).show();
            }
        });

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Siguiente Cancion", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Runnable mUpdateTimeTask = new Runnable() {
        @Override
        public void run() {
            if (player != null) {
                if (player.isPlaying()) {
                    updateTimeAndSeekBar();
                    mHandler.postDelayed(this, 100);
                }
            }
        }
    };

    private void updateTimeAndSeekBar() {
        if (player != null) {
            if (player.isPlaying()) {
                long totalDuration = player.getDuration();
                long currentDuration = player.getCurrentPosition();

                song_total_duration.setText((utils.milliSecodsToTime(totalDuration)));
                song_current_duration.setText(utils.milliSecodsToTime(currentDuration));

                int progress = (utils.getProgressSeekBar(currentDuration, totalDuration));
                seekBar.setProgress(progress);
            }
        }
    }

    private void rotateDisk() {
        if (player == null) {
            image.animate().cancel();
        } else if (player.isPlaying()){
            image.animate().setDuration(100).rotation(image.getRotation()+2f).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    rotateDisk();
                    super.onAnimationEnd(animation);
                }
            });
        }
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
        mHandler.post(mUpdateTimeTask);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mUpdateTimeTask);
        releasePlayer();
        finish();
    }
}
