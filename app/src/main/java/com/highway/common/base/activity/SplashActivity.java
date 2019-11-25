package com.highway.common.base.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.highway.R;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;


public class SplashActivity extends AppCompatActivity {

    private Handler handler;
    private static int SPLASH_TIME_OUT = 3000;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(SplashActivity.this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                Log.e("newToken", newToken);


            }
        });



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
