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
import com.highway.customer.customerActivity.WelcomeActivityForCustomer;
import com.highway.drivermodule.driverActivity.WelcomeDriverActivity;
import com.highway.millUserModule.milluserActivity.WelcomeActivityForMiller;
import com.highway.ownerModule.vehicleOwnerActivities.WelcomeOwnerActivity;
import com.highway.utils.HighwayPrefs;

public class LoginOptionActivity extends AppCompatActivity {

    private LinearLayout customer, driver, miller, owner;
    private ImageView imgCustomer, imgDriver,imgMiller, imgOwner;
    private TextView tvCustomer,tvMiller,tvOwner,tvDriver;
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
        customer  = findViewById(R.id.LinearCustomer);
        driver = findViewById(R.id.LinearDriver);
        miller = findViewById(R.id.LinearMiller);
        owner = findViewById(R.id.LnearOwner);
        // for img
        imgCustomer = findViewById(R.id.ImgCustomer);
        imgMiller = findViewById(R.id.ImgMiller);
        imgOwner = findViewById(R.id.ImgOwner);
        imgDriver = findViewById(R.id.ImgDriver);
        // for text
        tvCustomer = findViewById(R.id.txtCustomer);
        tvMiller = findViewById(R.id.txtMiller);
        tvOwner = findViewById(R.id.txtOwner);
        tvDriver = findViewById(R.id.txtDriver);
        //for button
        btnNext = findViewById(R.id.btnNext);

    }

    public void clickListenerView(){
        miller.setOnClickListener(v -> {
            userRoleId="2";
            updateSelectionView(miller);
        });

        driver.setOnClickListener(v -> {
            userRoleId="3";
            updateSelectionView(driver);
        });

        customer.setOnClickListener(v -> {
            userRoleId = "4";
            updateSelectionView(customer);
        });

        owner.setOnClickListener(v -> {
            userRoleId= "5";
            updateSelectionView(owner);
        });

        btnNext.setOnClickListener(view -> {

            if (TextUtils.isEmpty(userRoleId)){
                Toast.makeText(LoginOptionActivity.this, "Pls select user role", Toast.LENGTH_SHORT).show();

            }else{
                switch (userRoleId){
                    case "2":
                        intent = new Intent(LoginOptionActivity.this, WelcomeActivityForMiller.class);
                        HighwayPrefs.putString(getApplicationContext(), "millerRoleId" ,userRoleId);
                        startActivity(intent);
                        finish();
                        break;

                    case "3":
                        intent = new Intent(LoginOptionActivity.this, WelcomeDriverActivity.class);
                      //  intent.putExtra("driverRoleId", userRoleId);
                        HighwayPrefs.putString(getApplicationContext(),"driverRoleId",userRoleId);
                        startActivity(intent);
                        finish();
                        break;

                    case "4":
                        intent = new Intent(LoginOptionActivity.this, WelcomeActivityForCustomer.class);
                       // intent.putExtra("customerRoleId", userRoleId);
                        HighwayPrefs.putString(getApplicationContext(),"customerRoleId",userRoleId);
                        startActivity(intent);
                        finish();
                        break;

                    case "5":
                        intent = new Intent(LoginOptionActivity.this, WelcomeOwnerActivity.class);
                     //   intent.putExtra("ownerRoleId", userRoleId);
                        HighwayPrefs.putString(getApplicationContext(),"ownerRoleId",userRoleId);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        });
    }
    private void updateSelectionView(LinearLayout selectedView) {
        customer.setBackgroundResource(R.drawable.cardview_border_default);
        driver.setBackgroundResource(R.drawable.cardview_border_default);
        miller.setBackgroundResource(R.drawable.cardview_border_default);
        owner.setBackgroundResource(R.drawable.cardview_border_default);
        selectedView.setBackgroundResource(R.drawable.cardview_border_selected);
    }
}
