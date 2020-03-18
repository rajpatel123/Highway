package com.highway.customer.customerActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.common.base.HighwayApplication;
import com.highway.commonretrofit.RestClient;
import com.highway.customer.customerAdapter.BookingVehicleInfoAdapter;
import com.highway.customer.customerModelClass.vehicleInfo.BookingVehicleInfoRequest;
import com.highway.customer.customerModelClass.vehicleInfo.BookingVehicleInfoResponse;
import com.highway.customer.customerModelClass.vehicleInfo.VehicleTypeInfo;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingInfoDetailsActivity extends AppCompatActivity {

    public Toolbar bookInfoToolbar;
    TextView done;
    public RecyclerView bookInfoRecycler;
    public BookingVehicleInfoAdapter bookingVehicleInfoAdapter;

    public BookingVehicleInfoResponse bookingVehicleInfoResponse;
    public String userId,vehicleTypeId;

    List<VehicleTypeInfo>vehicleTypeInfos =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_info_details);

        vehicleTypeId =  HighwayPrefs.getString(getApplicationContext(), "vechicleId");
        Log.i("vichelid",vehicleTypeId);
        initView();

    }

    public void initView() {

        bookInfoToolbar = findViewById(R.id.bookInfoToolbar);
        bookInfoRecycler = findViewById(R.id.BookingInfoRecycler);
        //done = findViewById(R.id.done);

        setSupportActionBar(bookInfoToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        getSupportActionBar().setTitle("Booking Info Details");
        bookInfoToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

       // clicklistiner();
        getInfo();

    }

    public void getInfo() {

        userId = HighwayPrefs.getString(getApplicationContext(), Constants.ID);
        BookingVehicleInfoRequest bookingVehicleInfoRequest = new BookingVehicleInfoRequest();
        bookingVehicleInfoRequest.setUserId(userId);
        bookingVehicleInfoRequest.setVehicleTypeId(HighwayApplication.getInstance().getVehicleType());
        bookingVehicleInfoRequest.setVehicleTypeId(vehicleTypeId);

        RestClient.getInfo(bookingVehicleInfoRequest, new Callback<BookingVehicleInfoResponse>() {
            @Override
            public void onResponse(Call<BookingVehicleInfoResponse> call, Response<BookingVehicleInfoResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus()) {

                        List<VehicleTypeInfo> vehicleTypeInfos = response.body().getVehicleTypeInfo();
                        bookingVehicleInfoAdapter = new BookingVehicleInfoAdapter(vehicleTypeInfos, getApplicationContext());
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        bookInfoRecycler.setLayoutManager(layoutManager);
                        bookInfoRecycler.setItemAnimator(new DefaultItemAnimator());
                        bookInfoRecycler.setAdapter(bookingVehicleInfoAdapter);
                    }
                }
                else{
                    Toast.makeText(BookingInfoDetailsActivity.this, "error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<BookingVehicleInfoResponse> call, Throwable t) {
                Toast.makeText(BookingInfoDetailsActivity.this, "Failure booking Vehicle info", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void clicklistiner() {
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //onBackPressed();
        Intent resultIntent = new Intent();
        resultIntent.putExtra("isVehicleInfo", false);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
        super.onOptionsItemSelected(item);
        return true;
    }


}
