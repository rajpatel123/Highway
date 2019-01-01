package com.videoapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.videoapp.R;
import com.videoapp.model.CommonApiResponse;
import com.videoapp.retrofit.RestClient;
import com.videoapp.utill.AppUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;


public class SignupActivity extends AppCompatActivity {
    EditText username,userpassword,useremail,usercontact;
    Button btnRegister;
    TextView link_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username=findViewById(R.id.regUserName);
        link_login=findViewById(R.id.link_login);
        userpassword=findViewById(R.id.regUserpassword);
        useremail=findViewById(R.id.regUserEmail);
        usercontact=findViewById(R.id.regUserContactNo);
        btnRegister=findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Resigaternewuser();
            }
        });
        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        });
    }
    private void Resigaternewuser(){
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), username.getText().toString().trim());
        RequestBody email = RequestBody.create(MediaType.parse("text/plain"), useremail.getText().toString().trim());
        RequestBody mobile = RequestBody.create(MediaType.parse("text/plain"), usercontact.getText().toString().trim());
        RequestBody password = RequestBody.create(MediaType.parse("text/plain"), userpassword.getText().toString().trim());

        AppUtils.showProgressDialog(getApplicationContext(),"PLease wait...");
        RestClient.registerUser(name, email, mobile, password, new Callback<CommonApiResponse>() {
            @Override
            public void onResponse(Call<CommonApiResponse> call, retrofit2.Response<CommonApiResponse> response) {
              AppUtils.dismissProgressDialog();
                if (response.body()!=null){
                    if (response.body().getStatus().equalsIgnoreCase("1")){
                        AppUtils.displayToast(getApplicationContext(),"Successfuly registered");
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<CommonApiResponse> call, Throwable t) {
                AppUtils.dismissProgressDialog();
                AppUtils.displayToast(getApplicationContext(),"Unable to register, please try again later");

            }
        });
        link_login=findViewById(R.id.link_login);

        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login=new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(login);
            }
        });



    }
}
