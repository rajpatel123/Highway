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

import modelclass.UploadDLRequest;
import modelclass.UploadDLResponse;
import retrofit.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.CameraUtils;
import utils.HighwayPreface;
import utils.Utils;

public class DriveryLicenceActivity extends AppCompatActivity {

    // Activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;
    private static final int CAMERA_CAPTURE_FRONT_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_BACK_IMAGE_REQUEST_CODE = 101;


    // key to store image path in savedInstance state
    public static final String KEY_IMAGE_STORAGE_PATH = "image_path";

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    // Bitmap sampling size
    public static final int BITMAP_SAMPLE_SIZE = 8;

    // Gallery directory name to store the images or videos
    public static final String GALLERY_DIRECTORY_NAME = "Hello Camera";

    // Image and Video file extensions
    public static final String IMAGE_EXTENSION = "jpg";
    public static final String VIDEO_EXTENSION = "mp4";

    private static String imageStoragePath;

    private ImageView dLOpenGlaryCamera, licenceimageViewFront, licenceimageViewBack, backArrowOnDl;
    private AnimatorSet animatorSet;
    private Button submitDldetails;
    private EditText dlNumber, dlExpDate;
    private TextView txtDescription;

    private String base64imageserverDL;
    private Bitmap bitmap;
    private String userIdNew;
    private  String  dL_Number,dL_Expire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivery_licence);

        licenceimageViewFront = findViewById(R.id.drivingLicencefront);
        dLOpenGlaryCamera = findViewById(R.id.DLEditOption);
        dlExpDate = findViewById(R.id.edt_DlExpireDate);
        dlNumber = findViewById(R.id.edt_DlNumber);
        backArrowOnDl = findViewById(R.id.backArrow_Dl_Image);
        submitDldetails = findViewById(R.id.submitDL_Button);

        // licenceimageViewBack = findViewById(R.id.drivingLicenceback);
        //  txtDescription = findViewById(R.id.txt_desc);

        backArrowDlOperation();       // backArrow On Dl Page


        submitDldetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadDLValidationSubmit();
            }
        });

   /*     licenceimageViewFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                licenceimageViewFront.setVisibility(View.VISIBLE);
                licenceimageViewBack.setVisibility(View.GONE);
                txtDescription.setVisibility(View.GONE);

            }
        });

        licenceimageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                licenceimageViewFront.setVisibility(View.GONE);
                licenceimageViewBack.setVisibility(View.GONE);
                txtDescription.setVisibility(View.VISIBLE);


            }
        });*/




        licenceimageViewFront.setOnClickListener(new View.OnClickListener() {

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

    /*
     * Requesting permissions using Dexter library
     */
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
    /**
     * Activity result method will be called after closing the camera
     */
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
            // txtDescription.setVisibility(View.GONE);
            // licenceimageViewBack.setVisibility(View.GONE);

            licenceimageViewFront.setVisibility(View.VISIBLE);

            Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);

            base64imageserverDL = getEncoded64ImageStringFromBitmap(bitmap);

            licenceimageViewFront.setImageBitmap(bitmap);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    private void showPermissionsAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissions required!")
                .setMessage("Camera needs few permissions to work properly. Grant them in settings.")
                .setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        CameraUtils.openSettings(DriveryLicenceActivity.this);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }


    public void backArrowDlOperation() {
        backArrowOnDl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DriveryLicenceActivity.this, RegistrationSignUpFormActivity.class);
                startActivity(i);
            }
        });
    }


    public boolean input_DL_Validation() {

        dL_Number = dlNumber.getText().toString();
        dL_Expire = dlExpDate.getText().toString();

        //boolean check = true;

        if (TextUtils.isEmpty(dL_Number) || dL_Number.length()==16) {
            dlNumber.setError("Enter a valid DL number ");
            return false;
        } else {
            dlNumber.setError(null);
        }

        if (dL_Expire.isEmpty() ) {
            dlExpDate.setError("enter valid expire date in yy-mm-dd");
            return false;
        } else {
            dlExpDate.setError(null);
        }

        if(TextUtils.isEmpty(base64imageserverDL)){
            Toast.makeText(this, "Pls capture the dl image", Toast.LENGTH_SHORT).show();
            return false;
        }

      return true;
    }

    public void uploadDLValidationSubmit(){

        if (input_DL_Validation()){

                UploadDLRequest uploadDLRequest = new UploadDLRequest();
                uploadDLRequest.setLicenseNumber(dL_Number);
                uploadDLRequest.setExpiryDate(dL_Expire);
                uploadDLRequest.setLicenseImage(base64imageserverDL);

                userIdNew = HighwayPreface.getString(getApplicationContext(),"id");
                uploadDLRequest.setUserId(userIdNew);

                Utils.showProgressDialog(this);

                RestClient.uploadDL(uploadDLRequest, new Callback<UploadDLResponse>() {
                    @Override
                    public void onResponse(Call<UploadDLResponse> call, Response<UploadDLResponse> response) {
                        Utils.dismissProgressDialog();
                        if (response.body()!=null) {
                            if (response.body().getStatus() == true) {
                                Intent i = new Intent(DriveryLicenceActivity.this, VehicleRcDetails.class);
                                startActivity(i);
                                Toast.makeText(DriveryLicenceActivity.this, "DL Details upload success", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(DriveryLicenceActivity.this, "Details Uploading Failed !", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            try{
                                String rowdata = response.errorBody().string();
                                JSONObject  jsonObject = new JSONObject(rowdata);

                                String message = jsonObject.optString("message");
                                Toast.makeText(DriveryLicenceActivity.this, message, Toast.LENGTH_SHORT).show();

                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(DriveryLicenceActivity.this, "", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<UploadDLResponse> call, Throwable t) {
                        Toast.makeText(DriveryLicenceActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                    }
                });
        }
    }




}
