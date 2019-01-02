package com.videoapp.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.videoapp.BuildConfig;
import com.videoapp.R;
import com.videoapp.model.LoginResponse;
import com.videoapp.retrofit.RestClient;
import com.videoapp.utill.AlertDialogManager;
import com.videoapp.utill.AppConstants;
import com.videoapp.utill.AppUtils;
import com.videoapp.utill.RuntimePermissionHelper;
import com.videoapp.utill.VideoAppPrefs;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText emails, password;
    String email_str, pass_str;
    ImageButton btn_login_;
    TextView signup_text_;
    private RuntimePermissionHelper runtimePermissionHelper;
    private int count = 0;
    private static final String LoginUrl = " http://akwebtech.com/dev/api/api.php?req=login ";
    AlertDialogManager alert = new AlertDialogManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window w = getWindow(); // in Activity's onCreate() for instance
//            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
        }

        emails = findViewById(R.id.useremail);
        password = findViewById(R.id.userpassword);
        btn_login_ = findViewById(R.id.btnUserlogin);
        signup_text_ = findViewById(R.id.usersingup);

        btn_login_.setOnClickListener(this);
        signup_text_.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= 23) {
            runtimePermissionHelper = RuntimePermissionHelper.getInstance(this);
            if (runtimePermissionHelper.isAllPermissionAvailable()) {
// All permissions available. Go with the flow
            } else {
// Few permissions not granted. Ask for ungranted permissions
                runtimePermissionHelper.setActivity(this);
                runtimePermissionHelper.requestPermissionsIfDenied();
            }
        } else {
// SDK below API 23. Do nothing just go with the flow.
        }




    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean permissionGranted = true;
        for (int i : grantResults) {
            if (i != PackageManager.PERMISSION_GRANTED) {
                permissionGranted = false;
                break;
            }
        }
        if (!permissionGranted) {
            if (count > 4) {
                Toast.makeText(this, R.string.location_permission_toast, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + BuildConfig.APPLICATION_ID)));
            }
            runtimePermissionHelper.requestPermissionIfDenied(RuntimePermissionHelper.PERMISSION_ACCESS_FINE_LOCATION);
            runtimePermissionHelper.requestPermissionIfDenied(RuntimePermissionHelper.PERMISSION_ACCESS_CAMERA);
            runtimePermissionHelper.requestPermissionIfDenied(RuntimePermissionHelper.PERMISSION_ACCESS_WRITE_EXTERNAL_STORAGE);

        }
        count++;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnUserlogin) {
//            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
//            finish();

            email_str = emails.getText().toString();
            pass_str = password.getText().toString();
            if (TextUtils.isEmpty(email_str.trim()) || email_str.length() ==0) {
                AppUtils.displayToast(getApplicationContext(), "Please enter valid email");
                return;
            }
            if (TextUtils.isEmpty(pass_str.trim()) || pass_str.length() == 0) {
                AppUtils.displayToast(getApplicationContext(), "Please enter valid password");
                return;
            }

            RequestBody email = RequestBody.create(MediaType.parse("text/plain"), email_str);

            RequestBody pwd = RequestBody.create(MediaType.parse("text/plain"), pass_str);

            AppUtils.showProgressDialog(LoginActivity.this, "Please wait...");
            RestClient.loginUser(email,pwd, new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    AppUtils.dismissProgressDialog();
                    if (response != null && response.body() != null) {
                        LoginResponse loginResponse = response.body();
                        if (Integer.parseInt(loginResponse.getStatus()) == 1) {
                            AppUtils.displayToast(LoginActivity.this, loginResponse.getMessage());
                            VideoAppPrefs.putString(getApplicationContext(), AppConstants.CUSTOMER_ID,loginResponse.getLoginDetail().get(0).getId());
                            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                            finish();
                        }

                    } else {
                        AppUtils.displayToast(LoginActivity.this, "Invalid login detail");

                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    AppUtils.dismissProgressDialog();
                    AppUtils.displayToast(LoginActivity.this, "Invalid login detail");

                }
            });
        } else if (v.getId() == R.id.usersingup) {
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            finish();
        }
    }
}
