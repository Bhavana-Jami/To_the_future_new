package com.example.write_a_letter_to_future;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.accounts.AccountManagerFuture;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class sign_in extends AppCompatActivity {
    private static final int RC_SIGN_IN = 0;//variable declared for the google api process and note that all the process is just copying  from firebase website @ google sign in
    SignInButton signin;//button type is SignInButton special button provided by g api
    private GoogleSignInClient mGoogleSignInClient; //variable created for the client side
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //For full scree,splash screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_in);
        //Animations ----U are currently not using any animation considering the user experience....too many animations wont work and also makes the user wait
       /* topAnime = AnimationUtils.loadAnimation(this, R.anim.top_anime);
        bottomAnime = AnimationUtils.loadAnimation(this, R.anim.bottom_anime);
        line = findViewById(R.id.app_name);
        tag = findViewById(R.id.tag_name);
        mailbox = findViewById(R.id.lottieAnimationView);*/

        //****Here starts our Google sign in button code ,you just followed the firebase docs to do this
        signin = (SignInButton) findViewById(R.id.signin_btn);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //when button clicked ...no.of mail acs logged in your mobile appears
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        signIn();
            }
        });

    }
    //the sign in method calls the google activity i.e., the activity that shows up when we click on sign in #all logged mail acs
    private void signIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    //when selected the mail ac then let the user login into the app
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    //after logging in...move to the dashboard screen..we r gonna write an intent for that
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            Intent intent=new Intent(sign_in.this,dashboard.class);
            startActivity(intent);
        } catch (ApiException e) {//show error when failed for any reason
            Toast.makeText(this, "Failed to sign in", Toast.LENGTH_SHORT).show();
        }
    }

    @Override//This is for--if the user is logged into the app then direct  him to dashboard instead  of making sign in againa
    protected void onStart() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null){
            startActivity(new Intent(sign_in.this,dashboard.class));
            finish();
        }
        super.onStart();
    }
}
