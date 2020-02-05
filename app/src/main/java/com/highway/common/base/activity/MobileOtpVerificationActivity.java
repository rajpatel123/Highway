package com.highway.common.base.activity;

import android.content.Intent;
import android.content.IntentFilter;
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
import com.highway.customer.customerActivity.LoginActivityForCustomer;
import com.highway.interfaces.SmsListener;
import com.highway.reciever.SmsReceiver;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;
import com.highway.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobileOtpVerificationActivity extends AppCompatActivity implements SmsListener {
    private EditText verifyPin;
    private Button btnVerify;
    private ImageView backImage;
    private TextView resend;
    private TextView mobileNumberTV,changeNumberTv;
    String userId;


    SmsReceiver smsReceiver = new SmsReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_otp_verification);

        verifyPin = findViewById(R.id.verifyPin_edittext);
        btnVerify = findViewById(R.id.verifyPin_btn);
        resend = findViewById(R.id.resend);
        mobileNumberTV = findViewById(R.id.mobileNumber);
        changeNumberTv = findViewById(R.id.changeNumber);

        timerInOtp();                          // time count dowan of otp

        mobileNumberTV.setText(HighwayPrefs.getString(this,Constants.USERMOBILE));

        smsReceiver.bindListener(this);

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyPinOperation(); // otp option perform
            }
        });



        changeNumberTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MobileOtpVerificationActivity.this,LoginActivityForCustomer.class);
                startActivity(intent);
                finish();
            }
        });

    }


    public void timerInOtp() {

        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                resend.setEnabled(true);
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
                            if (TextUtils.isEmpty(response.body().getUser().getName())) {
                                Intent intent = new Intent(MobileOtpVerificationActivity.this, RegistrationDetailsActivity.class);
                                userId = response.body().getUser().getUserId();
                                HighwayPrefs.putString(getApplicationContext(), Constants.ID, userId);

                                startActivity(intent);
                                finish();
                            } else {
                                gotoDashboardAfterLogin(response.body());

                               /* Intent intent = new Intent(MobileOtpVerificationActivity.this, DashBoardActivity.class);

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
                                finish();*/
                            }
                        }else{
                            Toast.makeText(MobileOtpVerificationActivity.this, "Invalid OTP", Toast.LENGTH_SHORT).show();

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

    private void gotoDashboardAfterLogin(VerifyOtpResponse verifyOtpResponse) {
        Intent intent = new Intent(MobileOtpVerificationActivity.this, DashBoardActivity.class);

        HighwayPrefs.putBoolean(getApplicationContext(), Constants.LOGGED_IN, true);
        HighwayPrefs.putString(getApplicationContext(), Constants.ID, verifyOtpResponse.getUser().getUserId());
        HighwayPrefs.putString(getApplicationContext(), Constants.NAME, verifyOtpResponse.getUser().getName());
        HighwayPrefs.putString(getApplicationContext(), Constants.USERMOBILE, verifyOtpResponse.getUser().getMobile());
        HighwayPrefs.putString(getApplicationContext(), Constants.User_statuss, verifyOtpResponse.getUser().getUserStatus());

        HighwayPrefs.putString(getApplicationContext(), Constants.IMAGE, verifyOtpResponse.getUser().getImage());
        HighwayPrefs.putString(getApplicationContext(), Constants.EMAIL, verifyOtpResponse.getUser().getEmail());
        HighwayPrefs.putString(getApplicationContext(), Constants.GENDER, verifyOtpResponse.getUser().getGender());
        HighwayPrefs.putString(getApplicationContext(), Constants.ROLEID, verifyOtpResponse.getUser().getRoleId());
        HighwayPrefs.putString(getApplicationContext(), Constants.ADDRESS, verifyOtpResponse.getUser().getAddress());

        startActivity(intent);
        Toast.makeText(MobileOtpVerificationActivity.this, "Wlcm to Highway ", Toast.LENGTH_SHORT).show();
        finish();
    }

    // onBacked pressed
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            if (smsReceiver!=null)
                unregisterReceiver(smsReceiver);
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

    @Override
    public void messageReceived(String messageText) {
        verifyPin.setText(messageText);
        verifyPinOperation();

    }
}
