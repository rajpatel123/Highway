package com.videoapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.videoapp.R;


public class user extends AppCompatActivity {
    TextView welcomeuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Intent intent=getIntent();
        String user=intent.getStringExtra("username");
        welcomeuser=findViewById(R.id.welcomeuser);
        welcomeuser.setText("welcome"+user);

    }
}
