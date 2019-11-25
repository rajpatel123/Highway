package com.highway.common.base.activity;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.highway.R;
import com.highway.common.base.commonModel.otpverify.VerifyOtpRequest;
import com.highway.common.base.commonModel.otpverify.VerifyOtpResponse;
import com.highway.commonretrofit.RestClient;
import com.highway.customer.RegistrationDetailsActivity;
import com.highway.customer.customerActivity.CustomerLoginActivity;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;
import com.highway.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

            VerifyOtpResponse verifyOtpResponse = new VerifyOtpResponse();
            verifyOtpResponse.setEmail("fhd@fhjdfh.fnd");
            verifyOtpResponse.setImage("");
            verifyOtpResponse.setMobile("4657247345");
            gotoDashboardAfterLogin(verifyOtpResponse);

//            if (Utils.isInternetConnected(this)) {
//
//
//                Utils.showProgressDialog(this);
//
//                RestClient.otpVerify(verifyOtpRequest, new Callback<VerifyOtpResponse>() {
//                    @Override
//                    public void onResponse(Call<VerifyOtpResponse> call, Response<VerifyOtpResponse> response) {
//                        Utils.dismissProgressDialog();
//
//                        if (response.body() != null) {
//                            if (response.body().getUserStatus().equalsIgnoreCase("1")) {
//                                gotoDashboardAfterLogin(response.body());
//
//                            } else if (TextUtils.isEmpty(response.body().getName())) {
//                                Intent intent = new Intent(MobileOtpVerificationActivity.this, RegistrationDetailsActivity.class);
//
//                                HighwayPrefs.putString(getApplicationContext(), Constants.ID, response.body().getId());
//
//                                startActivity(intent);
//                                finish();
//                            }
//                        } else {
//                            Toast.makeText(MobileOtpVerificationActivity.this, "Failed", Toast.LENGTH_SHORT).show();
//                        }
//
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<VerifyOtpResponse> call, Throwable t) {
//                        Toast.makeText(MobileOtpVerificationActivity.this, "Otp verification failed", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//            }
        }
    }

    private void gotoDashboardAfterLogin(VerifyOtpResponse verifyOtpResponse) {
        Intent intent = new Intent(MobileOtpVerificationActivity.this, DashBoardActivity.class);

        HighwayPrefs.putBoolean(getApplicationContext(), Constants.LOGGED_IN, true);
        HighwayPrefs.putString(getApplicationContext(), Constants.NAME, verifyOtpResponse.getName());
        HighwayPrefs.putString(getApplicationContext(), Constants.USERMOBILE, verifyOtpResponse.getMobile());
        HighwayPrefs.putString(getApplicationContext(), Constants.IMAGE, verifyOtpResponse.getImage());
        HighwayPrefs.putString(getApplicationContext(), Constants.EMAIL, verifyOtpResponse.getEmail());
        HighwayPrefs.putString(getApplicationContext(), Constants.GENDER, verifyOtpResponse.getGender());
        HighwayPrefs.putString(getApplicationContext(), Constants.ROLEID, verifyOtpResponse.getRoleId());
        HighwayPrefs.putString(getApplicationContext(), Constants.ADDRESS, verifyOtpResponse.getAddress());

        startActivity(intent);
        Toast.makeText(MobileOtpVerificationActivity.this, "Wlcm to Highway", Toast.LENGTH_SHORT).show();
        finish();
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
