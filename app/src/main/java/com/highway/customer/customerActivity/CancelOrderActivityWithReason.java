package com.highway.customer.customerActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.highway.R;

public class CancelOrderActivityWithReason extends AppCompatActivity {

    public Toolbar canToolbar;
    public RadioButton canReasonRdBtn1, canReasonRdBtn2, canReasonRdBtn3, canReasonRdBtn4, canReasonRdBtn5, canReasonRdBtn6, canReasonRdBtn7, canReasonRdBtn8;
    /*canResnRdBtn1WrngInappropriateVehicle, canResnRdBtn2MyReasonIsNotListed, canResnRdBtn3DriverAskedMeToCancel,
    canResnRdBtn4DriverIsUnprofession, canResnRdBtn5ChangedMyMind, canResnRdBtn6DriverSaidHeWillBeLate, canResnRdBtn7UnableToConnectDriver,
    canResnRdBtn8UnExpactedShorterArivalTime;*/
    public EditText canReasonEdtTxt;
    public Button canBtn;
    private String canReasonRadioId;
    public RadioGroup cancleReasonRadioGroup;
    public String selectedRadioValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcel_with_reason);

        initview();
        //cancleRadioGroSelection();
        cancleTripOPeration();

        setSupportActionBar(canToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        getSupportActionBar().setTitle("Cancle Reason");
    }

    public void initview() {
        canToolbar = findViewById(R.id.CanResonToolbar);
      /*  canReasonRdBtn1 = findViewById(R.id.radioButton1);
        canReasonRdBtn2 = findViewById(R.id.radioButton2);
        canReasonRdBtn3 = findViewById(R.id.radioButton3);
        canReasonRdBtn4 = findViewById(R.id.radioButton4);
        canReasonRdBtn5 = findViewById(R.id.radioButton5);
        canReasonRdBtn6 = findViewById(R.id.radioButton6);
        canReasonRdBtn7 = findViewById(R.id.radioButton7);
        canReasonRdBtn8 = findViewById(R.id.radioButton8);*/
        cancleReasonRadioGroup = findViewById(R.id.cancleReasonRadioGroup);
        canReasonEdtTxt = findViewById(R.id.CanResnEdtTxt);
        canBtn = findViewById(R.id.BtnOrderCancle);
    }

// 1St method
//    public void canReasonRdBtnOperation(View view){
//        boolean checked = ((RadioButton) view).isChecked();
//        switch (view.getId()) {
//            case R.id.radioButton1:
//                if (checked)
//                  //  canReasonRdBtn1.setText("WrongInappropriateVehicle");
//                canReasonRadioId = canReasonRdBtn1.getText().toString().trim();
//                Toast.makeText(this, canReasonRdBtn1.getText().toString(), Toast.LENGTH_SHORT).show();
//                break;
//
//            case R.id.radioButton2:
//                if (checked)
//                   // canReasonRdBtn2.setText("MyReasonIsNotListed");
//                canReasonRadioId = canReasonRdBtn2.getText().toString().trim();
//                Toast.makeText(this, canReasonRdBtn2.getText().toString(), Toast.LENGTH_SHORT).show();
//                break;
//
//
//            case R.id.radioButton3:
//                if (checked)
//                   // canReasonRdBtn3.setText("DriverAskedMeToCancel");
//                canReasonRadioId = canReasonRdBtn3.getText().toString().trim();
//                Toast.makeText(this, canReasonRdBtn3.getText().toString(), Toast.LENGTH_SHORT).show();
//                break;
//
//            case R.id.radioButton4:
//                if (checked)
//                   // canReasonRdBtn4.setText("DriverIsUnprofession");
//                canReasonRadioId = canReasonRdBtn4.getText().toString().trim();
//                Toast.makeText(this, canReasonRdBtn4.getText().toString(), Toast.LENGTH_SHORT).show();
//                break;
//
//            case R.id.radioButton5:
//                if (checked)
//                   // canReasonRdBtn5.setText("ChangedMyMind");
//                canReasonRadioId = canReasonRdBtn5.getText().toString().trim();
//                Toast.makeText(this, canReasonRdBtn5.getText().toString(), Toast.LENGTH_SHORT).show();
//                break;
//
//            case R.id.radioButton6:
//                if (checked)
//                   // canReasonRdBtn6.setText("DriverSaidHeWillBeLate");
//                canReasonRadioId = canReasonRdBtn6.getText().toString().trim();
//                Toast.makeText(this, canReasonRdBtn6.getText().toString(), Toast.LENGTH_SHORT).show();
//                break;
//
//
//            case R.id.radioButton7:
//                if (checked)
//                  //  canReasonRdBtn7.setText("UnableToConnectDriver");
//                canReasonRadioId = canReasonRdBtn7.getText().toString().trim();
//                Toast.makeText(this, canReasonRdBtn7.getText().toString(), Toast.LENGTH_SHORT).show();
//                break;
//
//            case R.id.radioButton8:
//                if (checked)
//                  //  canReasonRdBtn8.setText("UnExpactedShorterArivalTime");
//                canReasonRadioId = canReasonRdBtn8.getText().toString().trim();
//                Toast.makeText(this, canReasonRdBtn8.getText().toString(), Toast.LENGTH_SHORT).show();
//                break;
//        }
//
//    }

    // 2nd method
    public void cancleRadioGroSelection() {
        cancleReasonRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton canclereasonRdId = (RadioButton) group.findViewById(checkedId);
                if (null != canclereasonRdId) {
                    selectedRadioValue = canclereasonRdId.getText().toString();
                    Toast.makeText(CancelOrderActivityWithReason.this, canclereasonRdId.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void cancleTripOPeration() {
        canBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CancleTripDetailsActivity.class);
                intent.putExtra("radioGroup1Selected", selectedRadioValue);
                startActivity(intent);
            }
        });

        //get another activity :
        /*Intent intent = getIntent();
        String selectedRadioValue = intent.getStringExtra("selectedRadioValue");*/
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }


}
