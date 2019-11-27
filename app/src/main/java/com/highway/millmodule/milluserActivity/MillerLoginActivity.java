package com.highway.millmodule.milluserActivity;

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

public class MillerLoginActivity extends AppCompatActivity {

    private EditText millerPhoneNo;
    private Button btnMillerOtp;
    String phone_number;
    private int backpress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miller_login);

        millerPhoneNo = findViewById(R.id.edtTxtMillerMobNo);
        btnMillerOtp = findViewById(R.id.btnSendMillerOtp);


        btnMillerOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validationMillerLogin();
            }
        });
    }

    public boolean inputMillerVatidation(){
        boolean check = true;

        phone_number = millerPhoneNo.getText().toString();

        if (phone_number.isEmpty() && millerPhoneNo.length()==10) {
            millerPhoneNo.setError(" enter a valid phone number ");
            check = false;
        } else {
            millerPhoneNo.setError(null);
            check = true;
        }

        return check;
    }

    public void validationMillerLogin(){

        if (inputMillerVatidation()){

            LoginRegisterRequest loginRegisterRequest = new LoginRegisterRequest();
            loginRegisterRequest.setMobile(phone_number);

            if (Utils.isInternetConnected(this)) {

                Utils.showProgressDialog(this);

                RestClient.loginUser(loginRegisterRequest, new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Utils.dismissProgressDialog();
                        if (response.body() != null) {
                            if (response.code() == 200) {
                                Intent intent = new Intent(MillerLoginActivity.this, MobileOtpVerificationActivity.class);

                                HighwayPrefs.putString(MillerLoginActivity.this, Constants.USERMOBILE, phone_number);
                                /********************/
                              /* *//* HighwayPrefs.putString(MillerLoginActivity.this,Constants.MillerMOBILE,"9471444622");*//*
                                HighwayPrefs.putString(MillerLoginActivity.this,Constants.ID,"2");
                                HighwayPrefs.putString(MillerLoginActivity.this,Constants.NAME,"Vikash");
                                HighwayPrefs.putString(MillerLoginActivity.this,Constants.MillerEmail,"prit@gmail.com");
                                HighwayPrefs.putString(MillerLoginActivity.this,Constants.MillerGender,"Male");
                                HighwayPrefs.putString(MillerLoginActivity.this,Constants.ROLEID,"2");
                                HighwayPrefs.putBoolean(MillerLoginActivity.this,Constants.User_statuss,true);*/

                                startActivity(intent);
                                finish();

                                Toast.makeText(MillerLoginActivity.this, "pls Verify Otp", Toast.LENGTH_SHORT).show();
                               /* Toast.makeText(MillerLoginActivity.this, "Welcome Miller", Toast.LENGTH_SHORT).show();*/
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(MillerLoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();

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
