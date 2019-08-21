package com.highway.activity;

import android.animation.AnimatorSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.highway.R;

public class DriveryLicenceActivity extends AppCompatActivity {
    private ImageView imageView, cameraOpenButton, licenceimageViewFront, licenceimageViewBack;
    private ImageView imageView0, imageView1, imageView2, imageView3;
    private AnimatorSet animatorSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivery_licence);




    }
}
