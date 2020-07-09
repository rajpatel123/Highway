package com.highway.common.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.highway.R;
import com.highway.customer.customerActivity.LoginActivityForCustomer;
import com.highway.customer.customerActivity.WelcomeActivityForCustomer;
import com.highway.drivermodule.driverActivity.WelcomeDriverActivity;
import com.highway.millUserModule.milluserActivity.WelcomeActivityForMiller;
import com.highway.ownerModule.vehicleOwnerActivities.WelcomeOwnerActivity;
import com.highway.utils.HighwayPrefs;

public class LoginOptionActivity extends AppCompatActivity {

    private TextView customer, driver;
    private Button  btnNext;
    private String userRoleId;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_option);

        initialView();
        clickListenerView();

    }

    public void initialView(){
        customer  = findViewById(R.id.customerTv);
        driver = findViewById(R.id.driverTv);

        //for button
        btnNext = findViewById(R.id.btnNext);

    }

    public void clickListenerView(){

        driver.setOnClickListener(v -> {
            userRoleId="1";
            updateSelectionView(driver);
        });

        customer.setOnClickListener(v -> {
            userRoleId = "2";
            updateSelectionView(customer);
        });


        btnNext.setOnClickListener(view -> {

            if (TextUtils.isEmpty(userRoleId)){
                Toast.makeText(LoginOptionActivity.this, "Pls select user role", Toast.LENGTH_SHORT).show();

            }else{
                switch (userRoleId){
                    case "1":
                        intent = new Intent(LoginOptionActivity.this, LoginActivityForCustomer.class);
                        //  intent.putExtra("driverRoleId", userRoleId);
                        HighwayPrefs.putString(getApplicationContext(),"driverRoleId",userRoleId);
                        startActivity(intent);
                        finish();
                        break;

                    case "2":
                        intent = new Intent(LoginOptionActivity.this, WelcomeDriverActivity.class);
                      //  intent.putExtra("driverRoleId", userRoleId);
                        HighwayPrefs.putString(getApplicationContext(),"driverRoleId",userRoleId);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        });
    }
    private void updateSelectionView(TextView selectedView) {
        customer.setBackgroundResource(R.drawable.rounded_user_type_selecter_white);
        driver.setBackgroundResource(R.drawable.rounded_user_type_selecter_white);

        selectedView.setBackgroundResource(R.drawable.rounded_user_type_selecter);
    }
}
