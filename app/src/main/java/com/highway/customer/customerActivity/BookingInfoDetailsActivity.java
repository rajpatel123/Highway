package com.highway.customer.customerActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.highway.R;
import com.highway.customer.customerModelClass.bookingVehicleList.VInfo;

public class BookingInfoDetailsActivity extends AppCompatActivity {
    public Toolbar bookInfoToolbar;
    TextView bookVehicleNameTv, bookbookVehicleCapicityTv, bookVehicleSizeTv;
    ImageView bookVehileBookImg;
    TextView bookInfo1, bookInfo2, bookInfo3, bookInfo4, bookInfo5, bookInfo6, done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_info_details);

        initView();
        clickliostiner();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    public void initView() {

        bookInfoToolbar = findViewById(R.id.bookInfoToolbar);
        bookVehicleNameTv = findViewById(R.id.truckName);
        bookVehileBookImg = findViewById(R.id.vehicleImg);
        bookbookVehicleCapicityTv = findViewById(R.id.capacity);
        bookVehicleSizeTv = findViewById(R.id.sizeTV);
        bookInfo1 = findViewById(R.id.Bookinfo1);
        bookInfo2 = findViewById(R.id.Bookinfo2);
        bookInfo3 = findViewById(R.id.Bookinfo3);
        bookInfo4 = findViewById(R.id.Bookinfo4);
        bookInfo5 = findViewById(R.id.Bookinfo5);
        bookInfo6 = findViewById(R.id.Bookinfo6);
        done = findViewById(R.id.done);

        setSupportActionBar(bookInfoToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        getSupportActionBar().setTitle("Booking Info Details");



    }

    public void clickliostiner() {
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
