package com.videoapp.activities;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.VideoView;

import com.klinker.android.simple_videoview.SimpleVideoView;
import com.videoapp.R;


public class Main2Activity extends AppCompatActivity {//implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener {


    private static  String SAMPLE_VIDEO ="https://sample-videos.com/video123/mp4/720/big_buck_bunny_720p_1mb.mp4";

    private VideoView videoView;
    /*private SurfaceView surfaceView;
    private String SAMPLE_NAME = "demo.mp4";
    private SurfaceHolder surfaceHolder;
    private MediaPlayer mediaPlayer;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        videoView = (VideoView) findViewById(R.id.video_view);

//        if(getIntent().hasExtra("url")){
//            SAMPLE_VIDEO=getIntent().getStringExtra("url");
//        }
        /*surfaceView = findViewById(R.id.selected_video);
        mediaPlayer = new MediaPlayer();

        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);

        surfaceView.setKeepScreenOn(true);*/

        Uri uri = Uri.parse(SAMPLE_VIDEO);
        videoView.setVideoURI(uri);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });
        videoView.start();

    }

    /*@Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mediaPlayer.setDisplay(surfaceHolder);
        try {
            mediaPlayer.setDataSource(SAMPLE_VIDEO);
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {

    }*/
}