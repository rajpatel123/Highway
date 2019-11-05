package com.highwayjprproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.highwayjprproject.R;
import com.highwayjprproject.model.login.LoginRegisterRequest;
import com.highwayjprproject.model.login.LoginRegisterResponse;
import com.highwayjprproject.retrofit.RestClient;
import com.highwayjprproject.utils.Constants;
import com.highwayjprproject.utils.HighwayPrefs;
import com.highwayjprproject.utils.Utils;

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

                RestClient.loginUser(loginRegisterRequest, new Callback<LoginRegisterResponse>() {
                    @Override
                    public void onResponse(Call<LoginRegisterResponse> call, Response<LoginRegisterResponse> response) {
                        Utils.dismissProgressDialog();
                        if (response.body() != null) {
                            if (response.body().getStatus() == true) {
                                Intent intent = new Intent(MillerLoginActivity.this, MobileOtpVerificationActivity.class);
                                HighwayPrefs.putString(MillerLoginActivity.this, Constants.USERMOBILE, phone_number);
                                startActivity(intent);
                                finish();
                                Toast.makeText(MillerLoginActivity.this, "pls Verify Otp", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<LoginRegisterResponse> call, Throwable t) {
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
