package com.highwayjprproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.highwayjprproject.R;
import com.highwayjprproject.model.otpverify.VerifyOtpRequest;
import com.highwayjprproject.model.otpverify.VerifyOtpResponse;
import com.highwayjprproject.retrofit.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.highwayjprproject.usermodule.activitries.RegistrationDetailsActivity;
import com.highwayjprproject.utils.Constants;
import com.highwayjprproject.utils.HighwayPrefs;
import com.highwayjprproject.utils.Utils;

public class MobileOtpVerificationActivity extends AppCompatActivity {
    private EditText verifyPin;
    private Button btnVerify;
    private ImageView backImage;
    private TextView timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_otp_verification);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        verifyPin = findViewById(R.id.verifyPin_edittext);
        backImage = findViewById(R.id.back_arrow_OTP);
        btnVerify = findViewById(R.id.verifyPin_btn);
        timer = findViewById(R.id.timmer_textview);


        timerInOtp();                          // time count dowan of otp
        backArrowOperationOnOtpVerifypage();
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyPinOperation(); // otp option perform
            }
        });


    }

    public void backArrowOperationOnOtpVerifypage() {
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MobileOtpVerificationActivity.this, CustomerLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void timerInOtp() {

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timer.setText("Resend otp");
            }

        }.start();

    }

    public void verifyPinOperation() {

        boolean check = true;
        String otpNumber = verifyPin.getText().toString().trim();
        String usermobileNumber = HighwayPrefs.getString(getApplicationContext(), Constants.USERMOBILE);

        if (otpNumber.isEmpty()) {
            verifyPin.setError("enter a valid otp");
            check = false;
        } else {
            verifyPin.setError(null);
        }

        if (check) {

            VerifyOtpRequest verifyOtpRequest = new VerifyOtpRequest();
            verifyOtpRequest.setOtp(otpNumber);
            verifyOtpRequest.setMobile(usermobileNumber);

            if (Utils.isInternetConnected(this)) {


                Utils.showProgressDialog(this);

                RestClient.otpVerify(verifyOtpRequest, new Callback<VerifyOtpResponse>() {
                    @Override
                    public void onResponse(Call<VerifyOtpResponse> call, Response<VerifyOtpResponse> response) {
                        Utils.dismissProgressDialog();

                        if (response.body() != null) {
                            if (response.body().getUserStatus().equalsIgnoreCase("1")) {
                                Intent intent = new Intent(MobileOtpVerificationActivity.this, DashBoardActivity.class);

                                HighwayPrefs.putBoolean(getApplicationContext(), Constants.LOGGED_IN, true);
                                HighwayPrefs.putString(getApplicationContext(), Constants.NAME, response.body().getName());
                                HighwayPrefs.putString(getApplicationContext(), Constants.USERMOBILE, response.body().getMobile());
                                HighwayPrefs.putString(getApplicationContext(), Constants.IMAGE, response.body().getImage());
                                HighwayPrefs.putString(getApplicationContext(), Constants.EMAIL, response.body().getEmail());
                                HighwayPrefs.putString(getApplicationContext(), Constants.GENDER, response.body().getGender());
                                HighwayPrefs.putString(getApplicationContext(), Constants.ROLEID, response.body().getRoleId());
                                HighwayPrefs.putString(getApplicationContext(), Constants.ADDRESS, response.body().getAddress());

                                startActivity(intent);
                                Toast.makeText(MobileOtpVerificationActivity.this, "Wlcm to Highway", Toast.LENGTH_SHORT).show();
                                finish();

                            } else if (TextUtils.isEmpty(response.body().getName())) {
                                Intent intent = new Intent(MobileOtpVerificationActivity.this, RegistrationDetailsActivity.class);

                                HighwayPrefs.putString(getApplicationContext(), Constants.ID, response.body().getId());

                                startActivity(intent);
                                finish();
                            }
                        } else {
                            Toast.makeText(MobileOtpVerificationActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<VerifyOtpResponse> call, Throwable t) {
                        Toast.makeText(MobileOtpVerificationActivity.this, "Otp verification failed", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }
    }

    // onBacked pressed
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
