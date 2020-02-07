package com.highway.customer.customerActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.commonretrofit.RestClient;
import com.highway.customer.customerAdapter.CancelledTripReasonAdapter;
import com.highway.customer.customerModelClass.cancleTripModel.cancleReason.CancelTripReasonRequest;
import com.highway.customer.customerModelClass.cancleTripModel.cancleReason.CancelTripReasonResponse;
import com.highway.customer.customerModelClass.cancleTripModel.cancleTrip.CancelTripByCustomerRequest;
import com.highway.customer.customerModelClass.cancleTripModel.cancleTrip.CancelTripByCustomerResponse;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CancelOrderTripActivityWithReason extends AppCompatActivity implements CancelledTripReasonAdapter.OnCancelReasonTypeSelect {

    public Toolbar canToolbar;
    public EditText canReasonEdtTxt;
    public Button canBtn;
    public String canReasonRadioId;
    public RadioGroup cancleReasonRadioGroup;
    public RadioButton canRsnRdBtn;
    public String selectedRadioValue;
    public String cmntRsnEdtTxt;
    public RecyclerView canRsnTypeRecyler;
    public CancelledTripReasonAdapter cancelledTripReasonAdapter;
    public CancelTripReasonResponse cancelTripReasonResponse;
    public String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcel_order_trip_with_reason);

        initview();
    }

    public void initview() {
        canToolbar = findViewById(R.id.CanResonToolbar);
        canRsnTypeRecyler = findViewById(R.id.cancleReasonRecyc);
        canReasonEdtTxt = findViewById(R.id.CanResnEdtTxt);
        canBtn = findViewById(R.id.BtnOrderCancle);

        setSupportActionBar(canToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        getSupportActionBar().setTitle("Cancle Reason");

        inputValidation();
       // cancleRadioGroSelection();
        //cancleTripOPeration();
        showCanRsnTypeRV();
        getCanReasonData();
        //cancelledTripOperation();

    }

    public boolean inputValidation() {
        cmntRsnEdtTxt = canReasonEdtTxt.getText().toString().trim();

        if (cmntRsnEdtTxt.isEmpty()) {
            canReasonEdtTxt.setError("pls enter the reason comment");
            return false;
        } else {
            canReasonEdtTxt.setError(null);
        }
        return true;
    }

    // 2nd method
    public void cancleRadioGroSelection() {
        cancleReasonRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton canclereasonRdId = (RadioButton) group.findViewById(checkedId);
                if (null != canclereasonRdId) {
                    selectedRadioValue = canclereasonRdId.getText().toString();
                    Toast.makeText(CancelOrderTripActivityWithReason.this, canclereasonRdId.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showCanRsnTypeRV() {
        cancelledTripReasonAdapter = new CancelledTripReasonAdapter(cancelTripReasonResponse, getApplicationContext(), this::onSelectedReasonType); // aaccording to sir initialize above ...................
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        canRsnTypeRecyler.setLayoutManager(layoutManager);
        canRsnTypeRecyler.setItemAnimator(new DefaultItemAnimator());
        canRsnTypeRecyler.setAdapter(cancelledTripReasonAdapter);
    }

    public void getCanReasonData() {
        CancelTripReasonRequest cancelTripReasonRequest = new CancelTripReasonRequest();
        userId = HighwayPrefs.getString(getApplicationContext(), Constants.ID);
        cancelTripReasonRequest.setUserId(userId);
        RestClient.getCancleReason(cancelTripReasonRequest, new Callback<CancelTripReasonResponse>() {
            @Override
            public void onResponse(Call<CancelTripReasonResponse> call, Response<CancelTripReasonResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus()) {
                        cancelTripReasonResponse = response.body();
                        cancelledTripReasonAdapter.setData(cancelTripReasonResponse);
                        cancelledTripReasonAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(CancelOrderTripActivityWithReason.this, "getting cancle reason response Failde", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CancelTripReasonResponse> call, Throwable t) {
                Toast.makeText(CancelOrderTripActivityWithReason.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void cancelledTripOperation() {

        if (inputValidation()) {

            CancelTripByCustomerRequest cancelTripByCustomerRequest = new CancelTripByCustomerRequest();
            cancelTripByCustomerRequest.setCancelBookId("tripId");
            cancelTripByCustomerRequest.setCancelReasonId(selectedRadioValue);
            cancelTripByCustomerRequest.setCancelReasonComment(cmntRsnEdtTxt);
            userId = HighwayPrefs.getString(getApplicationContext(), Constants.ID);
            cancelTripByCustomerRequest.setUserId(userId);

            RestClient.getCancelledTripByCust(cancelTripByCustomerRequest, new Callback<CancelTripByCustomerResponse>() {
                @Override
                public void onResponse(Call<CancelTripByCustomerResponse> call, Response<CancelTripByCustomerResponse> response) {
                    if (response.body() != null) {
                        if (response.body().getStatus()) {
                            Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
                            // Daught
                            HighwayPrefs.getString(getApplicationContext(), Constants.ID);
                            startActivity(intent);
                            finish();
                        }
                    }
                }

                @Override
                public void onFailure(Call<CancelTripByCustomerResponse> call, Throwable t) {
                    Toast.makeText(CancelOrderTripActivityWithReason.this, "Failure", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSelectedReasonType(String id, String type) {
        Intent data = new Intent();
        data.putExtra("id", id);
        data.putExtra("type", type);
        setResult(RESULT_OK, data);
        finish();

    }
}
