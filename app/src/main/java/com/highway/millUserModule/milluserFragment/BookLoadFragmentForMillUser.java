package com.highway.millUserModule.milluserFragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.highway.R;
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.commonretrofit.RestClient;
import com.highway.millUserModule.SpinnerModelForMiller.ApproxLoad.ApproxLoadDatum;
import com.highway.millUserModule.SpinnerModelForMiller.ApproxLoad.ApproxLoadDropDownRequest;
import com.highway.millUserModule.SpinnerModelForMiller.ApproxLoad.ApproxLoadDropDownResponse;
import com.highway.millUserModule.SpinnerModelForMiller.ApproxLoad.DataModel;
import com.highway.millUserModule.SpinnerModelForMiller.GoodsTypes.Data;
import com.highway.millUserModule.SpinnerModelForMiller.GoodsTypes.GoodTypeDatum;
import com.highway.millUserModule.SpinnerModelForMiller.GoodsTypes.GoodsTypeDropDownRequest;
import com.highway.millUserModule.SpinnerModelForMiller.GoodsTypes.GoodsTypesDropDownResponse;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;
import com.highway.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class BookLoadFragmentForMillUser extends Fragment implements /*AdapterView.OnItemSelectedListener,*/
        OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

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
    private String sourceName, destName;
    Spinner spGoodsType, spApproxWeightOfGoods;
    public String userId;
    GoodsTypesDropDownResponse goodsTypesDropDownResponse;
    ApproxLoadDropDownResponse approxLoadDropDownResponse;
    List<String> goodsTitle;
    List<String> approxLoadTitle;
    private String goodsTypeId, approxLoadId;


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
        View view = inflater.inflate(R.layout.fragment_book_load_fragment_for_mill_user, container, false);

        spGoodsType = view.findViewById(R.id.GoodsTypesSpinner);
        spApproxWeightOfGoods = view.findViewById(R.id.ApproxloadWeightSpinner);
        edtSourceLOcationBookLoad = view.findViewById(R.id.edtSourceLocationBookLoad);
        edtDropLocationBookload = view.findViewById(R.id.edtDropLocationBookLoad);
        sourceLatLng = view.findViewById(R.id.sourceLatLng);
        destLatLng = view.findViewById(R.id.destLatLng);
        btnBookNow = view.findViewById(R.id.BtnBookNow);


        Places.initialize(millActivity, "AIzaSyDRMI4wJHUfwtsX3zoNqVaTReXyHtIAT6U");

        if (!Places.isInitialized()) {
            Places.initialize(millActivity, "AIzaSyDRMI4wJHUfwtsX3zoNqVaTReXyHtIAT6U");
        }

        onClickListener();
        goodsTypesSpinnerOperation();
        approxWeightOfGoodsSpinOperation();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        millActivity = (DashBoardActivity) getActivity();
    }


    public void onClickListener() {
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

                Intent intent = new Intent(getActivity(), DashBoardActivity.class);
                startActivity(intent);

                getActivity().finish();

            }
        });


        spGoodsType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (goodsTypesDropDownResponse != null && goodsTypesDropDownResponse.getData() != null
                        && goodsTypesDropDownResponse.getData().getGoodTypeData() != null
                        && goodsTypesDropDownResponse.getData().getGoodTypeData().size() > 0) {
                    goodsTypeId = goodsTypesDropDownResponse.getData().getGoodTypeData().get(position).getGoodTypeId();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(millActivity, "Nothing item selected for  goods type", Toast.LENGTH_SHORT).show();

            }
        });

        spApproxWeightOfGoods.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (approxLoadDropDownResponse!=null && approxLoadDropDownResponse.getData()!=null
                && approxLoadDropDownResponse.getData().getApproxLoadData()!=null
                && approxLoadDropDownResponse.getData().getApproxLoadData().size()>0){

                    approxLoadId = approxLoadDropDownResponse.getData().getApproxLoadData().get(position).getApproxLoadId();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(millActivity, "Nothing item selected for approx book load", Toast.LENGTH_SHORT).show();

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
                    if (mMap != null && place.getLatLng() != null) {

                        LatLng latLng = place.getLatLng();
                        sourceName = place.getName();
                        sourceLatitude = latLng.latitude;
                        sourceLongitude = latLng.longitude;
                        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 14);
                        mMap.moveCamera(cameraUpdate);

                        // if (sourceLatitude > 0 && destLatitude > 0)
                        //  openBookingActivity();
                    }
                }
            } else if (requestCode == AUTOCOMPLETE_REQUEST_CODE_DEST) {

                Place placeDest = Autocomplete.getPlaceFromIntent(data);

                if (!TextUtils.isEmpty(placeDest.getName())) {
                    Log.i("TAG", "Place: " + placeDest.getName() + ", " + placeDest.getId());
                    if (TextUtils.isEmpty(placeDest.getAddress())) {
                        edtDropLocationBookload.setText("" + placeDest.getName());
                    } else {
                        edtDropLocationBookload.setText(placeDest.getName() + "" + placeDest.getAddress());
                    }
                    if (mMap != null && placeDest.getLatLng() != null) {

                        LatLng latLng = placeDest.getLatLng();

                        destName = placeDest.getName();
                        destLatitude = latLng.latitude;
                        destLongitude = latLng.longitude;
                        //  if (sourceLatitude > 0 && destLatitude > 0)
                        // openBookingActivity();
                    }

                }
            }
        } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
            Status status = Autocomplete.getStatusFromIntent(data);
            Log.i("TAG", status.getStatusMessage());
        } else if (resultCode == RESULT_CANCELED) {
        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(millActivity,
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
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());


        sourceName = Utils.getAddress(millActivity, latLng);
        sourceLatitude = latLng.latitude;
        sourceLongitude = latLng.longitude;
        edtSourceLOcationBookLoad.setText("" + sourceName);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(14));

        Location location1 = new Location("");
        location1.setLatitude(30.5518691);
        location1.setLongitude(75.6513571);
        // showMarker(location1);
        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }


    public void goodsTypesSpinnerOperation() {
        GoodsTypeDropDownRequest goodsTypeDropDownRequest = new GoodsTypeDropDownRequest();
        userId = HighwayPrefs.getString(getActivity(), Constants.ID);
        goodsTypeDropDownRequest.setUserId(userId);
        RestClient.getGoodsTypesLIst(goodsTypeDropDownRequest, new Callback<GoodsTypesDropDownResponse>() {
            @Override
            public void onResponse(Call<GoodsTypesDropDownResponse> call, Response<GoodsTypesDropDownResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus()) {
                        goodsTypesDropDownResponse = response.body();
                        Data data = goodsTypesDropDownResponse.getData();
                        GoodTypeDatum goodTypeDatum = new GoodTypeDatum();
                        goodTypeDatum.setGoodTypeTitle("-- Select Googds Types --");
                        data.getGoodTypeData().add(0, goodTypeDatum);

                        if (data != null && data.getGoodTypeData().size() > 0) {
                            goodsTitle = new ArrayList<>();
                            for (GoodTypeDatum goodTypeDatum1 : goodsTypesDropDownResponse.getData().getGoodTypeData()) {
                                goodsTitle.add(goodTypeDatum1.getGoodTypeTitle());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, goodsTitle);
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spGoodsType.setAdapter(dataAdapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GoodsTypesDropDownResponse> call, Throwable t) {
                Toast.makeText(millActivity, "Failed ! - Nothing itmes Show", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void approxWeightOfGoodsSpinOperation() {

        ApproxLoadDropDownRequest approxLoadDropDownRequest = new ApproxLoadDropDownRequest();
        userId = HighwayPrefs.getString(getContext(), Constants.ID);
        approxLoadDropDownRequest.setUserId(userId);

        RestClient.getApproxLoadList(approxLoadDropDownRequest, new Callback<ApproxLoadDropDownResponse>() {
            @Override
            public void onResponse(Call<ApproxLoadDropDownResponse> call, Response<ApproxLoadDropDownResponse> response) {

                if (response.body() != null) {
                    if (response.body().getStatus()) {
                        approxLoadDropDownResponse = response.body();

                        DataModel data = approxLoadDropDownResponse.getData();
                        ApproxLoadDatum approxLoadDatum = new ApproxLoadDatum();
                        approxLoadDatum.setApproxLoadTitle("-- Select Approx Load --");
                        data.getApproxLoadData().add(0, approxLoadDatum);

                        if (data != null && data.getApproxLoadData().size() > 0) {
                            approxLoadTitle = new ArrayList<>();
                            for (ApproxLoadDatum approxLoadDatum1 : approxLoadDropDownResponse.getData().getApproxLoadData()) {
                                approxLoadTitle.add(approxLoadDatum1.getApproxLoadTitle());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, approxLoadTitle);
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spApproxWeightOfGoods.setAdapter(dataAdapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ApproxLoadDropDownResponse> call, Throwable t) {
                Toast.makeText(millActivity, "Nothing Show Approx load weight", Toast.LENGTH_SHORT).show();

            }
        });


    }

}
