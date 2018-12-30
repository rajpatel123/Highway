package dev_video.dev_video;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.rtp.AudioStream;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.SurfaceHolder;

import android.view.SurfaceView;
import android.view.View;

import com.allattentionhere.autoplayvideos.AAH_CustomRecyclerView;
import com.klinker.android.simple_videoview.SimpleVideoView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {//implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener {


    private static final String SAMPLE_VIDEO =
            "https://video.twimg.com/ext_tw_video/703677246528221184/pu/vid/180x320/xnI48eAV8iPFW9aA.mp4";

    private SimpleVideoView videoView;
    /*private SurfaceView surfaceView;
    private String SAMPLE_NAME = "demo.mp4";
    private SurfaceHolder surfaceHolder;
    private MediaPlayer mediaPlayer;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /*surfaceView = findViewById(R.id.selected_video);
        mediaPlayer = new MediaPlayer();

        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);

        surfaceView.setKeepScreenOn(true);*/

        videoView = (SimpleVideoView) findViewById(R.id.video_view);
        videoView.setErrorTracker(new SimpleVideoView.VideoPlaybackErrorTracker() {
            @Override
            public void onPlaybackError(Exception e) {
                e.printStackTrace();
                Snackbar.make(videoView, "Uh oh, error playing!", Snackbar.LENGTH_INDEFINITE).show();
            }
        });
        videoView.start(SAMPLE_VIDEO);
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (videoView.isPlaying())
                    videoView.pause();
                else
                    videoView.play();
            }
        });
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

    @Override
    protected void onStop() {
        super.onStop();
        videoView.release();
    }
}