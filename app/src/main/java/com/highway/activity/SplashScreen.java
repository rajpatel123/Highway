package com.highway.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.highway.R;

import utils.Constants;
import utils.HighwayPreface;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(splashRunnable,4000);

    }



    Runnable splashRunnable = new Runnable() {
        @Override
        public void run() {

            if (HighwayPreface.getBoolean(SplashScreen.this, Constants.LOGGED_IN)){
                Intent i = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(i);
            }else{
                Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(i);
            }


            finish();
        }
    };
}
