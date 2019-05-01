package com.example.musicae.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.musicae.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountActivity extends AppCompatActivity {

    TextView tvUsername;
    TextView tvUseremail;
    ImageView ivUserAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        tvUsername = findViewById(R.id.userName);
        tvUseremail = findViewById(R.id.userEmail);

        ivUserAvatar = findViewById(R.id.userAvatar);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            tvUsername.setText(firebaseUser.getDisplayName());
            tvUseremail.setText(firebaseUser.getEmail());
            Glide.with(this)
                    .load(firebaseUser.getPhotoUrl().toString())
                    .into(ivUserAvatar);


            findViewById(R.id.sign_out).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AuthUI.getInstance()
                            .signOut(AccountActivity.this)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                public void onComplete(@NonNull Task<Void> task) {
                                    startActivity(new Intent(AccountActivity.this, LoginActivity.class));
                                    finish();
                                }
                            });
                }
            });
        }
    }
}