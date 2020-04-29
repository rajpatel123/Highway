package com.highway.customer.customerActivity;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.libraries.places.api.Places;
import com.highway.R;
import com.highway.broadCastReceiver.MySenderBroadCast;
import com.highway.common.base.HighwayApplication;
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.common.base.commonModel.bookingHTrip.BookingHTripResponse;
import com.highway.common.base.firebaseService.NotificationPushData;
import com.highway.commonretrofit.RestClient;
import com.highway.customer.customerModelClass.bookingVehicleList.BookingVehicleListResponse;
import com.highway.customer.customerModelClass.customerInvoice.CustomerInvoice;
import com.highway.customer.customerModelClass.customerInvoice.CustomerInvoiceReq;
import com.highway.customer.customerModelClass.customerInvoice.CustomerInvoiceResp;
import com.highway.customer.helper.FetchURL;
import com.highway.customer.helper.TaskLoadedCallback;
import com.highway.drivermodule.drivermodels.TripStatus;
import com.highway.utils.BaseUtil;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;
import com.highway.utils.Utils;

import org.json.JSONObject;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CancelTripDetailsForCustomerActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, TaskLoadedCallback, View.OnClickListener  {


    TextView bookingIdCode, totalAmount, travelTime, peekHourCharges, nightFare,tax, discount, rentalNormalPrice, rentalTotalDistance, rentalExtraHrKmPrice,
            rentalTravelTime, rentalHours, outstationDistanceFare, outstationDriverBeta, outstationRoundSingle, outstationNoOfDays, startDate, endDate,
            paymentMode, bookingId,fixed, walletDetection,distancePrice,outstationDistanceTravelled,payAbleAmout;

    TextView distance, start_time, end_time;
    TextView sourceTV, destTV,driverNameTV,vehicleNameTV;

    String userId, sourceName, destName, gdTypeId, goodsType, userRecvNO, userMobileNO, bookTripIdCode, bookId, vehicleTypeId, bookVehicleName, rejTV, acptTripTv;
    String mobileNo, sourceTV1, destTV1, driverName1, fareValue1, completeDate1, pickUp1, dropTime1, vehicleNumber1,fairCharge;
    LinearLayout paymentModeLayout, layout_normal_flow, layout_rental_flow, layout_outstation_flow, discountLayout, sourceLL, destLL;

    private String name, role, vehicleName, vehicleNumber, faireChargeVal,
            status, tripType, tripStartDate, tripEndDate, pickUpTime, dropTime;

    NestedScrollView nestedScrollView;

    Button btnConfirmPayment,done;

    int yy, mm, dd;

    String totDistance, tripId, getBookTripIdCode, getBookId, userName, userMobNo;
    DashBoardActivity mActivity;

    NumberFormat numberFormat;

    LinearLayout lnrWalletDetection;
    LinearLayout lnrDiscount;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public static final int SELECT_TYPE = 4;
    public static final int BOUND_PADDING = 100;
    public String TAG = getClass().getSimpleName();

    MarkerOptions markerOptions1, markerOptions2;
    List<Marker> markers = new ArrayList<>();
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;

    public JSONObject pushData;

    ImageView imgTruckIV, callActionIV;
    Polyline currentPolyline;
    GoogleMap mMap;
    public double sourceLatitude, sourceLongitude, destLatitude, destLongitude;
    BookingVehicleListResponse bookingVehicleListResponse;
    RelativeLayout mylocation;
    BookingConformedActivity bookingConformedActivity;

    MySenderBroadCast mySenderBroadCast = new MySenderBroadCast();
    Iterable<? extends LatLng> list;
    NotificationPushData data = new NotificationPushData();

    TripStatus tripStatus;

    BookingHTripResponse bookingHTripResponse;
    public ImageView vehicleimg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_trip_details_for_customer);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Cancel Trip Details");
        }

        //new FetchURL(CancelTripDetailsForCustomerActivity.this).execute(getUrl(markerOptions1.getPosition(), markerOptions2.getPosition(), "driving"), "driving");

        Places.initialize(this, "AIzaSyDRMI4wJHUfwtsX3zoNqVaTReXyHtIAT6U");

        if (!Places.isInitialized()) {
            Places.initialize(this, "AIzaSyDRMI4wJHUfwtsX3zoNqVaTReXyHtIAT6U");
        }

        IntentFilter intentFilter = new IntentFilter("com.highway.customer.customerActivity.ACTION_SEND");
        registerReceiver(mySenderBroadCast, intentFilter);

        Log.e(TAG, BaseUtil.jsonFromModel(pushData));

        intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        registerReceiver(mySenderBroadCast, intentFilter);


        sourceTV = findViewById(R.id.sourceTV);
        destTV = findViewById(R.id.destTV);
        callActionIV = findViewById(R.id.callActionIV);
        mylocation = findViewById(R.id.mylocation);
        driverNameTV =findViewById(R.id.DriverNameTV);
        vehicleNameTV = findViewById(R.id.vehicleNameTV);
        vehicleimg = findViewById(R.id.Vehicleimg);

        mylocation.setOnClickListener(this);


        bookingId = findViewById(R.id.booking_id);
        startDate = findViewById(R.id.start_date);
        endDate = findViewById(R.id.end_date);

        start_time = findViewById(R.id.start_time);
        end_time = findViewById(R.id.end_time);

        distance = findViewById(R.id.distance);
        travelTime = findViewById(R.id.travel_time);

        fixed = findViewById(R.id.fixed);
        distancePrice = findViewById(R.id.distance_price);
        peekHourCharges = findViewById(R.id.peek_hour_charges);
        nightFare = findViewById(R.id.night_fare);
        tax = findViewById(R.id.tax);
        totalAmount = findViewById(R.id.total);
        walletDetection = findViewById(R.id.wallet_detection);
        discount = findViewById(R.id.discount);
        paymentMode = findViewById(R.id.payment_mode);
        done = findViewById(R.id.done);
        payAbleAmout = findViewById(R.id.payable);

        layout_normal_flow = findViewById(R.id.layout_normal_flow);
        layout_rental_flow = findViewById(R.id.layout_rental_flow);
        rentalNormalPrice = findViewById(R.id.rental_normal_price);
        rentalTotalDistance = findViewById(R.id.rental_total_distance_km);
        rentalExtraHrKmPrice = findViewById(R.id.rental_extra_hr_km_price);
        rentalTravelTime = findViewById(R.id.rental_travel_time);
        rentalHours = findViewById(R.id.rental_hours);
        layout_outstation_flow = findViewById(R.id.layout_outstation_flow);
        outstationDistanceTravelled = findViewById(R.id.outstation_distance_travelled);
        outstationDistanceFare = findViewById(R.id.outstation_distance_fare);
        outstationDriverBeta = findViewById(R.id.outstation_driver_beta);
        outstationRoundSingle = findViewById(R.id.outstation_round_single);
        outstationNoOfDays = findViewById(R.id.outstation_no_of_days);


        userName = HighwayPrefs.getString(getApplicationContext(), Constants.RECEIVERNAME);
        userMobNo = HighwayPrefs.getString(getApplicationContext(), Constants.RECEIVERPHONENO);
        goodsType= HighwayPrefs.getString(getApplicationContext(),Constants.GOODSTYPES);

        tripdate();
        getInvoiceForCustomer();
        getCancleTripDetailForCustomer(getIntent());
    }

    private void getCancleTripDetailForCustomer(Intent intent) {

        if (intent!=null){
            Bundle bundle = getIntent().getExtras();

            LatLng sourceAddLatLng = new LatLng(Double.parseDouble(""+bundle.getString("sourceLat")),
                    Double.parseDouble(""+bundle.getString("sourceLong")));
            LatLng destAddLatLng = new LatLng(Double.parseDouble(""+ bundle.getString("destinationLat")),
                    Double.parseDouble(""+bundle.getString("destinationLong")));

            sourceLatitude = Double.parseDouble(bundle.getString("sourceLat"));
            sourceLongitude= Double.parseDouble(bundle.getString("sourceLong"));

            destLatitude = Double.parseDouble(bundle.getString("destinationLat"));
            destLongitude= Double.parseDouble(bundle.getString("destinationLong"));

            sourceTV.setText(""+ Utils.getAddress(getApplicationContext(),sourceAddLatLng));
            destTV.setText(""+Utils.getAddress(getApplicationContext(),destAddLatLng));

            markerOptions1 = new MarkerOptions().position(new LatLng(sourceLatitude, sourceLongitude));
            markerOptions1.icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(R.drawable.ic_pins)));

            markerOptions2 = new MarkerOptions().position(new LatLng(destLatitude, destLongitude));
            markerOptions2.icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(R.drawable.ic_pin)));


            userName = bundle.getString("name");
            role = bundle.getString("role");
            vehicleName = bundle.getString("vehicleName");
            vehicleNumber = bundle.getString("vehicleNumber");
            faireChargeVal = bundle.getString("fare");
            status = bundle.getString("status");
            tripType = bundle.getString("tripType");
            tripStartDate = bundle.getString("startDate");
            tripEndDate = bundle.getString("endDate");
            pickUpTime = bundle.getString("pickupTime");
            dropTime = bundle.getString("dropTime");

            driverNameTV.setText(userName);
            vehicleNameTV.setText(vehicleName);
            payAbleAmout.setText(faireChargeVal);

            // As a raugh;

        }

    }


    private void getInvoiceForCustomer() {
        userId = HighwayPrefs.getString(getApplicationContext(), Constants.ID);
        CustomerInvoiceReq customerInvoiceReq = new CustomerInvoiceReq();
        customerInvoiceReq.setBookingId(HighwayApplication.getInstance().getCurrentTripId());
        customerInvoiceReq.setCustomerId(userId);

        RestClient.getCustomerInvoice(customerInvoiceReq, new Callback<CustomerInvoiceResp>() {
            @Override
            public void onResponse(Call<CustomerInvoiceResp> call, Response<CustomerInvoiceResp> response) {
                if (response!=null && response.code()==200 && response.body()!=null){
                    if (response.body().getStatus()){
                        CustomerInvoice customerInvoice =response.body().getCustomerInvoice();
                        invoiceForCustomer(customerInvoice);
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomerInvoiceResp> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "customer invoice failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void invoiceForCustomer(CustomerInvoice customerInvoice) {
        bookingId.setText(""+customerInvoice.getBookingTripCode());
        startDate.setText(""+customerInvoice.getStartDate());
        endDate.setText(""+customerInvoice.getEndDate());
        start_time.setText(""+customerInvoice.getStartTime());
        end_time.setText(" "+customerInvoice.getEndTime());
        distance.setText(""+customerInvoice.getTotDistance());
        travelTime.setText(""+customerInvoice.getTravelTime());
        fixed.setText(""+customerInvoice.getBasedFarefixed());
        distancePrice.setText(""+customerInvoice.getDistancePrice());
        peekHourCharges.setText(""+customerInvoice.getPeekHourCharges());
        nightFare.setText(""+customerInvoice.getNightFare());
        tax.setText(""+customerInvoice.getTax());
        totalAmount.setText(""+customerInvoice.getTotalAmount());
        discount.setText(""+customerInvoice.getDiscount());
        payAbleAmout.setText(""+customerInvoice.getPaymentMode());
    }

    void tripdate() {
        final Calendar c = Calendar.getInstance();
        yy = c.get(Calendar.YEAR);
        mm = c.get(Calendar.MONTH);
        dd = c.get(Calendar.DAY_OF_MONTH);
        startDate.setText(new StringBuilder().append(yy).append(" ").append("-").append(mm + 1).append("-").append(dd));

        // for end date
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd, yyyy h:mm a");
        String dateString = sdf.format(date);
        endDate.setText(dateString);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        Intent resultIntent = new Intent();
        setResult(Activity.RESULT_OK, resultIntent);
        finish(); // close this activity and return to preview activity (if there is any)
        return super.onOptionsItemSelected(item);
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;

        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        onBackPressed();
        return super.onContextItemSelected(item);
    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    public Bitmap createCustomMarker(@DrawableRes int resource) {

        View marker = ((LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);

        ImageView markerImage = marker.findViewById(R.id.imgLogo);
        markerImage.setImageResource(resource);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        marker.setLayoutParams(new ViewGroup.LayoutParams(52, ViewGroup.LayoutParams.WRAP_CONTENT));
        marker.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(marker.getMeasuredWidth(), marker.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        marker.draw(canvas);

        return bitmap;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.setMinZoomPreference(2.0f);
        mMap.setMaxZoomPreference(18.0f);

        try {
            mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(this, R.raw.style));
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            // Oops, looks like the map style resource couldn't be found!
        }

        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                //Your code where exception occurs goes here..
                //
                if (destLatitude > 0 && destLongitude > 0) {

                    Marker markerS = mMap.addMarker(markerOptions1);
                    markers.add(markerS);
                    Marker markerD = mMap.addMarker(markerOptions2);
                    markers.add(markerD);

                    LatLngBounds.Builder builder = new LatLngBounds.Builder();
                    for (Marker marker : markers) {
                        builder.include(marker.getPosition());
                    }
                    LatLngBounds bounds = builder.build();
                    int padding = 40; // offset from edges of the map in pixels
                    CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);

                    mMap.moveCamera(cu);
                }
            }
        });

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
    }
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.mylocation:          // for location
                updateMyLocation();
                break;
        }

    }

    private void updateMyLocation() {
        if (mLastLocation == null) {
            return;
        }
        LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        updateMyLocation();

        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(10));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {}

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + "AIzaSyDRMI4wJHUfwtsX3zoNqVaTReXyHtIAT6U";
        return url;
    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null) {
            currentPolyline.remove();
            currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
        }
    }

}
