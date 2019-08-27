package com.highway.activity;

import android.Manifest;
import android.animation.AnimatorSet;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.highway.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import modelclass.UploadAdharRequest;
import modelclass.UploadAdharResponse;
import retrofit.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.CameraUtils;
import utils.HighwayPreface;
import utils.Utils;

public class CustomerAdharcardId extends AppCompatActivity {

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;
    private static final int CAMERA_CAPTURE_FRONT_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_BACK_IMAGE_REQUEST_CODE = 101;
    public static final String KEY_IMAGE_STORAGE_PATH = "image_path";

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    public static final int BITMAP_SAMPLE_SIZE = 8;
    public static final String GALLERY_DIRECTORY_NAME = "Hello Camera";
    public static final String IMAGE_EXTENSION = "jpg";
    public static final String VIDEO_EXTENSION = "mp4";

    private static String imageStoragePath;

    private ImageView CameraGalleryOpen, adharImageViewFront, adharImageViewBack, backArrowOnAdhar;
    private AnimatorSet animatorSet;
    private Button submitAdharDetails;
    private EditText adhar_Number;
    private TextView AdhartxtDescription;

    private String base64AdharImageUploadOnserver;
    private Bitmap bitmap;
    private String userAdharId;
    private String adhar_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_adharcard_id);

        CameraGalleryOpen = findViewById(R.id.AdharEditOpen);
        adharImageViewFront = findViewById(R.id.FrontImageOfAdharCard);

        adhar_Number = findViewById(R.id.edt_Adhar_Number);
        backArrowOnAdhar = findViewById(R.id.Adhar_backArrow__Image);
        submitAdharDetails = findViewById(R.id.submit_Adhar_Button);

        // AdhartxtDescription = findViewById(R.id.txt_adhar_desc);
        //adharImageViewBack = findViewById(R.id.BackImageOfAdharCard);

        backArrowOnAdhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CustomerAdharcardId.this, RegistrationSignUpFormActivity.class);
                startActivity(i);
                finish();
            }
        });

        submitAdharDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 uploadAdharValidationSubmit();
            }
        });

        adharImageViewFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CameraUtils.checkPermissions(getApplicationContext())) {
                    captureImage();
                } else {
                    requestCameraPermission(MEDIA_TYPE_IMAGE);
                }
            }
        });

    }

    private void captureImage() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = CameraUtils.getOutputMediaFile(MEDIA_TYPE_IMAGE);
        if (file != null) {
            imageStoragePath = file.getAbsolutePath();
        }

        Uri fileUri = CameraUtils.getOutputMediaFileUri(getApplicationContext(), file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);   // start the image capture Intent
    }

    private void requestCameraPermission(final int type) {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {

                            if (type == MEDIA_TYPE_IMAGE) {
                                captureImage();
                            } else {
                                //captureVideo();
                            }

                        } else if (report.isAnyPermissionPermanentlyDenied()) {
                            showPermissionsAlert();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void showPermissionsAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissions required!")
                .setMessage("Camera needs few permissions to work properly. Grant them in settings.")
                .setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        CameraUtils.openSettings(CustomerAdharcardId.this);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                CameraUtils.refreshGallery(getApplicationContext(), imageStoragePath);

                //successfully captured the image & display it in image view
                previewCapturedImageFont();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == CAMERA_CAPTURE_VIDEO_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                CameraUtils.refreshGallery(getApplicationContext(), imageStoragePath);

                /* video successfully recorded && preview the recorded video */
                //  previewVideo();
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled recording
                Toast.makeText(getApplicationContext(),
                        "User cancelled video recording", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to record video
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to record video", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteFormat = stream.toByteArray();
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        return imgString;
    }

    private void previewCapturedImageFont() {
        try {
            //AdhartxtDescription.setVisibility(View.GONE);
            // licenceimageViewBack.setVisibility(View.GONE);

            adharImageViewFront.setVisibility(View.VISIBLE);

            Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);

            base64AdharImageUploadOnserver = getEncoded64ImageStringFromBitmap(bitmap);

            adharImageViewFront.setImageBitmap(bitmap);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saving stored image path to saved instance state
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_IMAGE_STORAGE_PATH, imageStoragePath);
    }

    /**
     * Restoring image path from saved instance state
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        imageStoragePath = savedInstanceState.getString(KEY_IMAGE_STORAGE_PATH);  // get the file url
    }

    public boolean inputAdharCardValidation() {
        adhar_no = adhar_Number.getText().toString();

        if (TextUtils.isEmpty(adhar_no) || adhar_no.length() == 16) {
            adhar_Number.setError("enter valid adhar no");
            return false;
        } else {
            adhar_Number.setError(null);
        }

        if (TextUtils.isEmpty(base64AdharImageUploadOnserver)) {
            Toast.makeText(this, "pls capture the adhar image", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void uploadAdharValidationSubmit(){

        if (inputAdharCardValidation()){

            UploadAdharRequest uploadAdharRequest = new UploadAdharRequest();
            uploadAdharRequest.setAdharNumber(adhar_no);
            uploadAdharRequest.setAdharImage(base64AdharImageUploadOnserver);
            userAdharId= HighwayPreface.getString(getApplicationContext(),"id");
            uploadAdharRequest.setUserId(userAdharId);

            Utils.showProgressDialog(getApplicationContext());

            RestClient.uploadAdharDetails(uploadAdharRequest, new Callback<UploadAdharResponse>() {
                @Override
                public void onResponse(Call<UploadAdharResponse> call, Response<UploadAdharResponse> response) {
                    if (response.body()!=null){
                        if (response.body().getStatus()==true){
                            Intent i = new Intent(CustomerAdharcardId.this,MainActivity.class);
                            startActivity(i);
                            Toast.makeText(CustomerAdharcardId.this, "Adhar details uploaded sucessfully ", Toast.LENGTH_SHORT).show();
                            finish();
                        }else if(response.body().getStatus()==false){
                            Toast.makeText(CustomerAdharcardId.this, "Detail uploading Failed", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        try {
                            String rowdata = response.errorBody().string();
                            JSONObject jsonObject = new JSONObject(rowdata);
                            
                            String message = jsonObject.optString("message");
                            Toast.makeText(CustomerAdharcardId.this,message , Toast.LENGTH_SHORT).show();
                            
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(CustomerAdharcardId.this, "", Toast.LENGTH_SHORT).show();
                    }
                    
                }

                @Override
                public void onFailure(Call<UploadAdharResponse> call, Throwable t) {

                }
            });
       
        }
    }

}
