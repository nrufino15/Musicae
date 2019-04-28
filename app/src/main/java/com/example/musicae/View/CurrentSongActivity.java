package com.example.musicae.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.musicae.R;

public class CurrentSongActivity extends AppCompatActivity {

    private AppCompatSeekBar seekBar;
    private TextView song_current_duration, song_total_duration;
    private View parent_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_song);
    }
}
