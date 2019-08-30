package com.highway.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.highway.R;

import utils.Constants;
import utils.HighwayPreface;

public class ProfileActivity extends AppCompatActivity {
    private ImageView imgBack;
    private TextView logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imgBack = findViewById(R.id.ImgBack);
        logOut = findViewById(R.id.userLogOut);

        logOutOperation();




        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }


    public void logOutOperation(){
        logOut.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                HighwayPreface.putBoolean(ProfileActivity.this, Constants.LoginCheck, false);
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                finish();
                return false;
            }
        });




    }
}
