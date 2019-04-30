package com.example.musicae;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.musicae.View.NavigationBarWithTabsActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent;
        intent = new Intent(MainActivity.this, NavigationBarWithTabsActivity.class);
        startActivity(intent);
        finish();
    }

}
