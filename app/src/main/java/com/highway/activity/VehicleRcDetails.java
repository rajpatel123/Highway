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

import modelclass.UploadVehicleRcRequest;
import modelclass.UploadVehicleRcResponse;
import retrofit.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.CameraUtils;
import utils.HighwayPreface;
import utils.Utils;

public class VehicleRcDetails extends AppCompatActivity {

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

    private ImageView backVRCimageView, imageViewVichealFont, imageViewVichealBack, vhRcCameraGalleryOpen;
    private AnimatorSet animatorSet;
    private TextView vRctxtDescription;
    private Button submitVehicleRcdetails;
    private EditText vehicleRcNumber, vehicleColor;
    private String base64ImageServerVRc;
    private String vehicleId, vrc_number, v_Color;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_details);

        vhRcCameraGalleryOpen = findViewById(R.id.VehicleEditOpen);
        imageViewVichealFont = findViewById(R.id.Vichiel_RC_ImageviewFont);
        vehicleRcNumber = findViewById(R.id.Vehicle_Rc_Number);
        vehicleColor = findViewById(R.id.Vehicle_Rc_color);
        submitVehicleRcdetails = findViewById(R.id.sub_Vehicle_Rc_Button);
        // backVRCimageView = findViewById(R.id.backArrow_Vihal_Rc_Image);
        //  vRctxtDescription = findViewById(R.id.Vehical_Rc_txt_desc);


        submitVehicleRcdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitVehicleRCDetails();
            }
        });

        imageViewVichealFont.setOnClickListener(new View.OnClickListener() {
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_IMAGE_STORAGE_PATH, imageStoragePath);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        imageStoragePath = savedInstanceState.getString(KEY_IMAGE_STORAGE_PATH); // get the file url
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                CameraUtils.refreshGallery(getApplicationContext(), imageStoragePath);

                //successfully captured the image display it in image view
                previewCapturedImageFont();
                // previewCapturedImageBack();
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


                /*video successfully recorded preview the recorded video  */
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

            //vRctxtDescription.setVisibility(View.GONE);
            // imageViewVichealBack.setVisibility(View.GONE);

            imageViewVichealFont.setVisibility(View.VISIBLE);
            Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);
            base64ImageServerVRc = getEncoded64ImageStringFromBitmap(bitmap);
            imageViewVichealFont.setImageBitmap(bitmap);

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
                        CameraUtils.openSettings(VehicleRcDetails.this);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }


    public boolean inputVehicle_RC_DetailsValidation() {

        //boolean check = true;
        vrc_number = vehicleRcNumber.getText().toString();
        v_Color = vehicleColor.getText().toString();

        if (TextUtils.isEmpty(vrc_number) || vrc_number.length() <= 8 || vrc_number.length() >= 14) {
            vehicleRcNumber.setError("Enter a valid Vehicle RC Number");
            return false;
        } else {
            vehicleRcNumber.setError(null);
        }

        if (TextUtils.isEmpty(v_Color)) {
            vehicleColor.setError("enter Vehicle color");
            return false;
        } else {
            vehicleColor.setError(null);
        }
        if (TextUtils.isEmpty(base64ImageServerVRc)) {
            Toast.makeText(this, "Pls capture Image", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void submitVehicleRCDetails() {
        if (inputVehicle_RC_DetailsValidation()) {
            UploadVehicleRcRequest uploadVehicleRcRequest = new UploadVehicleRcRequest();
            uploadVehicleRcRequest.setVehicalNumber(vrc_number);
            uploadVehicleRcRequest.setBikeColour(v_Color);
            uploadVehicleRcRequest.setRCImage(base64ImageServerVRc);
            vehicleId = HighwayPreface.getString(getApplicationContext(),"id");
            uploadVehicleRcRequest.setUserId(vehicleId);
            Utils.showProgressDialog(this);

            RestClient.uploadVehicleRCDetails(uploadVehicleRcRequest, new Callback<UploadVehicleRcResponse>() {
                @Override
                public void onResponse(Call<UploadVehicleRcResponse> call, Response<UploadVehicleRcResponse> response) {
                    if (response.body()!=null){
                        if (response.body().getStatus()==true){
                            Intent i = new Intent(VehicleRcDetails.this,MainActivity.class);
                            startActivity(i);
                            Toast.makeText(VehicleRcDetails.this, "Vehicle Rc Details uploaded successfully", Toast.LENGTH_SHORT).show();
                           finish();
                        }else if (response.body().getStatus()==false){
                            Toast.makeText(VehicleRcDetails.this, "Details Uploaded failed", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        try {
                            String rowdata = response.errorBody().string();
                            JSONObject jsonObject = new JSONObject(rowdata);
                            String message = jsonObject.optString("message");
                            Toast.makeText(VehicleRcDetails.this,message, Toast.LENGTH_SHORT).show();

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(VehicleRcDetails.this, "", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<UploadVehicleRcResponse> call, Throwable t) {

                }
            });
        }

    }

}
