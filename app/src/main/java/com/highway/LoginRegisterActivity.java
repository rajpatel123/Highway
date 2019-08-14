package com.highway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import modelclass.LoginRequest;
import modelclass.LoginResponse;
import retrofit.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.HighwayPreface;
import utils.Utils;

public class LoginRegisterActivity extends AppCompatActivity {

    private EditText userphoneno, userpassword;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        userphoneno = findViewById(R.id.login_phone_number);
        userpassword = findViewById(R.id.login_password);
        next = findViewById(R.id.btn_Next);


        loginOperation();

    }

    public void loginOperation() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validationLoginUser();

            }
        });

    }

    public void validationLoginUser() {

        String phone_number = userphoneno.getText().toString();
        String password = userpassword.getText().toString();

        boolean check = true;

        if (phone_number.isEmpty() || userphoneno.length() < 4 || userphoneno.length() > 10) {
            userphoneno.setError(" enter a valid phone number ");
            check = false;
        } else {
            userphoneno.setError(null);
        }

        if (password.isEmpty() || userpassword.length() < 5 || userpassword.length() > 8) {
            userpassword.setError("enter valid password 5 to 8 characters");
            check = false;
        } else {
            userpassword.setError(null);
        }

        if (check) {
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setMobile(phone_number);
            loginRequest.setPassword(password);

            Utils.showProgressDialog(this);

            RestClient.loginUser(loginRequest, new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    Utils.dismissProgressDialog();

                    if (response.body() != null) {
                        if (response.body().getUserStatus().equalsIgnoreCase("1")) {//good job
                            Intent intent = new Intent(LoginRegisterActivity.this, MainActivity.class);
                            Toast.makeText(LoginRegisterActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            finish();
                        }else if(response.body().getUserStatus().equalsIgnoreCase("0")){

                            String  phone_number =response.body().getMobile();
                            String id = response.body().getId();

                            HighwayPreface.putString(getApplicationContext(),"phone_number",phone_number);
                            HighwayPreface.putString(getApplicationContext(),"id",id);

                            Intent intent = new Intent(LoginRegisterActivity.this, OTP_VerificationActivity.class);
                            startActivity(intent);
                            finish();

                            Toast.makeText(LoginRegisterActivity.this, "Please OTP Verify", Toast.LENGTH_SHORT).show();

                        }

                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {

                    Toast.makeText(LoginRegisterActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();

                }
            });

        }

    }
}
