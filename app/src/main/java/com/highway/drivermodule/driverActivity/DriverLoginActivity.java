package com.highway.drivermodule.driverActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.highway.R;
import com.highway.common.base.activity.MobileOtpVerificationActivity;
import com.highway.common.base.commonModel.login.LoginRegisterRequest;
import com.highway.commonretrofit.RestClient;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;
import com.highway.utils.Utils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverLoginActivity extends AppCompatActivity {

    private EditText edtDriverMobNo;
    private Button sendOtp;
    String phone_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);

        edtDriverMobNo = findViewById(R.id.edtTxtDriverMobNo);
        sendOtp = findViewById(R.id.btnSendDriverOtp);

        sendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateDrivwerLogin();
            }
        });
    }

    public boolean inputValidation() {

        boolean check = true;

        phone_number = edtDriverMobNo.getText().toString();

        if (phone_number.isEmpty() && edtDriverMobNo.length() == 10) {
            edtDriverMobNo.setError(" enter a valid phone number ");
            check = false;
        } else {
            edtDriverMobNo.setError(null);
            check = true;
        }

        return check;
    }

    public void validateDrivwerLogin() {

        if (inputValidation()) {

            LoginRegisterRequest loginRegisterRequest = new LoginRegisterRequest();
            loginRegisterRequest.setMobile(phone_number);

            if (Utils.isInternetConnected(this)) {

                Utils.showProgressDialog(getApplicationContext());

                RestClient.loginUser(loginRegisterRequest, new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Utils.dismissProgressDialog();

                        if (response.body() != null) {
                            if (response.code() ==200) {
                                Intent intent = new Intent(DriverLoginActivity.this, MobileOtpVerificationActivity.class);
                                HighwayPrefs.putString(DriverLoginActivity.this, Constants.USERMOBILE, phone_number);
                               /*
                                HighwayPrefs.putString(DriverLoginActivity.this,Constants.ID,"3");
                                HighwayPrefs.putString(DriverLoginActivity.this,Constants.NAME,"Santosh");
                                HighwayPrefs.putString(DriverLoginActivity.this,Constants.DriverEmail,"prit@gmail.com");
                                HighwayPrefs.putString(DriverLoginActivity.this,Constants.MillerGender,"Male");
                                HighwayPrefs.putString(DriverLoginActivity.this,Constants.ROLEID,"3");
                                HighwayPrefs.putBoolean(DriverLoginActivity.this,Constants.User_statuss,true);*/

                                startActivity(intent);
                                finish();
                                Toast.makeText(DriverLoginActivity.this, "Pls verify Otp ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                        Toast.makeText(DriverLoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }

    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;

        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


}
