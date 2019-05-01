package com.example.musicae.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.musicae.R;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;


public class LoginActivity extends AppCompatActivity {
//    implements GoogleApiClient.OnConnectionFailedListener

    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.signInBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        comeIn();
    }

    void comeIn(){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            startActivity(new Intent(this, NavigationBarWithTabsActivity.class));
            finish();
        }
    }

    void signIn(){
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.GoogleBuilder().build()))
                        .build(),
                RC_SIGN_IN);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                comeIn();
            }
        }
    }





//    private GoogleApiClient googleApiClient;
//
//    private SignInButton signInButton;
//
//    public static final int SIGN_IN_CODE = 777;
//
//    private FirebaseAuth firebaseAuth;
//    private FirebaseAuth.AuthStateListener firebaseAuthListener;
//
//    private ProgressBar progressBar;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//
//        googleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this, this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();
//
//        signInButton = (SignInButton) findViewById(R.id.signInBtn);
//
//        signInButton.setSize(SignInButton.SIZE_WIDE);
//
//        signInButton.setColorScheme(SignInButton.COLOR_DARK);
//
//        signInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
//                startActivityForResult(intent, SIGN_IN_CODE);
//            }
//        });
//
//        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    goMainScreen();
//                }
//            }
//        };
//
//
//        progressBar = findViewById(R.id.progressBar);
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        firebaseAuth.addAuthStateListener(firebaseAuthListener);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if (firebaseAuthListener != null) {
//            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
//        }
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == SIGN_IN_CODE) {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            handleSignInResult(result);
//        }
//    }
//
//    private void handleSignInResult(GoogleSignInResult result) {
//        if (result.isSuccess()) {
//            firebaseAuthWithGoogle(result.getSignInAccount());
//        } else {
//            Toast.makeText(this, R.string.not_log_in, Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void firebaseAuthWithGoogle(GoogleSignInAccount signInAccount) {
//
//        progressBar.setVisibility(View.VISIBLE);
//        signInButton.setVisibility(View.INVISIBLE);
//        AuthCredential credential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(), null);
//        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                progressBar.setVisibility(View.INVISIBLE);
//                signInButton.setVisibility(View.VISIBLE);
//                if (!task.isSuccessful()) {
//                    Toast.makeText(getApplicationContext(), "No se pudo autenticar", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    private void goMainScreen() {
//        Intent intent = new Intent(this, NavigationBarWithTabsActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//    }
}
