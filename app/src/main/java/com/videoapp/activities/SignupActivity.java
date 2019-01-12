package com.videoapp.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.videoapp.R;
import com.videoapp.model.CommonApiResponse;
import com.videoapp.retrofit.ApiInterface;
import com.videoapp.retrofit.RestClient;
import com.videoapp.utill.AppUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SignupActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    EditText username,userpassword,useremail,usercontact;
    Button btnRegister;
    TextView link_login;
    ImageView imgUserImage;
    String pathToPhoto;
    Uri selectedImage;
    private static final String SERVER_PATH = " http://akwebtech.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
        }
        username=findViewById(R.id.regUserName);
        imgUserImage = (ImageView) findViewById(R.id.imgUserImage);
        userpassword=findViewById(R.id.regUserpassword);
        useremail=findViewById(R.id.regUserEmail);
        usercontact=findViewById(R.id.regUserContactNo);
        btnRegister=findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Resigaternewuser();
            }
        });
//        link_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
//                finish();
//            }
//        });


    }
    private void Resigaternewuser(){

        if (TextUtils.isEmpty(username.getText().toString())){
            username.setError(Html.fromHtml("<font color=white>" + "Username is required." + "</font>"));
        } else if (TextUtils.isEmpty(userpassword.getText().toString())) {
            userpassword.setError(Html.fromHtml("<font color=white>" + "Password is required." + "</font>"));
        } else if (TextUtils.isEmpty(useremail.getText().toString())){
            useremail.setError(Html.fromHtml("<font color=white>" + "Email is required." + "</font>"));
        } else if (TextUtils.isEmpty(usercontact.getText().toString())){
            usercontact.setError(Html.fromHtml("<font color=white>" + "Contact number is required." + "</font>"));
        } else {

            uploadVideoToServer(pathToPhoto);


//            RequestBody name = RequestBody.create(MediaType.parse("text/plain"), username.getText().toString().trim());
//            RequestBody email = RequestBody.create(MediaType.parse("text/plain"), useremail.getText().toString().trim());
//            RequestBody mobile = RequestBody.create(MediaType.parse("text/plain"), usercontact.getText().toString().trim());
//            RequestBody password = RequestBody.create(MediaType.parse("text/plain"), userpassword.getText().toString().trim());
//            RequestBody image = RequestBody.create(MediaType.parse("text/plain"), "");
//
//            //AppUtils.showProgressDialog(getApplicationContext(), "PLease wait...");
//            RestClient.registerUser(name, email, mobile, image, password, new Callback<CommonApiResponse>() {
//                @Override
//                public void onResponse(Call<CommonApiResponse> call, retrofit2.Response<CommonApiResponse> response) {
//                    AppUtils.dismissProgressDialog();
//                    if (response.body() != null) {
//                        if (response.body().getStatus().equalsIgnoreCase("1")) {
//                            AppUtils.displayToast(getApplicationContext(), "Successfuly registered");
//                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//                            finish();
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<CommonApiResponse> call, Throwable t) {
//                    AppUtils.dismissProgressDialog();
//                    AppUtils.displayToast(getApplicationContext(), "Unable to register, please try again later");
//
//                }
//            });
            //link_login=findViewById(R.id.link_login);

//            link_login.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent login = new Intent(SignupActivity.this, LoginActivity.class);
//                    startActivity(login);
//                }
//            });
        }



    }

    public void fncPickImage(View view){

        try {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            } else {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //galleryIntent.putExtra("crop", "true");
                galleryIntent.putExtra("aspectX", 1);
                galleryIntent.putExtra("aspectY", 1);
                galleryIntent.putExtra("outputX", 200);
                galleryIntent.putExtra("outputY", 200);
                galleryIntent.putExtra("return-data", true);
                startActivityForResult(galleryIntent, 1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    //galleryIntent.putExtra("crop", "true");
                    galleryIntent.putExtra("aspectX", 1);
                    galleryIntent.putExtra("aspectY", 1);
                    galleryIntent.putExtra("outputX", 200);
                    galleryIntent.putExtra("outputY", 200);
                    galleryIntent.putExtra("return-data", true);
                    startActivityForResult(galleryIntent, 1);
                } else {
                    //do something like displaying a message that he didn`t allow the app to access gallery and you wont be able to let him select from gallery
                }
                break;
        }
    }

    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && null != data){
            try{
                selectedImage = data.getData();
                if (EasyPermissions.hasPermissions(SignupActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)){
                    pathToPhoto = getRealPathFromURIPath(selectedImage, this);

                    //Bundle bundle = data.getExtras();
                    Bitmap image = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);;
                    Bitmap circleImage = getRoundedShape(image);
                    //String encodedImage = encodeImage(circleImage);
                    //Log.i("Encoded", encodedImage);
                    imgUserImage.setImageBitmap(circleImage);

                }




            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    private void uploadVideoToServer(String pathToVideoFile){
        File videoFile = new File(pathToVideoFile);
        RequestBody videoBody = RequestBody.create(okhttp3.MultipartBody.FORM, "file");
//
//        RequestBody cid = RequestBody.create(
//                okhttp3.MultipartBody.FORM, "23");

        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), username.getText().toString().trim());
        RequestBody email = RequestBody.create(MediaType.parse("text/plain"), useremail.getText().toString().trim());
        RequestBody mobile = RequestBody.create(MediaType.parse("text/plain"), usercontact.getText().toString().trim());
        RequestBody password = RequestBody.create(MediaType.parse("text/plain"), userpassword.getText().toString().trim());
        RequestBody image = RequestBody.create(okhttp3.MultipartBody.FORM, "file");

        MultipartBody.Part vFile = MultipartBody.Part.createFormData("file", videoFile.getName(), videoBody);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(SERVER_PATH).addConverterFactory(GsonConverterFactory.create()).build();
        ApiInterface vInterface = retrofit.create(ApiInterface.class);
        //showProgressDialog(this);
        Call<CommonApiResponse>  serverCom = vInterface.registerUser(name, email, mobile, password, image);
        serverCom.enqueue(new Callback<CommonApiResponse>() {
            @Override
            public void onResponse(Call<CommonApiResponse> call, Response<CommonApiResponse> response) {
                //dismissProgressDialog();
                CommonApiResponse uploadResponse = response.body();
                if (response.code()==200){
                    if (uploadResponse!=null && Integer.parseInt(uploadResponse.getStatus())==1){
                        Toast.makeText(SignupActivity.this, uploadResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(SignupActivity.this, "Unable to uplaod, try again later", Toast.LENGTH_SHORT).show();
                    }

                }
            }
            @Override
            public void onFailure(Call<CommonApiResponse> call, Throwable t) {
//                dismissProgressDialog();
//                Log.d(TAG, "Error message " + t.getMessage());
            }
        });
    }

    public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
        int targetWidth = 250;
        int targetHeight = 250;
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight,Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth),
                        ((float) targetHeight)) / 2),
                Path.Direction.CCW);

        canvas.clipPath(path);
        Bitmap sourceBitmap = scaleBitmapImage;
        canvas.drawBitmap(sourceBitmap,
                new Rect(0, 0, sourceBitmap.getWidth(),
                        sourceBitmap.getHeight()),
                new Rect(0, 0, targetWidth, targetHeight), null);
        return targetBitmap;
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }
}
