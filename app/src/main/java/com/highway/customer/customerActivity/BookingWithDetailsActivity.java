package com.highway.customer.customerActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.highway.R;
import com.highway.Vehicle;
import com.highway.customer.helper.FetchURL;
import com.highway.customer.helper.TaskLoadedCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookingWithDetailsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, TaskLoadedCallback {


    Button getDirection;
    private Polyline currentPolyline;

    MarkerOptions markerOptions1;
    MarkerOptions markerOptions2;

    RecyclerView recyclerView;

    int AUTOCOMPLETE_REQUEST_CODE_SOURCE = 1;
    int AUTOCOMPLETE_REQUEST_CODE_DEST = 2;

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;


    EditText edtSourceLOcationEDT;
    EditText edtDropLocation;

    private double sourceLatitude, sourceLongitude;
    private double destLatitude, destLongitude;
    private String sourceName;
    private String destName;

    List<Vehicle> vehicleList = new ArrayList<>();
   private BookingVehicleAdapter bookingVehicleAdapter;


    public static void start(Activity activity,
                             String sourceName,
                             double sourceLatitude,
                             double sourceLongitude,
                             String destName,
                             double destLatitude,
                             double destLongitude) {


        Intent intent = new Intent(activity, BookingWithDetailsActivity.class);
        intent.putExtra("sourceName", sourceName);
        intent.putExtra("sourceLatitude", sourceLatitude);
        intent.putExtra("sourceLongitude", sourceLongitude);
        intent.putExtra("destName", destName);
        intent.putExtra("destLatitude", destLatitude);
        intent.putExtra("destLongitude", destLongitude);

        activity.startActivity(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_with_details);

        edtSourceLOcationEDT = findViewById(R.id.edtSourceLOcation);
        edtDropLocation = findViewById(R.id.edtDropLocation);
        recyclerView = findViewById(R.id.vehicleListRV);

        recyclerView = findViewById(R.id.vehicleRecyclerView);


        initLocations(getIntent());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }


        new FetchURL(BookingWithDetailsActivity.this).execute(getUrl(markerOptions1.getPosition(), markerOptions2.getPosition(), "driving"), "driving");


        Places.initialize(this, "AIzaSyDRMI4wJHUfwtsX3zoNqVaTReXyHtIAT6U");

        if (!Places.isInitialized()) {
            Places.initialize(this, "AIzaSyDRMI4wJHUfwtsX3zoNqVaTReXyHtIAT6U");
        }

        edtSourceLOcationEDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);

                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.FULLSCREEN, fields)
                        .setCountry("IN")
                        .build(BookingWithDetailsActivity.this);
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE_SOURCE);
            }
        });


        edtDropLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);

                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.FULLSCREEN, fields)
                        .setCountry("IN")
                        .build(BookingWithDetailsActivity.this);
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE_DEST);
            }
        });




        bookingVehicleAdapter = new BookingVehicleAdapter(vehicleList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(bookingVehicleAdapter);


        List<Vehicle> vehicles = new ArrayList<>();


        for (int i = 0; i < 8; i++) {
            Vehicle vehicle = new Vehicle();
            vehicle.setvName("TATA ACE");
            vehicle.setDuration("26 min");
            vehicle.setFare("1398.00");
            vehicle.setIcon(R.drawable.truck);
            vehicle.setCapacity("580Kg");
            vehicle.setInfo1("In given fare 60 mins free loading & unloading time");
            vehicle.setInfo2("Addition loading & unloading will be charged @INR 2/min");
            vehicle.setInfo3("Fare may change in case of route or location will be changed");
            vehicle.setInfo4("Fare does not include toll charges if any");
            vehicle.setInfo5("Parking charges willbe paid by customer");
            vehicle.setInfo6("We do not allow overloading");

            vehicleList.add(vehicle);
        }
        BookingVehicleAdapter bookingVehicleAdapter1 = new BookingVehicleAdapter(this, vehicles);

        recyclerView.setAdapter(bookingVehicleAdapter1);




    }

    private void initLocations(Intent intent) {
        if (intent != null) {
            sourceName = intent.getStringExtra("sourceName");
            sourceLatitude = intent.getDoubleExtra("sourceLatitude", sourceLatitude);
            sourceLongitude = intent.getDoubleExtra("sourceLongitude", sourceLongitude);
            destName = intent.getStringExtra("destName");
            destLatitude = intent.getDoubleExtra("destLatitude", destLatitude);
            destLongitude = intent.getDoubleExtra("destLongitude", destLongitude);
            edtSourceLOcationEDT.setText("" + sourceName);
            edtDropLocation.setText("" + destName);

            markerOptions1 = new MarkerOptions().position(new LatLng(sourceLatitude, sourceLongitude));
            markerOptions2 = new MarkerOptions().position(new LatLng(destLatitude, destLongitude));


        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == AUTOCOMPLETE_REQUEST_CODE_SOURCE) {

                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.i("TAG", "Place: " + place.getName() + ", " + place.getId());

                if (TextUtils.isEmpty(place.getName())) {
                    edtSourceLOcationEDT.setText("" + place.getName());
                    if (mMap != null && place.getLatLng() != null) {

                        LatLng latLng = place.getLatLng();

                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title("" + place.getName());
                        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(R.drawable.highway_logo)));
                        mCurrLocationMarker = mMap.addMarker(markerOptions);

                        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 14);
                        mMap.moveCamera(cameraUpdate);
                    }

                }
            } else if (requestCode == AUTOCOMPLETE_REQUEST_CODE_DEST) {

                Place placeDest = Autocomplete.getPlaceFromIntent(data);

                if (!TextUtils.isEmpty(placeDest.getName())) {
                    Log.i("TAG", "Place: " + placeDest.getName() + ", " + placeDest.getId());

                    edtSourceLOcationEDT.setText("" + placeDest.getName());
                    if (mMap != null && placeDest.getLatLng() != null) {

                        LatLng latLng = placeDest.getLatLng();

                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title("" + placeDest.getName());
                        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(R.drawable.highway_logo)));
                        mCurrLocationMarker = mMap.addMarker(markerOptions);

                        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 14);
                        mMap.moveCamera(cameraUpdate);
                    }

                }
            }
        } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
            // TODO: Handle the error.
            Status status = Autocomplete.getStatusFromIntent(data);
            Log.i("TAG", status.getStatusMessage());
        } else if (resultCode == RESULT_CANCELED) {
            // The user canceled the operation.
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
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMinZoomPreference(0.0f);
        mMap.setMaxZoomPreference(18.0f);

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


        if (sourceLatitude > 0 && sourceLongitude > 0) {


            mMap.addMarker(markerOptions1);
            mMap.addMarker(markerOptions2);
            CameraPosition googlePlex = CameraPosition.builder()
                    .target(new LatLng(sourceLatitude, sourceLatitude))
                    .zoom(7)
                    .bearing(0)
                    .tilt(45)
                    .build();

            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 5000, null);

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

    @Override
    public void onLocationChanged(Location location) {
//        mLastLocation = location;
//        if (mCurrLocationMarker != null) {
//            mCurrLocationMarker.remove();
//        }
//
//        //Place current location marker
//        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//
//        //move map camera
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//        mMap.moveCamera(CameraUpdateFactory.zoomTo(14));
//
//        //stop location updates
//        if (mGoogleApiClient != null) {
//            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
//        }

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */


    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        // mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
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
