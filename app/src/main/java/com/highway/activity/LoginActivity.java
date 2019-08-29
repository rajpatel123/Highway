package com.highway.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.highway.R;

import modelclass.LoginRequest;
import modelclass.LoginResponse;
import retrofit.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.Constants;
import utils.HighwayPreface;
import utils.Utils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userPhoneNo, userPassword;
    private Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        userPhoneNo = findViewById(R.id.login_phone_number);
        userPassword = findViewById(R.id.login_password);
        nextBtn = findViewById(R.id.btn_Next);

        nextBtn.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Next:
                validationLoginUser();
                break;
            default:
        }

    }

    private boolean validateInput(String phone_number, String password) {
        boolean check = false;
        if (phone_number.isEmpty() || userPhoneNo.length() < 4 || userPhoneNo.length() > 10) {
            userPhoneNo.setError(" enter a valid phone number ");
            check = false;
        } else {
            userPhoneNo.setError(null);
            check = true;
        }

        if (password.isEmpty() || userPassword.length() < 4 || userPassword.length() > 6) {
            userPassword.setError("enter valid password 4 to 6 characters");
            check = false;
        } else {
            userPassword.setError(null);
            check = true;
        }

        return check;
    }

    public void validationLoginUser() {

        String phone_number = userPhoneNo.getText().toString();
        String password = userPassword.getText().toString();

        if (validateInput(phone_number, password)) {
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setMobile(phone_number);
            loginRequest.setPassword(password);

            Utils.showProgressDialog(this);

            RestClient.loginUser(loginRequest, new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                    Utils.dismissProgressDialog();
                    LoginResponse loginResponse = response.body();

                    if (loginResponse != null && !TextUtils.isEmpty(loginResponse.getUserStatus())) {
                        if (loginResponse.getUserStatus().equalsIgnoreCase("1")) {//good job

                            HighwayPreface.putBoolean(LoginActivity.this, Constants.LOGGED_IN, true);
                            HighwayPreface.putString(LoginActivity.this, Constants.NAME, loginResponse.getName());
                            HighwayPreface.putString(LoginActivity.this, Constants.USERMOBNO, loginResponse.getMobile());

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                            finish();
                        } else if (loginResponse.getUserStatus().equalsIgnoreCase("0")) {

                            Intent intent = new Intent(LoginActivity.this, OTP_VerificationActivity.class);
                            startActivity(intent);

                            String phone_number = response.body().getMobile();
                            String id = response.body().getId();

                            HighwayPreface.putString(getApplicationContext(), "phone_number", phone_number);
                            HighwayPreface.putString(getApplicationContext(), "id", id);

                            Toast.makeText(LoginActivity.this, "Please OTP Verify", Toast.LENGTH_SHORT).show();

                            Toast.makeText(LoginActivity.this, "you are not Register", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {

                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();

                }
            });
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


    // for password
    public void ShowHidePass(View view) {

    /*    if (view.getId() == R.id.show_pass_btn) {

            if (userPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                ((ImageView) (view)).setImageResource(R.drawable.ic_visibility_off_black_24dp);

                //Show Password
                userPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                ((ImageView) (view)).setImageResource(R.drawable.ic_visibility_black_24dp);

                //Hide Password
                userPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }*/
    }


}
