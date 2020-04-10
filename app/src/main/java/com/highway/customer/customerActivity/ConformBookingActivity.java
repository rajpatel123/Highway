package com.highway.customer.customerActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
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
import androidx.core.content.ContextCompat;
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
import com.highway.common.base.HighwayApplication;
import com.highway.common.base.commonModel.bookingHTrip.BookingHTripResponse;
import com.highway.commonretrofit.RestClient;
import com.highway.customer.customerFragment.ReceiverBottomSheetFragment;
import com.highway.customer.helper.TaskLoadedCallback;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConformBookingActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
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
    int AUTOCOMPLETE_REQUEST_CODE_SOURCE = 1;
    int AUTOCOMPLETE_REQUEST_CODE_DEST = 2;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    TextView edtDropLocation;
    LinearLayout sourceLL;
    LinearLayout destLL;

    TextView goodtype;
    private Polyline currentPolyline;
    private GoogleMap mMap;
    private Button back_button;
    private Button confirmBooking;
    private double sourceLatitude, sourceLongitude;
    private double destLatitude;
    private double destLongitude;
    private String sourceName;
    private String destName;
    private boolean isSelected;
    private String gdTypeId, gdTypeText;

    public static void start(BookingWithDetailsActivity activity) {
        Intent intent = new Intent(activity, ConformBookingActivity.class);
        activity.startActivity(intent);

    }

    public static ReceiverBottomSheetFragment newInstance() {
        ReceiverBottomSheetFragment fragment = new ReceiverBottomSheetFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_confirm_booking);

        edtDropLocation = findViewById(R.id.edtDropLocation);
        back_button = findViewById(R.id.back_button);

        confirmBooking = findViewById(R.id.confirmBooking);
        phoneNoTv = findViewById(R.id.mobileNumber);
        nameTv = findViewById(R.id.TvUserName);
        editTV = findViewById(R.id.editTV);

        userName = HighwayPrefs.getString(getApplicationContext(), Constants.RECEIVERNAME);
        userMobNo = HighwayPrefs.getString(getApplicationContext(), Constants.RECEIVERPHONENO);

        phoneNoTv.setText("Phone :" + userMobNo);
        nameTv.setText("Receiver Name :" + userName);


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


        clicklistener();

    }

    public void clicklistener() {

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        confirmBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RestClient.confirmBooking(HighwayApplication.getInstance().getBookingHTripRequest(), new Callback<BookingHTripResponse>() {
                    @Override
                    public void onResponse(Call<BookingHTripResponse> call, Response<BookingHTripResponse> response) {
                        if (response.code() == 200 && response.body() != null) {

                            BookingHTripResponse resp = response.body();
                            if (resp != null && resp.getBookIdCode() != null && resp.getBookId() != null)
                                BookingConformedActivity.start(ConformBookingActivity.this, resp.getBookIdCode(), resp.getBookId(),
                                        HighwayApplication.getInstance().getBookingHTripRequest().getVehicleTypeId(),
                                        /// Asked by sir
                                        HighwayApplication.getInstance().getBookingHTripRequest().getUserId(),
                                        HighwayApplication.getInstance().getBookingHTripRequest().getTripRecevirId(),
                                        HighwayApplication.getInstance().getBookingHTripRequest().getGoodsTypeId(),
                                        HighwayApplication.getInstance().getBookingHTripRequest().getCouponId(),
                                        HighwayApplication.getInstance().getBookingHTripRequest().getSourceLat(),
                                        HighwayApplication.getInstance().getBookingHTripRequest().getSourceLong(),
                                        HighwayApplication.getInstance().getBookingHTripRequest().getDestLat(),
                                        HighwayApplication.getInstance().getBookingHTripRequest().getDestLong(),
                                        HighwayApplication.getInstance().getBookingHTripRequest().getTripFare(),
                                        HighwayApplication.getInstance().getBookingHTripRequest().getSourceAddress(),
                                        HighwayApplication.getInstance().getBookingHTripRequest().getDestAddress()
                                        );
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<BookingHTripResponse> call, Throwable t) {
                        Toast.makeText(ConformBookingActivity.this, "booking failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        editTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 if (!TextUtils.isEmpty(HighwayApplication.getInstance().getBookingHTripRequest().getVehicleTypeId())
                        && !TextUtils.isEmpty(HighwayApplication.getInstance().getBookingHTripRequest().getGoodsTypeId())) {
                    ReceiverBottomSheetFragment receiverBottomSheetFragment = ReceiverBottomSheetFragment.newInstance().newInstance();
                    receiverBottomSheetFragment.setCancelable(false);
                    receiverBottomSheetFragment.show(getSupportFragmentManager(), ReceiverBottomSheetFragment.TAG);
                } else {
                    Toast.makeText(getApplicationContext(), "Please update receiver name and number", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void initLocations(Intent intent) {

        destName = HighwayApplication.getInstance().getBookingHTripRequest().getDestAddress();
        destLatitude = HighwayApplication.getInstance().getBookingHTripRequest().getDestLat();

        destLongitude = HighwayApplication.getInstance().getBookingHTripRequest().getDestLong();
        edtDropLocation.setText("" + destName);

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

                    Marker markerD = mMap.addMarker(markerOptions2);
                    markers.add(markerD);

                    LatLngBounds.Builder builder = new LatLngBounds.Builder();
                    for (Marker marker : markers) {
                        builder.include(marker.getPosition());
                    }
                    LatLngBounds bounds = builder.build();
                    int padding = 0; // offset from edges of the map in pixels
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


}
