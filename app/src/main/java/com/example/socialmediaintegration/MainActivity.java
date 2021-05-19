package com.example.socialmediaintegration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=2000;
    //ImageView imgview;
    //TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // imgview=findViewById(R.id.imageView);
       // textview=findViewById(R.id.textView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent loginIntent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
