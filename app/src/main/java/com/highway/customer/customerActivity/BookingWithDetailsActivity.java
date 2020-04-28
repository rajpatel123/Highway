package com.highway.customer.customerActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
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
import com.highway.common.base.HighwayApplication;
import com.highway.commonretrofit.RestClient;
import com.highway.customer.customerFragment.ReceiverBottomSheetFragment;
import com.highway.customer.customerModelClass.bookingVehicleList.BookingVehicleListRequest;
import com.highway.customer.customerModelClass.bookingVehicleList.BookingVehicleListResponse;
import com.highway.customer.customerModelClass.bookingVehicleList.VInfo;
import com.highway.customer.customerModelClass.bookingVehicleList.VehicleList;
import com.highway.customer.helper.FetchURL;
import com.highway.customer.helper.TaskLoadedCallback;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingWithDetailsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, TaskLoadedCallback, BookingVehicleAdapter.OnClickEvents {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private static final int SELECT_TYPE = 4;
    public TextView bookTruckTv, phoneNoTv, nameTv;
    public String userName, userMobNo;
    Button getDirection;
    int distance;
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
    TextView edtSourceLOcationEDT;
    TextView edtDropLocation;
    LinearLayout sourceLL;
    LinearLayout destLL;

    TextView goodtype;
    TextView vehicleNameTv, vehicleCapicityTv, vehicleSizeTv;
    ImageView vehileBookImg;
    TextView info1, info2, info3, info4, info5, info6;
    List<VehicleList> vehicleList = new ArrayList<>();
    List<VInfo> vInfoList = new ArrayList<>();
    BookingVehicleAdapter bookingVehicleAdapter;
    BookingVehicleListResponse bookingVehicleListResponse;
    String user_Id;
    private Polyline currentPolyline;
    private GoogleMap mMap;
    private Button back_button;
    private double sourceLatitude, sourceLongitude;
    private double destLatitude, destLongitude;
    private String sourceName;
    private String destName;
    private boolean isSelected;
    private String gdTypeId, gdTypeText, vehicleTypId;
    private boolean userclicked;
    private Paint paint;

    public static void start(Activity activity) {

        Intent intent = new Intent(activity, BookingWithDetailsActivity.class);

        intent.putExtra("sourceName", HighwayApplication.getInstance().getBookingHTripRequest().getSourceAddress());
        intent.putExtra("sourceLatitude", HighwayApplication.getInstance().getBookingHTripRequest().getSourceLat());
        intent.putExtra("sourceLongitude", HighwayApplication.getInstance().getBookingHTripRequest().getSourceLong());
        intent.putExtra("destName", HighwayApplication.getInstance().getBookingHTripRequest().getDestAddress());
        intent.putExtra("destLatitude", HighwayApplication.getInstance().getBookingHTripRequest().getDestLat());
        intent.putExtra("destLongitude", HighwayApplication.getInstance().getBookingHTripRequest().getDestLong());

        activity.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_booking_with_details);

        edtSourceLOcationEDT = findViewById(R.id.edtSourceLOcation);
        edtDropLocation = findViewById(R.id.edtDropLocation);
        back_button = findViewById(R.id.back_button);
        goodtype = findViewById(R.id.goodtype);
        sourceLL = findViewById(R.id.sourceLL);
        destLL = findViewById(R.id.destLL);

        recyclerView = findViewById(R.id.vehicleListRV);
        bookTruckTv = findViewById(R.id.bookTruckTv);
        phoneNoTv = findViewById(R.id.TvPhoneNo);
        nameTv = findViewById(R.id.TvUserName);


        userName = HighwayPrefs.getString(getApplicationContext(), Constants.NAME);
        userMobNo = HighwayPrefs.getString(getApplicationContext(), Constants.USERMOBILE);

        phoneNoTv.setText(userMobNo);
        nameTv.setText(userName);


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

        new FetchURL(BookingWithDetailsActivity.this).execute(getUrl(markerOptions1.getPosition(), markerOptions2.getPosition(), "driving"), "driving");


        Places.initialize(this, "AIzaSyDRMI4wJHUfwtsX3zoNqVaTReXyHtIAT6U");

        if (!Places.isInitialized()) {
            Places.initialize(this, "AIzaSyDRMI4wJHUfwtsX3zoNqVaTReXyHtIAT6U");
        }

        sourceLL.setOnClickListener(new View.OnClickListener() {
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


        destLL.setOnClickListener(new View.OnClickListener() {
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

        goodtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(BookingWithDetailsActivity.this, GoodsTypeDetailActivity.class);
                startActivityForResult(intent, SELECT_TYPE);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_up_out);
            }
        });

        clicklistener();
        showBookingVehicleListRv();
        getBookingVehicle();

    }

    public void clicklistener() {

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bookTruckTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!TextUtils.isEmpty(HighwayApplication.getInstance().getBookingHTripRequest().getVehicleTypeId())
                        && !TextUtils.isEmpty(HighwayApplication.getInstance().getBookingHTripRequest().getGoodsTypeId())) {
                    ReceiverBottomSheetFragment receiverBottomSheetFragment = ReceiverBottomSheetFragment.newInstance().newInstance();
                    receiverBottomSheetFragment.setCancelable(false);
                    receiverBottomSheetFragment.show(getSupportFragmentManager(), ReceiverBottomSheetFragment.TAG);
                } else {
                    Toast.makeText(BookingWithDetailsActivity.this, "Please select vehicle and goods type", Toast.LENGTH_LONG).show();
                }
            }
        });

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
            markerOptions1.icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(R.drawable.ic_pins)));
            markerOptions2 = new MarkerOptions().position(new LatLng(destLatitude, destLongitude));
            markerOptions2.icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(R.drawable.ic_pin)));

            distance();

        }
    }

    /* both method r working But not chnge parameter   dist = dist * 60 * 1.1515 */
    private double distance() {
        // 1st method
        Location startPoint = new Location("sourceName");
        startPoint.setLatitude(sourceLatitude);
        startPoint.setLongitude(sourceLongitude);

        Location endPoint = new Location("destName");
        endPoint.setLatitude(destLatitude);
        endPoint.setLongitude(destLongitude);
        distance = (int) (startPoint.distanceTo(endPoint) / 1000);

        Intent intent = new Intent();
        intent.putExtra("distance", distance);

        Toast.makeText(this, "" + String.valueOf(distance) + " " + " km ", Toast.LENGTH_SHORT).show();
        return distance;

          /*  2nd method

            double theta = sourceLatitude - sourceLongitude;
            double dist = Math.sin(deg2rad(sourceLatitude))
                    * Math.sin(deg2rad(sourceLongitude))
                    + Math.cos(deg2rad(sourceLatitude))
                    * Math.cos(deg2rad(sourceLongitude))
                    * Math.cos(deg2rad(theta));
            dist = Math.acos(dist);
            dist = rad2deg(dist);
            dist = dist * 60 * 1.1515;

           Toast.makeText(this, ""+dist, Toast.LENGTH_SHORT).show();

            return (dist);

        // 3rd method
        int Radius = 6371;// radius of earth in Km
        double lat1 = sourceLatitude;
        double lat2 = sourceLongitude;
        double lon1 = sourceLatitude;
        double lon2 = sourceLongitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));

        double meter = valueResult * 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));

        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec + " Meter   " + meterInDec);

        Toast.makeText(this, "" + kmInDec, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "" + meterInDec, Toast.LENGTH_SHORT).show();

        return Radius * c;
        */

    }


    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }


    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
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
                      //  markerOptions.icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(R.drawable.highway_logo)));
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
                      //  markerOptions.icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(R.drawable.highway_logo)));
                        mCurrLocationMarker = mMap.addMarker(markerOptions);

                        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 14);
                        mMap.moveCamera(cameraUpdate);
                    }
                }
            } else if (requestCode == SELECT_TYPE) {
                gdTypeId = data.getStringExtra("id");
                HighwayApplication.getInstance().getBookingHTripRequest().setGoodsTypeId(gdTypeId);
                gdTypeText = data.getStringExtra("type");
                goodtype.setText(gdTypeText);
                HighwayPrefs.putString(getApplicationContext(),Constants.GOODSTYPES,gdTypeText);
                //goodtype.setText(gdTypeId);
            }
        } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
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
                if (sourceLatitude > 0 && sourceLongitude > 0) {

                    Marker markerS = mMap.addMarker(markerOptions1);
                    Marker markerD = mMap.addMarker(markerOptions2);
                    markers.add(markerS);
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
        mMap.moveCamera(CameraUpdateFactory.zoomTo(14));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }


    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

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
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCLickInfo(int position) {

         /* if (vehicleList != null && vehicleList.size() > 0)
            showInfoDialog(vehicleInfoList.get(position));  */

        if (bookingVehicleListResponse.getVehicleData().getVehicleList() != null
                && bookingVehicleListResponse.getVehicleData().getVehicleList().size() > 0)
            showInfoDialog(bookingVehicleListResponse.getVehicleData().getVehicleList().get(position).getVInfo());

    }

    @Override
    public void onCLickTruck(int position, String fare) {

        if (vehicleList != null && vehicleList.size() > 0) {
            bookTruckTv.setText("BOOK " + vehicleList.get(position).getVehicleName());
            HighwayApplication.getInstance().getBookingHTripRequest().setVehicleTypeId(vehicleList.get(position).getVehicleId());
            HighwayApplication.getInstance().getBookingHTripRequest().setTripFare(fare);
            HighwayPrefs.putString(getApplicationContext(),"bookVehicleName",vehicleList.get(position).getVehicleName()); // send vehicle name on booking conformed
        }
    }

    public void getBookingVehicle() {

        BookingVehicleListRequest bookingVehicleListRequest = new BookingVehicleListRequest();

        user_Id = HighwayPrefs.getString(getApplicationContext(), Constants.ID);

        HighwayApplication.getInstance().getBookingHTripRequest().setUserId(user_Id);
        bookingVehicleListRequest.setUserId(user_Id);

        bookingVehicleListRequest.setStartLat(Double.parseDouble(String.valueOf(sourceLatitude)));
        bookingVehicleListRequest.setStartLong(Double.parseDouble(String.valueOf(sourceLongitude)));
        bookingVehicleListRequest.setEndLat(Double.parseDouble(String.valueOf(destLatitude)));
        bookingVehicleListRequest.setEndLong(Double.parseDouble(String.valueOf(destLongitude)));

        bookingVehicleListRequest.setStartLat(HighwayApplication.getInstance().getBookingHTripRequest().getSourceLat());
        bookingVehicleListRequest.setStartLong(HighwayApplication.getInstance().getBookingHTripRequest().getSourceLong());
        bookingVehicleListRequest.setEndLat(HighwayApplication.getInstance().getBookingHTripRequest().getDestLat());
        bookingVehicleListRequest.setEndLong(HighwayApplication.getInstance().getBookingHTripRequest().getDestLong());

        RestClient.getBookingVehicleList(bookingVehicleListRequest, new Callback<BookingVehicleListResponse>() {
            @Override
            public void onResponse(Call<BookingVehicleListResponse> call, Response<BookingVehicleListResponse> response) {

                if (response.body() != null) {
                    if (response.body().getStatus()) {
                        bookingVehicleListResponse = response.body();
                        bookingVehicleAdapter.setData(bookingVehicleListResponse);
                        vehicleList = bookingVehicleListResponse.getVehicleData().getVehicleList();
                        bookingVehicleAdapter.setOnClickEvents(BookingWithDetailsActivity.this);
                        bookingVehicleAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<BookingVehicleListResponse> call, Throwable t) {
                Toast.makeText(BookingWithDetailsActivity.this, "Booking Vehicle list Response failed", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void showBookingVehicleListRv() {

        bookingVehicleAdapter = new BookingVehicleAdapter(bookingVehicleListResponse, getApplicationContext(), this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        bookingVehicleAdapter.setOnClickEvents(this);
        recyclerView.setAdapter(bookingVehicleAdapter);
    }

    private void showInfoDialog(VInfo vInfo) {

        final android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.fare_info, null);
        dialogBuilder.setView(dialogView);

        final android.app.AlertDialog dialog = dialogBuilder.create();
        vehicleNameTv = dialogView.findViewById(R.id.truckName);
        vehileBookImg = dialogView.findViewById(R.id.vehicleImg);
        vehicleCapicityTv = dialogView.findViewById(R.id.capacity);
        vehicleSizeTv = dialogView.findViewById(R.id.sizeTV);

        info1 = dialogView.findViewById(R.id.info1);
        info2 = dialogView.findViewById(R.id.info2);
        info3 = dialogView.findViewById(R.id.info3);
        info4 = dialogView.findViewById(R.id.info4);
        info5 = dialogView.findViewById(R.id.info6);
        info6 = dialogView.findViewById(R.id.info5);
        TextView okay = dialogView.findViewById(R.id.done);

        vehicleNameTv.setText(vInfo.getVehicleName());
        vehicleCapicityTv.setText(vInfo.getVehicleCapacity());
        vehicleSizeTv.setText(vInfo.getVehicleSize());
        info1.setText(vInfo.getInfo1());
        info2.setText(vInfo.getInfo2());
        info3.setText(vInfo.getInfo3());
        info4.setText(vInfo.getInfo4());
        info5.setText(vInfo.getInfo5());
        info6.setText(vInfo.getInfo6());
        // vehicleData.getVehicleList().add(0, vInfo);
        //vehileBookImg.setBackgroundResource(R.drawable.truck);

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        if (!isFinishing() && !dialog.isShowing())
            dialog.show();
    }

}
