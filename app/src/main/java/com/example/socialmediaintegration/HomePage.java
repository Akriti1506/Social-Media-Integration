package com.example.socialmediaintegration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePage extends AppCompatActivity {

    ImageView imageView;
    TextView name,email;
    Button signout;

    FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mAuth=FirebaseAuth.getInstance();
        final FirebaseUser mUser=mAuth.getCurrentUser();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        imageView=findViewById(R.id.imageView3);
        name=findViewById(R.id.textView3);
        email=findViewById(R.id.textView4);
       // phno=findViewById(R.id.textView5);
        signout=(Button)findViewById(R.id.button);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fb logout code below
                mAuth.signOut();
                LoginManager.getInstance().logOut();
                finish();
                //fb code above
                switch (v.getId()) {

                    case R.id.button:
                        signOut();
                        break;

                }

            }
        });

        if(mUser !=null)
        {
           String namef= mUser.getDisplayName();
            String emailf=mUser.getEmail();
           String photof= mUser.getPhotoUrl().toString();

            name.setText("NAME: "+namef);
            email.setText("EMAIL ID: "+emailf);
            Glide.with(this).load(photof).into(imageView);

        }

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
           // String personGivenName = acct.getGivenName();
          //  String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            name.setText("NAME: "+personName);
            email.setText("EMAIL ID: "+personEmail);
            Glide.with(this).load(String.valueOf(personPhoto)).into(imageView);
        }
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(HomePage.this,"Signed out successfully",Toast.LENGTH_LONG).show();
                        finish();


                    }
                });
    }
}
