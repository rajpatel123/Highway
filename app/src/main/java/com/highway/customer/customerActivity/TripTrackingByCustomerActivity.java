package com.highway.customer.customerActivity;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

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
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.android.gms.maps.SupportMapFragment;
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
import com.highway.customer.helper.FetchURL;
import com.highway.customer.helper.TaskLoadedCallback;
import com.highway.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class TripTrackingByCustomerActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, TaskLoadedCallback, View.OnClickListener {


    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private static final int SELECT_TYPE = 4;
    private static final int BOUND_PADDING = 100;
    public String TAG = getClass().getSimpleName();
    MarkerOptions markerOptions1;
    MarkerOptions markerOptions2;
    List<Marker> markers = new ArrayList<>();
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    TextView edtDropLocation;
    TextView edtSourceLOcation;
    LinearLayout sourceLL;
    LinearLayout destLL;

    private Toolbar toolbar;
    RelativeLayout mylocation;

    private Polyline currentPolyline;
    private GoogleMap mMap;
    private double sourceLatitude, sourceLongitude;
    private double destLatitude, destLongitude;
    private String sourceName, destName, name, role, vehicleName, vehicleNumber, fareCharge,
            status, tripType, startDate, endDate, pickUpTime, dropTime;
    public String bookTripIdCode, bookId, vehicleTypeId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_tracking_by_customer);

        edtSourceLOcation = findViewById(R.id.edtSourceLOcation);
        edtDropLocation = findViewById(R.id.edtDropLocation);
        sourceLL = findViewById(R.id.sourceLL);
        destLL = findViewById(R.id.destLL);
        toolbar = findViewById(R.id.toolbar);
        mylocation = findViewById(R.id.mylocation);

        mylocation.setOnClickListener(this);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Trip Tracking");

        }

        new FetchURL(TripTrackingByCustomerActivity.this).execute(getUrl(markerOptions1.getPosition(), markerOptions2.getPosition(), "driving"), "driving");

        Places.initialize(this, "AIzaSyDRMI4wJHUfwtsX3zoNqVaTReXyHtIAT6U");

        if (!Places.isInitialized()) {
            Places.initialize(this, "AIzaSyDRMI4wJHUfwtsX3zoNqVaTReXyHtIAT6U");
        }

        initLocations(getIntent());


    }

    private void initLocations(Intent intent) {
        if (intent != null) {

            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                LatLng sourceAddLatLng = new LatLng(Double.parseDouble("" + bundle.getString("sourceLat")),
                        Double.parseDouble("" + bundle.getString("sourceLong")));
                LatLng destAddLatLng = new LatLng(Double.parseDouble("" + bundle.getString("destinationLat")),
                        Double.parseDouble("" + bundle.getString("destinationLat")));

                sourceLatitude = Double.parseDouble("" + bundle.getString("sourceLat"));
                sourceLongitude = Double.parseDouble("" + bundle.getString("sourceLong"));
                destLatitude = Double.parseDouble("" + bundle.getString("destinationLat"));
                destLongitude = Double.parseDouble("" + bundle.getString("destinationLong"));

                edtSourceLOcation.setText("" + Utils.getAddress(getApplicationContext(), sourceAddLatLng));
                edtDropLocation.setText("" + Utils.getAddress(getApplicationContext(), destAddLatLng));

                markerOptions1 = new MarkerOptions().position(new LatLng(sourceLatitude, sourceLongitude));
                markerOptions1.icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(R.drawable.ic_pins)));

                markerOptions2 = new MarkerOptions().position(new LatLng(destLatitude, destLongitude));
                markerOptions2.icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(R.drawable.ic_pin)));

                name = bundle.getString("name");
                role = bundle.getString("role");
                vehicleName = bundle.getString("vehicleName");
                vehicleNumber = bundle.getString("vehicleNumber");
                fareCharge = bundle.getString("fare");
                status = bundle.getString("status");
                tripType = bundle.getString("tripType");
                startDate = bundle.getString("startDate");
                endDate = bundle.getString("endDate");
                pickUpTime = bundle.getString("pickupTime");
                dropTime = bundle.getString("dropTime");

            }
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
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

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
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }


}
