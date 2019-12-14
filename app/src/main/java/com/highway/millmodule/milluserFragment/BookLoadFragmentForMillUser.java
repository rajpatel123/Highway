package com.highway.millmodule.milluserFragment;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.highway.R;
import com.highway.common.base.activity.DashBoardActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class BookLoadFragmentForMillUser extends Fragment implements AdapterView.OnItemSelectedListener, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener , LocationListener  {

    int AUTOCOMPLETE_REQUEST_CODE_SOURCE = 1;
    int AUTOCOMPLETE_REQUEST_CODE_DEST = 2;

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;

    TextView edtSourceLOcationBookLoad;
    TextView edtDropLocationBookload;
    Button btnBookNow;

    LinearLayout sourceLatLng;
    LinearLayout destLatLng;

    private double sourceLatitude, sourceLongitude;
    private double destLatitude, destLongitude;
    private String sourceName;
    private String destName;
    Intent intent;

    Spinner spGoodsType,spApproxWeightOfGoods;

    private DashBoardActivity millActivity;


    public BookLoadFragmentForMillUser() {
        // Required empty public constructor
    }


    public static BookLoadFragmentForMillUser newInstance() {
        BookLoadFragmentForMillUser fragment = new BookLoadFragmentForMillUser();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_book_load_fragment_for_mill_user, container, false);

        spGoodsType =view.findViewById(R.id.GoodsTypesSpinner);
        spApproxWeightOfGoods = view.findViewById(R.id.ApproxloadWeightSpinner);
        edtSourceLOcationBookLoad = view.findViewById(R.id.edtSourceLocationBookLoad);
        edtDropLocationBookload = view.findViewById(R.id.edtDropLocationBookLoad);
        sourceLatLng = view.findViewById(R.id.sourceLatLng);
        destLatLng = view.findViewById(R.id.destLatLng);
        btnBookNow    = view.findViewById(R.id.BtnBookNow);


        mapInitialization();
        onClickListener();
        spinnerOperation();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        millActivity = (DashBoardActivity) getActivity();
    }

    public void mapInitialization(){
        Places.initialize(millActivity, "AIzaSyDRMI4wJHUfwtsX3zoNqVaTReXyHtIAT6U");

        if (!Places.isInitialized()) {
            Places.initialize(millActivity, "AIzaSyDRMI4wJHUfwtsX3zoNqVaTReXyHtIAT6U");
        }
    }

    public void onClickListener(){
        sourceLatLng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);

                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.FULLSCREEN, fields)
                        .setCountry("IN")
                        .build(millActivity);
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE_SOURCE);
            }
        });

        destLatLng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);

                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.FULLSCREEN, fields)
                        .setCountry("IN")
                        .build(millActivity);
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE_DEST);
            }
        });

        edtSourceLOcationBookLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);

                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.FULLSCREEN, fields)
                        .build(millActivity);
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE_SOURCE);
            }
        });


        btnBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),DashBoardActivity.class);
                startActivity(intent);

                getActivity().finish();

            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            if (requestCode == AUTOCOMPLETE_REQUEST_CODE_SOURCE) {

                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.i("TAG", "Place: " + place.getName() + ", " + place.getId());

                if (!TextUtils.isEmpty(place.getName())) {
                    edtSourceLOcationBookLoad.setText("" + place.getName());
                    if (mMap != null && place.getLatLng()!= null) {

                        LatLng latLng = place.getLatLng();
                        sourceName = place.getName();
                        sourceLatitude = latLng.latitude;
                        sourceLongitude = latLng.longitude;
                        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 14);
                        mMap.moveCamera(cameraUpdate);
                    }
                }
            } else if (requestCode == AUTOCOMPLETE_REQUEST_CODE_DEST) {

                Place placeDest = Autocomplete.getPlaceFromIntent(data);

                if (!TextUtils.isEmpty(placeDest.getName())) {
                    Log.i("TAG", "Place: " + placeDest.getName() + ", " + placeDest.getId());

                    edtDropLocationBookload.setText("" + placeDest.getName());
                    if (mMap != null && placeDest.getLatLng() != null) {

                        LatLng latLng = placeDest.getLatLng();

                        destName = placeDest.getName();
                        destLatitude = latLng.latitude;
                        destLongitude = latLng.longitude;
                    }
                }
            }
        } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
            Status status = Autocomplete.getStatusFromIntent(data);
            Log.i("TAG", status.getStatusMessage());
        } else if (resultCode == RESULT_CANCELED) {
        }
    }


    public void spinnerOperation(){

        spGoodsType.setOnItemSelectedListener(this);
        spApproxWeightOfGoods.setOnItemSelectedListener(this);

       // Spinner Drop down elements
        List<String> categorieSpGoodsTypes = new ArrayList<>();
         categorieSpGoodsTypes.add("Item 1");
        categorieSpGoodsTypes.add("Item 2");
        categorieSpGoodsTypes.add("Item 3");
        categorieSpGoodsTypes.add("Item 4");
        categorieSpGoodsTypes.add("Item 5");
        categorieSpGoodsTypes.add("Item 6");
        categorieSpGoodsTypes.add("Item 7");
        categorieSpGoodsTypes.add("Item 8");
        categorieSpGoodsTypes.add("Item 9");
        categorieSpGoodsTypes.add("Item 10");

        // Spinner Drop down elements
        List<String> categOfApproxWeightOfGoods = new ArrayList<>();
        categOfApproxWeightOfGoods.add("Item 1");
        categOfApproxWeightOfGoods.add("Item 2");
        categOfApproxWeightOfGoods.add("Item 3");
        categOfApproxWeightOfGoods.add("Item 4");
        categOfApproxWeightOfGoods.add("Item 5");
        categOfApproxWeightOfGoods.add("Item 6");
        categOfApproxWeightOfGoods.add("Item 7");
        categOfApproxWeightOfGoods.add("Item 8");
        categOfApproxWeightOfGoods.add("Item 9");
        categOfApproxWeightOfGoods.add("Item 10");

       // creating Adapter for goots types
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categorieSpGoodsTypes);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGoodsType.setAdapter(dataAdapter);

        // creating Adapter for approx load weight
        ArrayAdapter<String> loadWeightAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categOfApproxWeightOfGoods);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGoodsType.setAdapter(loadWeightAdapter);
        spApproxWeightOfGoods.setAdapter(loadWeightAdapter);

        /*intent.putExtra("data",String.valueOf(spGoodsType.getSelectedItem()));
        intent.putExtra("data",String.valueOf(spApproxWeightOfGoods.getSelectedItem()));*/
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
       // Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }


}
