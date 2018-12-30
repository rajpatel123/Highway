package dev_video.dev_video;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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
