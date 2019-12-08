package com.highway.ownermodule.vehicleOwner.vehicleOwnerActivities;

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
import com.highway.utils.HighwayPrefs;
import com.highway.utils.Utils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.highway.utils.Constants.USERMOBILE;


public class LoginActivityForVehicleOwner extends AppCompatActivity {

    private EditText ownerPhoneNo;
    private Button btnOwnerOtp;
    String phone_number;
    private int backpress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_login);

        ownerPhoneNo = findViewById(R.id.edtTxtOwnerMobNo);
        btnOwnerOtp = findViewById(R.id.btnSendOwnerOtp);

        btnOwnerOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validationLoginOwner();
            }
        });
    }


    private boolean inputOwnerValidate() {
        boolean check = true;

        phone_number = ownerPhoneNo.getText().toString();

        if (phone_number.isEmpty() && ownerPhoneNo.length()!=10) {
            ownerPhoneNo.setError(" enter a valid phone number ");
            check = false;
        } else {
            ownerPhoneNo.setError(null);
            check = true;
        }
        return check;
    }


    public void validationLoginOwner() {

        if (inputOwnerValidate()){

            LoginRegisterRequest loginRegisterRequest = new LoginRegisterRequest();
            loginRegisterRequest.setMobile(phone_number);

            if (Utils.isInternetConnected(this)) {

                Utils.showProgressDialog(this);

                RestClient.loginUser(loginRegisterRequest, new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Utils.dismissProgressDialog();
                        if (response.body() != null) {
                            if (response.code() ==200) {
                                Intent intent = new Intent(LoginActivityForVehicleOwner.this, MobileOtpVerificationActivity.class);
                                HighwayPrefs.putString(LoginActivityForVehicleOwner.this, USERMOBILE, phone_number);
                                /*
                                HighwayPrefs.putString(LoginActivityForVehicleOwner.this, Constants.ID,"5");
                                HighwayPrefs.putString(LoginActivityForVehicleOwner.this,Constants.NAME,"Nitin");
                                HighwayPrefs.putString(LoginActivityForVehicleOwner.this,Constants.OwnerEmail,"prit@gmail.com");
                                HighwayPrefs.putString(LoginActivityForVehicleOwner.this,Constants.MillerGender,"Male");
                                HighwayPrefs.putString(LoginActivityForVehicleOwner.this,Constants.ROLEID,"5");
                                HighwayPrefs.putBoolean(LoginActivityForVehicleOwner.this,Constants.User_statuss,true);*/
                                startActivity(intent);
                                finish();

                                Toast.makeText(LoginActivityForVehicleOwner.this, "pls Verify Otp", Toast.LENGTH_SHORT).show();
                                /*Toast.makeText(LoginActivityForVehicleOwner.this, "Welcome Owner !", Toast.LENGTH_SHORT).show();*/
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(LoginActivityForVehicleOwner.this, "Login failed", Toast.LENGTH_SHORT).show();

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
