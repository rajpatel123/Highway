package com.highway.customer.customerActivity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.highway.R;
import com.highway.broadCastReceiver.MySenderBroadCast;
import com.highway.broadCastReceiver.MyIntentService;
import com.highway.common.base.HighwayApplication;
import com.highway.customer.customerModelClass.bookingVehicleList.BookingVehicleListResponse;
import com.highway.customer.helper.TaskLoadedCallback;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static com.highway.utils.Constants.RECEIVERPHONENO;

public class BookingConformedActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, TaskLoadedCallback {


    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private static final int SELECT_TYPE = 4;
    public TextView bookTruckTv, phoneNoTv, nameTv, editTV;
    public String userName, userMobNo;
    MarkerOptions markerOptions1;
    MarkerOptions markerOptions2;
    List<Marker> markers = new ArrayList<>();
    RecyclerView recyclerView;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    TextView edtDropLocation;
    LinearLayout sourceLL;
    LinearLayout destLL;


    private Toolbar toolbar;
    private TextView sourceTV, destTV, driverName, vehicleName, bookingInfoForDriverAllocationTime, fareValue, cancelTripTV, infoTV;
    private ImageView imgTruckIV, callActionIV;
    TextView goodtype;
    private Polyline currentPolyline;
    private GoogleMap mMap;
    private Button back_button;
    private Button confirmBooking;
    private double sourceLatitude, sourceLongitude;
    private double destLatitude, destLongitude;
    private String sourceName;
    private String destName;
    private boolean isSelected;
    private String gdTypeId, gdTypeText;
    BookingVehicleListResponse bookingVehicleListResponse;
    private String tripId, userRecvNO, userMobileNO;
    public String bookTripIdCode, bookId, vehicleTypeId;
    boolean timeUp;
    String bookVehicleName;
    public TextView rejTV, acptTripTv;

    MySenderBroadCast mySenderBroadCast = new MySenderBroadCast();

  // USING BROADCAST RECEIVER
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String data= intent.getStringExtra("key");
            acptTripTv.setText(data);
        }
    };


    public static void start(ConformBookingActivity activity, String bookTripIdCode, String bookId, String vTypeId, String user_id, String tripRecevirId, String GoodsTypeId, String couponId,
                             double sourceLat, double sourceLong, double destLat,double destLong, String tripFare, String sourceAddress, String destAddress) {
        Intent intent = new Intent(activity, BookingConformedActivity.class);
        intent.putExtra("bookTripIdCode", bookTripIdCode);
        intent.putExtra("bookId", bookId);
        intent.putExtra("vTypeId", vTypeId);
            // Asked by sir
        intent.putExtra("User_id",user_id);
        intent.putExtra("tripRecevirId",tripRecevirId);
        intent.putExtra("GoodsTypeId",GoodsTypeId);
        intent.putExtra("CouponId",couponId);
        intent.putExtra("SourceLat",sourceLat);
        intent.putExtra("SourceLong",sourceLong);
        intent.putExtra("DestLat",destLat);
        intent.putExtra("DestLong",destLong);
        intent.putExtra("TripFare",tripFare);
        intent.putExtra("sourceAddress",sourceAddress);
        intent.putExtra("destAddress",destAddress);

        activity.startActivity(intent);

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmed_booking);

        sourceTV = findViewById(R.id.sourceTV);
        destTV = findViewById(R.id.destTV);
        driverName = findViewById(R.id.driverName);
        vehicleName = findViewById(R.id.vehicleName);
        bookingInfoForDriverAllocationTime = findViewById(R.id.bookingInfoForDriverAllocation);
        fareValue = findViewById(R.id.fareValue);
        callActionIV = findViewById(R.id.callActionIV);
        cancelTripTV = findViewById(R.id.cancelTripTV);
        infoTV = findViewById(R.id.tripInfo);
        toolbar = findViewById(R.id.toolbar);
        // Using Broadcast for Customer
        /*rejTV = findViewById(R.id.TripRejectedTv);
        acptTripTv = findViewById(R.id.TripAcceptTv);
*/
        userName = HighwayPrefs.getString(getApplicationContext(), Constants.RECEIVERNAME);
        userMobNo = HighwayPrefs.getString(getApplicationContext(), RECEIVERPHONENO);
        bookVehicleName = HighwayPrefs.getString(getApplicationContext(), "bookVehicleName");  // booking vehicle nane
        vehicleName.setText(bookVehicleName);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            if (getIntent().hasExtra("bookTripIdCode")) {
                bookTripIdCode = getIntent().getStringExtra("bookTripIdCode");
                bookId = getIntent().getStringExtra("bookId");
                vehicleTypeId = getIntent().getStringExtra("vTypeId");  //vehicle type id
                getSupportActionBar().setTitle("TRIP " + bookTripIdCode);
                HighwayPrefs.putString(getApplicationContext(), "vechicleId", vehicleTypeId);

            }
        }

        sourceTV.setText(HighwayApplication.getInstance().getBookingHTripRequest().getSourceAddress());
        destTV.setText(HighwayApplication.getInstance().getBookingHTripRequest().getDestAddress());
        fareValue.setText(HighwayApplication.getInstance().getBookingHTripRequest().getTripFare());

        initLocations(getIntent());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        IntentFilter intentFilter = new IntentFilter("com.highway.customer.customerActivity.ACTION_SEND");
        registerReceiver(mySenderBroadCast,intentFilter);

        clicklistener();
        bookingTimer();
    }

// USING BROAD CAST RECEIVER
    public void broadCastMessage(View view) {
        Intent serviceIntent = new Intent(this, MyIntentService.class);
        serviceIntent.putExtra("key","Inital Value");
        startService(serviceIntent);

    }
    // USING BROAD CAST RECEIVER --- registered
    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(MyIntentService.MY_SERVICE_INTENT);
        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(mReceiver,intentFilter);
    }
// USING BROAD CAST RECEIVER --- Unregistered
    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getApplicationContext())
                .unregisterReceiver(mReceiver);
    }

// USING BROAD CAST RECEIVER --- Unregistered
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mySenderBroadCast);
    }

    public void clicklistener() {
        cancelTripTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CancelOrderTripByCustomerWithReasonActivity.class);
                //  userId = HighwayPrefs.putString(getApplicationContext(),Constants.ID);
                intent.putExtra("bookTripIdCode", bookTripIdCode);
                intent.putExtra("bookId", bookId);
                intent.putExtra("vTypeId", vehicleTypeId);
                startActivityForResult(intent, 1000);

            }
        });

        infoTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), BookingInfoDetailsActivity.class);
                // asked by sir not sure
//                HighwayPrefs.putString(getApplicationContext(),Constants.BOOKINGTRIPID,tripId);
//                HighwayPrefs.putString(getApplicationContext(),Constants.USERMOBILE,userMobileNO);
//                HighwayPrefs.putString(getApplicationContext(),RECEIVERPHONENO,userRecvNO);
                startActivity(intent);
                //finish();
            }
        });
    }


    private void initLocations(Intent intent) {
        destName = HighwayApplication.getInstance().getBookingHTripRequest().getDestAddress();
        sourceName = HighwayApplication.getInstance().getBookingHTripRequest().getSourceAddress();

        destLatitude = HighwayApplication.getInstance().getBookingHTripRequest().getDestLat();
        destLongitude = HighwayApplication.getInstance().getBookingHTripRequest().getDestLong();

        sourceLatitude = HighwayApplication.getInstance().getBookingHTripRequest().getSourceLat();
        sourceLongitude = HighwayApplication.getInstance().getBookingHTripRequest().getSourceLong();

        sourceTV.setText("" + sourceName);

        markerOptions1 = new MarkerOptions().position(new LatLng(sourceLatitude, sourceLongitude));
        markerOptions1.icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(R.drawable.ic_pins)));

        markerOptions2 = new MarkerOptions().position(new LatLng(destLatitude, destLongitude));
        markerOptions2.icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(R.drawable.ic_pin)));

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
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
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

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    ///**********************
    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
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
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                    }

                } else {

                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

        }
    }

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
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }

    public void bookingTimer() {

        new CountDownTimer(60 * 10 * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                String text = String.format(Locale.getDefault(), "%02d: %02d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60);
                bookingInfoForDriverAllocationTime.setText(text);

            }

            public void onFinish() {
                bookingInfoForDriverAllocationTime.setText("Time up!");
                timeUp = true;
                showAlertDiolog("");

            }
        }.start();
    }

    private void showAlertDiolog(String message) {

        final android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_show_alert_dialog_driver_not_responding, null);
        dialogBuilder.setView(dialogView);

        final android.app.AlertDialog dialog = dialogBuilder.create();
        Button done = dialogView.findViewById(R.id.btn_done);

        TextView text_cancel = dialogView.findViewById(R.id.text_cancel);
        text_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    //by sir notes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK) {

            if (data.hasExtra("isCancelled") && data.getBooleanExtra("isCancelled", false)) {
                finish();
            }

            if (data.hasExtra("isVehicleInfo") && data.getBooleanExtra("isVehicleInfo", false)) {
                finish();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
