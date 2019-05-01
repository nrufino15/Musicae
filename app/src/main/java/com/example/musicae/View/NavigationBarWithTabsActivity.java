package com.example.musicae.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musicae.R;
import com.example.musicae.TapPagerAdapter;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NavigationBarWithTabsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public ImageView userImage;
    public TextView userName;
    public TextView userEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bar_with_tabs);

        setUp();
    }

    public void setUp() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);

        TapPagerAdapter tapPagerAdapter = new TapPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tapPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        setUser();
    }

    public void setUser(){
        userImage = findViewById(R.id.userimage);
        userName =  findViewById(R.id.username);
        userEmail =  findViewById(R.id.useremail);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            Log.d("MiAPP", firebaseUser.getDisplayName());
            Log.d("MiAPP", firebaseUser.getEmail());
            Log.d("MiAPP", firebaseUser.getPhotoUrl().toString());
//            userName.setText(firebaseUser.getDisplayName());
//            userEmail.setText(firebaseUser.getEmail());
//            Glide.with(this)
//                    .load(firebaseUser.getPhotoUrl().toString())
//                    .into(userImage);


            findViewById(R.id.logoutBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AuthUI.getInstance()
                            .signOut(NavigationBarWithTabsActivity.this)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                public void onComplete(@NonNull Task<Void> task) {
                                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                    finish();
                                }
                            });
                }
            });
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            goToReportSection();
        }
        if (id == R.id.action_help) {
            goToHelpSection();
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToHelpSection(){
        Intent intent = new Intent(getApplicationContext(), SlideActivity.class);
        startActivity(intent);
    }

    public void goToReportSection(){
        Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
        startActivity(intent);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_music) {
            Intent intent = new Intent(getApplicationContext(), NavigationBarWithTabsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_account) {
            Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
