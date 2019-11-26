package com.highway.customer.customerActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.highway.R;
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.common.base.activity.MobileOtpVerificationActivity;
import com.highway.common.base.commonModel.login.LoginRegisterRequest;
import com.highway.common.base.commonModel.login.LoginRegisterResponse;
import com.highway.commonretrofit.RestClient;
import com.highway.millmodule.milluserActivity.MillerLoginActivity;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;
import com.highway.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerLoginActivity extends AppCompatActivity {

    private EditText CustomerPhoneNo;
    private Button btnSendOtp;
    String phone_number;
    private int backpress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        CustomerPhoneNo = findViewById(R.id.edtTxtMobNo);
        btnSendOtp = findViewById(R.id.btnSendOtp);


        btnSendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validationLoginUser();
            }
        });

    }

    private boolean validateInput() {
        boolean check = true;

        phone_number = CustomerPhoneNo.getText().toString();

        if (phone_number.isEmpty() && CustomerPhoneNo.length()==10) {
            CustomerPhoneNo.setError(" enter a valid phone number ");
            check = false;
        } else {
            CustomerPhoneNo.setError(null);
            check = true;
        }

        return check;
    }

    public void validationLoginUser() {

        if (validateInput()){

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
                                Intent intent = new Intent(CustomerLoginActivity.this,MobileOtpVerificationActivity.class);
                                HighwayPrefs.putString(CustomerLoginActivity.this, Constants.USERMOBILE, phone_number);

                                /*HighwayPrefs.putString(CustomerLoginActivity.this,Constants.ID,"4");
                                HighwayPrefs.putString(CustomerLoginActivity.this,Constants.NAME,"Ramashish");
                                HighwayPrefs.putString(CustomerLoginActivity.this,Constants.CustomerEmail,"prit@gmail.com");
                                HighwayPrefs.putString(CustomerLoginActivity.this,Constants.MillerGender,"Male");
                                HighwayPrefs.putString(CustomerLoginActivity.this,Constants.ROLEID,"4");
                                HighwayPrefs.putBoolean(CustomerLoginActivity.this,Constants.User_statuss,true);*/

                                startActivity(intent);
                                finish();
                                Toast.makeText(CustomerLoginActivity.this, "Pls verify Otp  !", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<LoginRegisterResponse> call, Throwable t) {
                        Toast.makeText(CustomerLoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();

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
