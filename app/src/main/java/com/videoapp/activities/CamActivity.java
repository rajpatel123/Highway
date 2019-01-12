package com.videoapp.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.videoapp.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class CamActivity extends AppCompatActivity implements View.OnClickListener {
    public Camera mCamera;
    public static final String TAG = "CamActivity";
    private boolean isFilterOpen = false;
    private Button filterButton;
    private final int VIDEO_REQUEST_CODE = 100;
    private MediaRecorder mMediaRecorder;
    private CameraPreview mPreview;
    private Button musicselect;
    private boolean isRecording = false;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private RadioGroup recordSelectionRadioGroup;
    private RadioButton selectedRadioButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam);
        musicselect = findViewById(R.id.select_music);
        musicselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CamActivity.this, Music_player.class);
                startActivity(i);
            }
        });
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);


        // Create an instance of Camera
        mCamera = getCameraInstance();
        mMediaRecorder = new MediaRecorder();


        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        if (preview != null) {
            preview.addView(mPreview);
        }
        // Add a listener to the Capture button
        ImageButton captureButton = findViewById(R.id.button_capture);
        if (captureButton != null) {
            captureButton.setOnClickListener(this);
        }
        // Add listener to filter button
        filterButton = (Button) findViewById(R.id.filterButton);
        if (filterButton != null) {
            filterButton.setOnClickListener(this);
        }
    }

    /**
     * A safe way to get an instance of the Camera object.
     */
    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        } catch (Exception e) {
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    // Method for set the camera filters
    public void colorEffectFilter(View v) {
        try {
            Camera.Parameters parameters = mCamera.getParameters();
            switch (v.getId()) {
                case R.id.effectNone:
                    parameters.setColorEffect(Camera.Parameters.EFFECT_NONE);
                    mCamera.setParameters(parameters);
                    break;
                case R.id.effectAqua:
                    parameters.setColorEffect(Camera.Parameters.EFFECT_AQUA);
                    mCamera.setParameters(parameters);
                    break;
                case R.id.effectBlackboard:
                    parameters.setColorEffect(Camera.Parameters.EFFECT_BLACKBOARD);
                    mCamera.setParameters(parameters);
                    break;
                case R.id.effectMono:
                    parameters.setColorEffect(Camera.Parameters.EFFECT_MONO);
                    mCamera.setParameters(parameters);
                    break;
                case R.id.effectNegative:
                    parameters.setColorEffect(Camera.Parameters.EFFECT_NEGATIVE);
                    mCamera.setParameters(parameters);
                    break;
                case R.id.effectPosterize:
                    parameters.setColorEffect(Camera.Parameters.EFFECT_POSTERIZE);
                    mCamera.setParameters(parameters);
                    break;
                case R.id.effectSepia:
                    parameters.setColorEffect(Camera.Parameters.EFFECT_SEPIA);
                    mCamera.setParameters(parameters);
                    break;
                case R.id.effectSolarize:
                    parameters.setColorEffect(Camera.Parameters.EFFECT_SOLARIZE);
                    mCamera.setParameters(parameters);
                    break;
                case R.id.effectWhiteboard:
                    parameters.setColorEffect(Camera.Parameters.EFFECT_WHITEBOARD);
                    mCamera.setParameters(parameters);
                    break;
            }
        } catch (Exception ex) {
            Log.d(TAG, ex.getMessage());
        }
    }


    /**
     * File for saving an image
     */
    private static Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * Create a File for saving an image or video
     */
    private static File getOutputMediaFile(int type) {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.filterButton:
                if (!isFilterOpen) {
                    findViewById(R.id.record_filter_layout).setVisibility(View.VISIBLE);
                    //  filterButton.setImageResource(R.drawable.filter);
                    isFilterOpen = true;//
                } else {
                    findViewById(R.id.record_filter_layout).setVisibility(View.GONE);
                    // filterButton.setImageResource(R.drawable.filter);
                    isFilterOpen = false;//
                }
                break;
            case R.id.button_capture:
                Toast.makeText(this, "Start recording", Toast.LENGTH_SHORT).show();

//                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
//
//                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},
//                            0);
//
//                } else {
                    prepareVideoRecorder();
                //}
            /*final Dialog dialog = new Dialog(CamActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.record_video_time);
            //dialog.setTitle("Your Title goes here");

            Button dialogButton = (Button) dialog.findViewById(R.id.button_ok);

            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recordSelectionRadioGroup = dialog.findViewById(R.id.record_video_radio_group);
                    int selectedId = recordSelectionRadioGroup.getCheckedRadioButtonId();

                    selectedRadioButton = dialog.findViewById(selectedId);
                    Toast.makeText(CamActivity.this,selectedRadioButton.getText().toString(),Toast.LENGTH_SHORT).show();

                    int time = 0;

                    if(selectedRadioButton.getText().toString().equals("30 Seconds"))
                        time = 30;
                    else if(selectedRadioButton.getText().toString().equals("60 Seconds"))
                        time = 60;
                    else if(selectedRadioButton.getText().toString().equals("90 Seconds"))
                        time = 90;
                    if (isRecording) {
                        mMediaRecorder.stop();
                        releaseMediaRecorder();
                        mCamera.lock();

                        Toast.makeText(getApplicationContext(),"Capture",Toast.LENGTH_SHORT).show();
                        isRecording = true;
                    } else {
                        if (prepareVideoRecorder()) {
                            mMediaRecorder.start();

                            Toast.makeText(getApplicationContext(),"Capture",Toast.LENGTH_SHORT).show();
                            isRecording = true;
                        } else {
                            releaseMediaRecorder();

                        }
                    }


                    dialog.dismiss();
                }
            });

            dialog.show();
            Window window = dialog.getWindow();
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);*/

                //mCamera.takePicture(null,null,mMediaRecorder);


        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaRecorder();       // if you are using MediaRecorder, release it first
        releaseCamera();              // release the camera immediately on pause event
    }

    private void releaseMediaRecorder() {
        if (mMediaRecorder != null) {
            mMediaRecorder.reset();   // clear recorder configuration
            mMediaRecorder.release(); // release the recorder object
            mMediaRecorder = null;
            mCamera.lock();           // lock camera for later use
        }
    }

    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.release();        // release the camera for other applications
            mCamera = null;
        }
    }

    private boolean prepareVideoRecorder() {

        mCamera = getCameraInstance();
        mMediaRecorder = new MediaRecorder();

        // Step 1: Unlock and set camera to MediaRecorder

        mCamera.unlock();
        mMediaRecorder.setCamera(mCamera);

        // Step 2: Set sources

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},
                    1);

        } else {

            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        }
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        // Step 3: Set a CamcorderProfile (requires API Level 8 or higher)
        mMediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));

        // Step 4: Set output file
        mMediaRecorder.setOutputFile(getOutputMediaFile(MEDIA_TYPE_VIDEO).toString());

        // Step 5: Set the preview output
        mMediaRecorder.setPreviewDisplay(mPreview.getHolder().getSurface());

        // Step 6: Prepare configured MediaRecorder
        try {
            mMediaRecorder.prepare();
        } catch (IllegalStateException e) {
            Log.d(TAG, "IllegalStateException preparing MediaRecorder: " + e.getMessage());
            releaseMediaRecorder();
            return false;
        } catch (IOException e) {
            Log.d(TAG, "IOException preparing MediaRecorder: " + e.getMessage());
            releaseMediaRecorder();
            return false;
        }
        return true;
    }

 /*mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);*/



}
