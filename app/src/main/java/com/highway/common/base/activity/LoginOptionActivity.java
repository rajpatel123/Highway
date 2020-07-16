package com.highway.common.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.highway.R;
import com.highway.customer.customerActivity.LoginActivityForCustomer;
import com.highway.drivermodule.driverActivity.LoginActivityForDriver;
import com.highway.drivermodule.driverActivity.WelcomeDriverActivity;

public class LoginOptionActivity extends AppCompatActivity {

    private TextView customer, driver;
    private Button btnNext;
    private String userRoleId="2";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_option);

        initialView();
        clickListenerView();

    }

    public void initialView() {
        customer = findViewById(R.id.customerTv);
        driver = findViewById(R.id.driverTv);

        //for button
        btnNext = findViewById(R.id.btnNext);

    }

    public void clickListenerView() {

        driver.setOnClickListener(v -> {
            userRoleId = "1";
            updateSelectionView(driver);
        });

        customer.setOnClickListener(v -> {
            userRoleId = "2";

            updateSelectionView(customer);
        });


        btnNext.setOnClickListener(view -> {
            switch (userRoleId) {
                case "1":
                    intent = new Intent(LoginOptionActivity.this, LoginActivityForDriver.class);
                     intent.putExtra("driverRoleId", userRoleId);
                    //HighwayPrefs.putString(getApplicationContext(),"driverRoleId",userRoleId);
                    startActivity(intent);
                    finish();
                    break;

                case "2":
                    intent = new Intent(LoginOptionActivity.this, LoginActivityForCustomer.class);
                      intent.putExtra("driverRoleId", userRoleId);
                    //HighwayPrefs.putString(getApplicationContext(),"driverRoleId",userRoleId);
                    startActivity(intent);
                    finish();
                    break;
            }
        });
    }

    private void updateSelectionView(TextView selectedView) {
        customer.setBackgroundResource(R.drawable.rounded_user_type_selecter_white);
        driver.setBackgroundResource(R.drawable.rounded_user_type_selecter_white);

        selectedView.setBackgroundResource(R.drawable.rounded_user_type_selecter);
    }
}
