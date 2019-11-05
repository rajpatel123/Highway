package com.highwayjprproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.highwayjprproject.R;

public class LoginOptionActivity extends AppCompatActivity {
    private LinearLayout customer, driver, miller, owner;
    private ImageView imgCustomer, imgDriver,imgMiller, imgOwner;
    private TextView tvCustomer,tvMiller,tvOwner,tvDriver;
    private Button  btnNext;
    private String userRole;
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
        miller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRole="2";
                updateSelectionView(miller);
            }
        });

        driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRole="3";
                updateSelectionView(driver);

            }
        });

        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRole = "4";
                updateSelectionView(customer);
            }
        });

        owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRole= "5";
                updateSelectionView(owner);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(userRole)){
                    Toast.makeText(LoginOptionActivity.this, "Pls select user role", Toast.LENGTH_SHORT).show();

                }else{
                    switch (userRole){
                        case "2":
                            intent = new Intent(LoginOptionActivity.this,MillerLoginActivity.class);
                            startActivity(intent);
                            finish();
                        case "3":
                            intent = new Intent(LoginOptionActivity.this,DriverLoginActivity.class);
                            startActivity(intent);
                            finish();
                        case "4":
                            intent = new Intent(LoginOptionActivity.this,CustomerLoginActivity.class);
                            startActivity(intent);
                            finish();
                        case "5":
                            intent = new Intent(LoginOptionActivity.this,OwnerLoginActivity.class);
                            startActivity(intent);
                            finish();
                    }
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
