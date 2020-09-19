package com.example.write_a_letter_to_future;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 2;
    private static final String TAG = "Error";
    private static int SPLASH_SCREEN=5000;//5000 here decides for howlong the splash screen need to stand
    Animation topAnime;
    Animation bottomAnime;
    TextView line,tag;
    LottieAnimationView mailbox;
    private Object mGoogleSignInClient;
    SignInButton sign_in;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //For full scree,splash screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //Animations
        topAnime=AnimationUtils.loadAnimation(this,R.anim.top_anime);
        bottomAnime=AnimationUtils.loadAnimation(this,R.anim.bottom_anime);
        line=findViewById(R.id.app_name);
        tag=findViewById(R.id.tag_name);
        mailbox=findViewById(R.id.lottieAnimationView);
        sign_in= (SignInButton) findViewById(R.id.signin_btn);
        //SETTING the animations to the views
        line.setAnimation(bottomAnime);
        tag.setAnimation(bottomAnime);
        mAuth=FirebaseAuth.getInstance();
        //for moving to the dashboard screent after loading the splash screen
       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,dashboard.class);
                startActivity(intent);
                finish();//must remember line of the code,coz it allows u to close the app directly without moving to the splash screen again
            }
        },SPLASH_SCREEN);*/


    }
}