package com.highwayjprproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.highwayjprproject.R;
import com.highwayjprproject.utils.Constants;
import com.highwayjprproject.utils.HighwayPrefs;

public class SplashActivity extends AppCompatActivity {

    private Handler handler;
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashScreenHandler();

    }
    public void splashScreenHandler(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (HighwayPrefs.getBoolean(SplashActivity.this, Constants.LOGGED_IN)) {
                    Intent i = new Intent(SplashActivity.this, DashBoardActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Intent i = new Intent(SplashActivity.this, LoginOptionActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        },SPLASH_TIME_OUT);
    }
}
