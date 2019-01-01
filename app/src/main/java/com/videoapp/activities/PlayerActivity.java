package com.videoapp.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.videoapp.R;

import java.io.File;
import java.util.ArrayList;


public class PlayerActivity extends AppCompatActivity {
    static MediaPlayer mp;
    ArrayList<File> mysong;
    Uri u;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Intent i=getIntent();
        Bundle b=i.getExtras();
        mysong=(ArrayList)b.getParcelableArrayList("songlist");
        position=b.getInt("pos",0);
        u=Uri.parse(mysong.get(position).toString());
        mp=MediaPlayer.create(getApplicationContext(),u);
        mp.start();
    }
}
