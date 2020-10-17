package com.example.write_a_letter_to_future;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.google.android.gms.auth.api.signin.GoogleSignInOptions.DEFAULT_SIGN_IN;

public class dashboard<mGoogleSignInClient> extends AppCompatActivity {
    TextView user_name,user_mail;
    ImageView user_profile;
    ImageButton user_logout;
    GoogleSignInClient GoogleSignInClient;
    RecyclerView mRecyclerView;
    MyAdapter myAdapter; //created my adapter class in the use of recycler view
    private Object mGoogleSignInClient;
    private static Object Activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //typecasting the views
        user_name = (TextView) findViewById(R.id.name);
        user_mail = (TextView) findViewById(R.id.email);
        user_profile = (ImageView) findViewById(R.id.profile);
        user_logout = (ImageButton) findViewById(R.id.logout_btn);

        //here starts the sign out process
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        GoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //To get the user information

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(dashboard.this);

        //if someone already logged in then get the details
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            Uri personPhoto = acct.getPhotoUrl();
            //Setting the above variables to our textview fields
            user_name.setText(personName);
            user_mail.setText(personEmail);
            Glide.with(this).load(new String().valueOf(personPhoto)).into(user_profile);//for getting the image..

        }

        //when clicked on the logout image or button change the activity to sign in activity
        user_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        //This is for the title for the collapsing toolbar i.e., setting the user name that we get from the google api
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Hello " + " " + acct.getDisplayName());

        //Recycler view and Card view
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);//typecasting the recyclerview
        //creating object for the linearlayoutmanager which allows us to set the orientation etc
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        //getting the list of items from the my adapter class
        myAdapter = new MyAdapter(this, getMyList());
        mRecyclerView.setAdapter(myAdapter);
    }
    //changing activity through signout()
    private void signOut() {
        Task<Void> successfully_signedout = GoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(dashboard.this, "Successfully signedout", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(dashboard.this, sign_in.class));
                        finish();
                    }
                });
    }
    //Recycler view and cardview.....setting the images and titles to the list we've created
    private ArrayList<Model> getMyList(){
        ArrayList<Model> models=new ArrayList<>();
        Model m=new Model();
        m.setMain_line("Write");
        m.setSub_line("Hurry up! Don't let your future forget your past!");
        m.setImage(R.drawable.card_pencil);
        models.add(m);

        m=new Model();
        m.setMain_line("Read");
        m.setSub_line("Take a sneak peak at other's letters !");
        m.setImage(R.drawable.card_book);
        models.add(m);

        m=new Model();
        m.setMain_line("Rate");
        m.setSub_line("Like our app? Rate it !");
        m.setImage(R.drawable.card_heart2);
        models.add(m);

        m=new Model();
        m.setMain_line("About us");
        m.setSub_line("Know about our project !");
        m.setImage(R.drawable.card_team);
        models.add(m);

        return models;
    }
}
