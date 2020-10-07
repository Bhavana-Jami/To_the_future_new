package com.example.write_a_letter_to_future;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.google.android.gms.auth.api.signin.GoogleSignInOptions.DEFAULT_SIGN_IN;

public class dashboard extends AppCompatActivity {
    TextView user_name,user_mail;
    ImageView user_profile;
    Button user_logout;
    GoogleSignInClient GoogleSignInClient;
    RecyclerView mRecyclerView;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        user_name = (TextView) findViewById(R.id.name);
        user_mail = (TextView) findViewById(R.id.email);
        user_profile = (ImageView) findViewById(R.id.profile);
        user_logout = (Button) findViewById(R.id.logout_btn);
        //for signout
       /* user_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    // ...
                    case R.id.user_logout:
                        signOut();
                        break;
                    // ...
                }
            }
        });*/
        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        GoogleSignInClient=GoogleSignIn.getClient(this,gso);

        //***To get the user information

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(dashboard.this);

        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            Uri personPhoto = acct.getPhotoUrl();
            //Setting the above variables to our textview fields
            user_name.setText(personName);
            user_mail.setText(personEmail);
            Glide.with(this).load(new String().valueOf(personPhoto)).into(user_profile);//for getting the image..
        }
        user_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        //Recycler view and Card view
        mRecyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        myAdapter=new MyAdapter(this,getMyList());
        mRecyclerView.setAdapter(myAdapter);

    }
    private void signOut(){
        GoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(dashboard.this, "Successfully signedout", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(dashboard.this,MainActivity.class));
                        finish();
                    }
                });
    }
    //Recycler view and cardview
    private ArrayList<Model> getMyList(){
        ArrayList<Model> models=new ArrayList<>();
        Model m=new Model();
        m.setMain_line("Write your letter now!");
        m.setSub_line("We've 0 written for now!");
        m.setImage(R.drawable.card_pencil);
        models.add(m);

        m=new Model();
        m.setMain_line("Write your letter now!");
        m.setSub_line("We've 0 written for now!");
        m.setImage(R.drawable.card_pencil);
        models.add(m);

        m=new Model();
        m.setMain_line("Write your letter now!");
        m.setSub_line("We've 0 written for now!");
        m.setImage(R.drawable.card_pencil);
        models.add(m);

        m=new Model();
        m.setMain_line("Write your letter now!");
        m.setSub_line("We've 0 written for now!");
        m.setImage(R.drawable.card_pencil);
        models.add(m);

        return models;
    }
}
