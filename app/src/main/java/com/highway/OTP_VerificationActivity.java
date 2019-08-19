package com.highway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import modelclass.VerifyOTPRequest;
import modelclass.VerifyOTPResponse;
import retrofit.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.HighwayPreface;
import utils.Utils;

public class OTP_VerificationActivity extends AppCompatActivity {

    private EditText verifypin;
    private Button btnVerify;
    private ImageView backImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp__verification);

        verifypin = findViewById(R.id.Verifypin_edittext);
        backImage = findViewById(R.id.back_arrow_OTP);
        btnVerify = findViewById(R.id.VerifyPin_btn);

        verifyPerformOperation();
        backArrowOperationOnOtpVerifypage();  // arrow operation
    }

    public void verifyPerformOperation() {
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verifyPinOperation();

            }
        });
    }


    public void verifyPinOperation() {

        boolean check = true;
        String otpNumber = verifypin.getText().toString().trim();
        String Usermobilenumber = HighwayPreface.getString(getApplicationContext(), "phone_number");

        if (otpNumber.isEmpty()) {
            verifypin.setError("enter a valid otp");
            check = false;
        } else {
            verifypin.setError(null);
        }

        if (check) {
            if (verifypin != null) {

                VerifyOTPRequest verifyOTPRequest = new VerifyOTPRequest();
                verifyOTPRequest.setOtp(otpNumber);
                verifyOTPRequest.setMobile(Usermobilenumber);

                Utils.showProgressDialog(this);
                RestClient.otpVerifed(verifyOTPRequest, new Callback<VerifyOTPResponse>() {
                    @Override
                    public void onResponse(Call<VerifyOTPResponse> call, Response<VerifyOTPResponse> response) {
                        Utils.dismissProgressDialog();
                        if (response.body() != null) {
                            if (response.body().getStatus() == true) {
                                Intent i = new Intent(OTP_VerificationActivity.this, RegistrationSignUpFormActivity.class);
                                startActivity(i);
                                Toast.makeText(OTP_VerificationActivity.this, "Otp verified successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            } else if (response.body().getStatus() == false) {

                                Toast.makeText(OTP_VerificationActivity.this, "enter valid otp", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<VerifyOTPResponse> call, Throwable t) {
                        Toast.makeText(OTP_VerificationActivity.this, "Enter your OTP", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
    public void backArrowOperationOnOtpVerifypage() {
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OTP_VerificationActivity.this, LoginRegisterActivity.class);
                startActivity(i);
                finish();
            }
        });


    }


}
