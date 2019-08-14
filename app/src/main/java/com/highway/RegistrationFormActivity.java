package com.highway;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

public class RegistrationFormActivity extends AppCompatActivity {

    private ImageView regBackArrow,reg_Profile_image,calender;
    private EditText Name,dob,email;
    private RadioButton male,female,diver,poolar,customer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        regBackArrow = findViewById(R.id.registration_Back_Arrow);
        reg_Profile_image = findViewById(R.id.reg_faculty_image);
        calender = findViewById(R.id.calenderPicker);

    }
}
