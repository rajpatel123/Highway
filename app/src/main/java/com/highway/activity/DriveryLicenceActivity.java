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

import java.io.ByteArrayOutputStream;
import java.io.File;
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

    private ImageView cameraOpenButton, licenceimageViewFront, licenceimageViewBack, backArrowOnDl;
    private ImageView imageView0, imageView1, imageView2, imageView3;
    private AnimatorSet animatorSet;
    private Button submitDldetails;
    private EditText dlNumber, dlExpDate;
    private TextView txtDescription;

    private String baseimageserver;
    private Bitmap bitmap;
    private String userIdNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivery_licence);

        txtDescription = findViewById(R.id.txt_desc);
        licenceimageViewFront = findViewById(R.id.drivingLicencefront);
        // licenceimageViewBack = findViewById(R.id.drivingLicenceback);
        cameraOpenButton = findViewById(R.id.DlcameraOpen);

        dlExpDate = findViewById(R.id.edt_DlExpireDate);
        dlNumber = findViewById(R.id.edt_DlNumber);
        backArrowOnDl = findViewById(R.id.backArrow_Dl_Image);
        submitDldetails = findViewById(R.id.submitDL_Button);

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

        /**
         * Capture image on button click
         */
        cameraOpenButton.setOnClickListener(new View.OnClickListener() {

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

    /**
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
                                // capture picture
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

    /**
     * Capturing Camera Image will launch camera app requested image capture
     */
    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File file = CameraUtils.getOutputMediaFile(MEDIA_TYPE_IMAGE);
        if (file != null) {
            imageStoragePath = file.getAbsolutePath();
        }

        Uri fileUri = CameraUtils.getOutputMediaFileUri(getApplicationContext(), file);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    /**
     * Saving stored image path to saved instance state
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on screen orientation
        // changes
        outState.putString(KEY_IMAGE_STORAGE_PATH, imageStoragePath);
    }

    /**
     * Restoring image path from saved instance state
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        imageStoragePath = savedInstanceState.getString(KEY_IMAGE_STORAGE_PATH);
    }

    /**
     * Activity result method will be called after closing the camera
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Refreshing the gallery
                CameraUtils.refreshGallery(getApplicationContext(), imageStoragePath);

                // successfully captured the image
                // display it in image view
                previewCapturedImageFont();
                // previewCapturedImageBack();
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == CAMERA_CAPTURE_VIDEO_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Refreshing the gallery
                CameraUtils.refreshGallery(getApplicationContext(), imageStoragePath);

                // video successfully recorded
                // preview the recorded video
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

       /* switch (requestCode) {
            case CAMERA_CAPTURE_BACK_IMAGE_REQUEST_CODE:

                if (resultCode == RESULT_OK) {
                    // Refreshing the gallery
                    CameraUtils.refreshGallery(getApplicationContext(), imageStoragePath);

                    // successfully captured the image
                    // display it in image view
                    previewCapturedImageBack();
                } else if (resultCode == RESULT_CANCELED) {
                    // user cancelled Image capture
                    Toast.makeText(getApplicationContext(),
                            "User cancelled image capture", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    // failed to capture image
                    Toast.makeText(getApplicationContext(),
                            "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                            .show();
                }

                break;

            case CAMERA_CAPTURE_FRONT_IMAGE_REQUEST_CODE:

                if (resultCode == RESULT_OK) {
                    // Refreshing the gallery
                    CameraUtils.refreshGallery(getApplicationContext(), imageStoragePath);

                    // successfully captured the image
                    // display it in image view
                    previewCapturedImageFont();
                } else if (resultCode == RESULT_CANCELED) {
                    // user cancelled Image capture
                    Toast.makeText(getApplicationContext(), "User cancelled image capture", Toast.LENGTH_SHORT).show();
                } else {
                    // failed to capture image
                    Toast.makeText(getApplicationContext(), "Sorry! Failed to capture image", Toast.LENGTH_SHORT).show();
                }
        }*/


    }

    public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteFormat = stream.toByteArray();
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        return imgString;
    }

    /**
     * Display image from gallery
     */
    private void previewCapturedImageFont() {
        try {
            // hide video preview
            txtDescription.setVisibility(View.GONE);
            // licenceimageViewBack.setVisibility(View.GONE);

            licenceimageViewFront.setVisibility(View.VISIBLE);

            Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);

            baseimageserver = getEncoded64ImageStringFromBitmap(bitmap);

            licenceimageViewFront.setImageBitmap(bitmap);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
  /*  private void previewCapturedImageBack() {
        try {
            txtDescription.setVisibility(View.GONE);
            licenceimageViewFront.setVisibility(View.GONE);

            licenceimageViewBack.setVisibility(View.VISIBLE);

            Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);

            baseimageserver =getEncoded64ImageStringFromBitmap(bitmap);


            licenceimageViewBack.setImageBitmap(bitmap);


        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }*/


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

    /**
     * Display image from gallery
     * <p>
     * private void previewCapturedImageBack() {
     * try {
     * // hide video preview
     * txtDescription.setVisibility(View.GONE);
     * licenceimageViewFront.setVisibility(View.GONE);
     * <p>
     * licenceimageViewBack.setVisibility(View.VISIBLE);
     * <p>
     * Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);
     * <p>
     * licenceimageViewBack.setImageBitmap(bitmap);
     * <p>
     * } catch (NullPointerException e) {
     * e.printStackTrace();
     * }
     * }
     */



    public void uploadDLValidationSubmit() {

        String dL_Number = dlNumber.getText().toString();
        String dL_Expire = dlExpDate.getText().toString();
      //String Dl_Image = licenceimageViewFront.getVisibility().

        boolean check = true;

        if (dL_Number.isEmpty() || dlNumber.length()==16) {
            dlNumber.setError(" enter a valid DL number ");
            check = false;
        } else {
            dlNumber.setError(null);
        }

        if (dL_Expire.isEmpty() ) {
            dlExpDate.setError("enter valid expire date in yy-mm-dd");
            check = false;
        } else {
            dlExpDate.setError(null);
        }

        if (check){

            UploadDLRequest uploadDLRequest = new UploadDLRequest();
            uploadDLRequest.setLicenseNumber(dL_Number);
            uploadDLRequest.setExpiryDate(dL_Expire);

            uploadDLRequest.setLicenseImage(baseimageserver);

            userIdNew = HighwayPreface.getString(getApplicationContext(), "id");
            uploadDLRequest.setUserId(userIdNew);

            Utils.showProgressDialog(this);

            RestClient.uploadDL(uploadDLRequest, new Callback<UploadDLResponse>() {
                @Override
                public void onResponse(Call<UploadDLResponse> call, Response<UploadDLResponse> response) {
                    Utils.dismissProgressDialog();
                    Toast.makeText(DriveryLicenceActivity.this, "Success", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<UploadDLResponse> call, Throwable t) {
                    Toast.makeText(DriveryLicenceActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                }
            });

        }


    }

 /*   public void sendDLDeatails() {

        UploadDLRequest uploadDLRequest = new UploadDLRequest();

        userIdNew = HighwayPreface.getString(getApplicationContext(), "id");

        uploadDLRequest.setLicenseImage(baseimageserver);
        uploadDLRequest.setExpiryDate("2019-12-12");
        uploadDLRequest.setLicenseNumber("12345678");

        uploadDLRequest.setUserId(userIdNew);


        RestClient.uploadDL(uploadDLRequest, new Callback<UploadDLResponse>() {
            @Override
            public void onResponse(Call<UploadDLResponse> call, Response<UploadDLResponse> response) {
                Toast.makeText(DriveryLicenceActivity.this, "DL Uploaded Successfull", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<UploadDLResponse> call, Throwable t) {
                Toast.makeText(DriveryLicenceActivity.this, "failure", Toast.LENGTH_SHORT).show();

            }
        });


    } */


}
