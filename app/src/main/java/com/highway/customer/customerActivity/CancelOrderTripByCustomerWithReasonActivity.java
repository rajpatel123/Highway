package com.highway.customer.customerActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.common.base.HighwayApplication;
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.commonretrofit.RestClient;
import com.highway.customer.customerAdapter.CancelledTripReasonAdapter;
import com.highway.customer.customerModelClass.cancleTripModel.cancleReason.CancelTripReasonRequest;
import com.highway.customer.customerModelClass.cancleTripModel.cancleReason.CancelTripReasonResponse;
import com.highway.customer.customerModelClass.cancleTripModel.cancleReason.CancelTripReson;
import com.highway.customer.customerModelClass.cancleTripModel.cancleTrip.CancelTripByCustomerRequest;
import com.highway.customer.customerModelClass.cancleTripModel.cancleTrip.CancelTripByCustomerResponse;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CancelOrderTripByCustomerWithReasonActivity extends AppCompatActivity {

    private static final String TAG = "CancelOrderTripByCustomerWithReasonActivity";
    public Toolbar canToolbar;
    public EditText canReasonEdtTxt;
    public Button canBtn;
    public RadioGroup cancleReasonRadioGroup;
    public String selectedRadioValue;
    public String cmntRsnEdtTxt;
    public RecyclerView canRsnTypeRecyler;
    public CancelledTripReasonAdapter cancelledTripReasonAdapter;
    public CancelTripReasonResponse cancelTripReasonResponse;
    public String userId, bookId,bookTripIdCode,vTypeId;
    String cancelreasonid;
    Intent resultIntent;
    private String cancelResnId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcel_order_trip_with_reason);

        initview();
        clicklistener();
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
        showCanRsnTypeRV();
        getCanReasonData();

       // bookId = getIntent().getStringExtra("bookId");
        bookTripIdCode = getIntent().getStringExtra("bookTripIdCode");
        vTypeId = getIntent().getStringExtra("vTypeId");
        bookId= HighwayPrefs.getString(getApplicationContext(),"bookId");
        cancelreasonid = getIntent().getStringExtra("cancelReasonId");

    }


    public void showCanRsnTypeRV() {
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

                        List<CancelTripReson>cancelTripResons = response.body().getCancelTripReson();

                        cancelledTripReasonAdapter = new CancelledTripReasonAdapter(cancelTripResons,getApplicationContext());
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        canRsnTypeRecyler.setLayoutManager(layoutManager);
                        canRsnTypeRecyler.setItemAnimator(new DefaultItemAnimator());
                        canRsnTypeRecyler.setAdapter(cancelledTripReasonAdapter);

                        cancelledTripReasonAdapter.setData(new CancelledTripReasonAdapter.OnCancelReasonTypeSelect() {
                            @Override
                            public void onSelectedReasonType(String id, String type) {
                                cancelreasonid = id;
                                Log.i("reasonid",cancelreasonid);
                            }
                        });

                        cancelledTripReasonAdapter.notifyDataSetChanged();

                    }
                } else {
                    Toast.makeText(CancelOrderTripByCustomerWithReasonActivity.this, "getting cancle reason response Failde", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CancelTripReasonResponse> call, Throwable t) {
                Toast.makeText(CancelOrderTripByCustomerWithReasonActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean inputValidation() {

      cmntRsnEdtTxt = canReasonEdtTxt.getText().toString().trim();

      /*  if (cancelreasonid.isEmpty()){
            Toast.makeText(this, "Please select cancle reason", Toast.LENGTH_SHORT).show();
            return false;
        }*/
      return true;
    }


    public void clicklistener(){
        canBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelledTripOperation();

            }
        });
    }
    public void cancelledTripOperation() {
        if (inputValidation()) {

            // cmntRsnEdtTxt = canReasonEdtTxt.getText().toString().trim();

            CancelTripByCustomerRequest cancelTripByCustomerRequest = new CancelTripByCustomerRequest();
            cancelTripByCustomerRequest.setCancelBookId(HighwayApplication.getInstance().getCurrentTripId());
            cancelTripByCustomerRequest.setCancelReasonId(cancelreasonid);
            cancelTripByCustomerRequest.setCancelReasonComment(cmntRsnEdtTxt);
            userId = HighwayPrefs.getString(getApplicationContext(), Constants.ID);
            cancelTripByCustomerRequest.setUserId(userId);

            RestClient.getCancelledTripByCust(cancelTripByCustomerRequest, new Callback<CancelTripByCustomerResponse>() {
                @Override
                public void onResponse(Call<CancelTripByCustomerResponse> call, Response<CancelTripByCustomerResponse> response) {
                    if (response.body() != null) {
                        if (response.body().getStatus()) {

                            resultIntent = new Intent(getApplicationContext(), DashBoardActivity.class);
                            Toast.makeText(CancelOrderTripByCustomerWithReasonActivity.this, "booking Cancled success", Toast.LENGTH_SHORT).show();
                            startActivity(resultIntent);
                            finish();

                                 /* resultIntent = new Intent();
                                resultIntent.putExtra("isCancelled", true);
                                setResult(Activity.RESULT_OK, resultIntent);
                                finish();*/
                        }
                    }
                }

                @Override
                public void onFailure(Call<CancelTripByCustomerResponse> call, Throwable t) {
                    Toast.makeText(CancelOrderTripByCustomerWithReasonActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("isCancelled", false);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
        return super.onOptionsItemSelected(item);
    }


   /* // @Override
    public void onSelectedReasonType(String id, String type) {
       cancelResnId=id;
    }*/

}