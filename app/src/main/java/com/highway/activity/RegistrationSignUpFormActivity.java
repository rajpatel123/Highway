package com.highway.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.highway.R;

import java.util.Calendar;

import modelclass.RegistrationSignUpRequest;
import modelclass.RegistrationSignUpResponse;
import retrofit.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.HighwayPreface;
import utils.Utils;

public class RegistrationSignUpFormActivity extends AppCompatActivity {

    private ImageView regBackArrow, signUp_Profile_image, calenderDatePicker;
    private EditText name, dobDate, email;
    private RadioButton userMale, userFemale, userDiver, userCustomer;
    private String gender;
    private String userRole;
    private Button signUp;

    private RadioGroup radiogroup_Gender;
    private RadioGroup radiogroup_User;

    String name_User, email_User, dobDate_User, userId;
    private DatePickerDialog datePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_signup_form);

        regBackArrow = findViewById(R.id.registration_Back_Arrow);
        signUp_Profile_image = findViewById(R.id.reg_faculty_image);
        calenderDatePicker = findViewById(R.id.calenderPicker);
        dobDate = findViewById(R.id.user_dob);
        // calenderDatePicker.setEnabled(false);
        name = findViewById(R.id.user_name);
        email = findViewById(R.id.user_email);
        userMale = findViewById(R.id.radio_Male);
        userFemale = findViewById(R.id.radio_female);
        userDiver = findViewById(R.id.radio_Driver);
        userCustomer = findViewById(R.id.radio_Customer);
        signUp = findViewById(R.id.SignUpButton);

        radiogroup_Gender = (RadioGroup) findViewById(R.id.radiogroup_Gender);
        radiogroup_User = (RadioGroup) findViewById(R.id.radiogroup_User);

        calenderPickOperation();         //  calender picker
        radioGenderGroupOperation();    // gender group operation
        backArrowOperation();           // back arrow operation

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registrationAndValidateSignUpOpertion();
            }
        });
    }

    public void calenderPickOperation() {
        calenderDatePicker.setOnClickListener(new View.OnClickListener() {
            private int mYear, mMonth, mDay;// mHour, mMinute;

            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(RegistrationSignUpFormActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                        //dobDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        dobDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }


    public void radioGenderGroupOperation() {
        radiogroup_Gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton genderrg = (RadioButton) group.findViewById(checkedId);
                if (null != genderrg) {
                    gender = genderrg.getText().toString();
                    Toast.makeText(RegistrationSignUpFormActivity.this, genderrg.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void onUserRadioButtonClicked(View v) {
        boolean checked = ((RadioButton) v).isChecked();
        switch (v.getId()) {

            case R.id.radio_Driver:
                if (checked)
                    userDiver.setText("Driver");
                userRole = userDiver.getText().toString().trim();
                break;

            case R.id.radio_Customer:
                if (checked)

                    userCustomer.setText("Customer");
                userRole = userCustomer.getText().toString().trim();
                break;
        }
    }


    public void backArrowOperation() {
        regBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(RegistrationSignUpFormActivity.this, OTP_VerificationActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void registrationAndValidateSignUpOpertion() {

        if (inputValidation()) {

            RegistrationSignUpRequest registrationSignUpRequest = new RegistrationSignUpRequest();

            registrationSignUpRequest.setName(name_User);
            registrationSignUpRequest.setEmail(email_User);
            registrationSignUpRequest.setDob(dobDate_User);
            registrationSignUpRequest.setGender(gender);
            registrationSignUpRequest.setRole(userRole);
            registrationSignUpRequest.setId(userId);

            Utils.showProgressDialog(this);

            RestClient.registrationSignUp(registrationSignUpRequest, new Callback<RegistrationSignUpResponse>() {
                @Override
                public void onResponse(Call<RegistrationSignUpResponse> call, Response<RegistrationSignUpResponse> response) {

                    Utils.dismissProgressDialog();

                    if (response.body() != null && !TextUtils.isEmpty(response.body().getUserStatus())) {
                        if (response.body().getUserStatus().equalsIgnoreCase("1")){
                            if (response.body().getRole().equalsIgnoreCase("Driver")){
                                Intent i = new Intent(RegistrationSignUpFormActivity.this,DriveryLicenceActivity.class);
                                startActivity(i);
                                Toast.makeText(RegistrationSignUpFormActivity.this, "You are a driver pls  enter your details ", Toast.LENGTH_SHORT).show();
                            }else if (response.body().getRole().equalsIgnoreCase("Customer")){
                                Intent i = new Intent(RegistrationSignUpFormActivity.this, CustomerAdharcardId.class);
                                startActivity(i);
                                Toast.makeText(RegistrationSignUpFormActivity.this, "You are a Customer pls enter your details", Toast.LENGTH_SHORT).show();
                            }
                        }else if (response.body().getStatus()==false){
                            Toast.makeText(RegistrationSignUpFormActivity.this, "Sign up Failed", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(RegistrationSignUpFormActivity.this, "Pls Enter your details", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<RegistrationSignUpResponse> call, Throwable t) {

                    Toast.makeText(RegistrationSignUpFormActivity.this, "sign up Failure", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    // email validation operation perform
    public boolean inputValidation() {

        name_User = name.getText().toString().trim();
        email_User = email.getText().toString().trim();
        dobDate_User = dobDate.getText().toString().trim();
        userId = HighwayPreface.getString(getApplicationContext(), "id");

        if (name_User.isEmpty()) {
            name.setError("enter a valid email address");
            return false;
        } else {
            name.setError(null);
        }

        if (email_User.isEmpty()) {
            email.setError("enter a valid email address");
            return false;
        } else {
            email.setError(null);
        }

        if (!validEmai(email)) {
            Toast.makeText(getApplicationContext(), "Enter valid email address", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (dobDate_User.isEmpty()) {
            dobDate.setError("enter a valid D.O.B");
            return false;
        } else {
            dobDate.setError(null);
        }

        return true;
    }

    private boolean validEmai(EditText email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"; // onClick of button perform this simplest code.

        if (email.getText().toString().matches(emailPattern)) {
            return true;
        } else {
            return false;
        }
    }
}

