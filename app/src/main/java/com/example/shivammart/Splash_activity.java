package com.example.shivammart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash );

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                move();
            }

        }, 3000);
    }

    void move() {
        Intent intent = new Intent(Splash_activity.this,Firstpart.class);
        startActivity(intent);
        finish();

    }
}