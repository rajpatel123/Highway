package com.highway.customer;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.highway.R;
import com.highway.common.base.HighwayApplication;
import com.highway.commonretrofit.RestClient;
import com.highway.customer.customerActivity.BookingVehicleAdapter;
import com.highway.customer.customerActivity.BookingWithDetailsActivity;
import com.highway.customer.customerActivity.GoodsTypeDetailActivity;
import com.highway.customer.customerFragment.GoodsTypeDialogFragmentForCustomer;
import com.highway.customer.customerFragment.InvoiceBottomDialogFragmentForCustomer;
import com.highway.customer.customerModelClass.bookingVehicleList.BookingVehicleListRequest;
import com.highway.customer.customerModelClass.bookingVehicleList.BookingVehicleListResponse;
import com.highway.customer.customerModelClass.bookingVehicleList.VehicleList;
import com.highway.customer.customerModelClass.selectYoursGoodsType.GoodsTypeDataRequest;
import com.highway.customer.customerModelClass.selectYoursGoodsType.GoodsTypeDataResponse;
import com.highway.drivermodule.driverFragment.InvoiceBottomDialogFragmentForDriver;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.VISIBLE;

public class BookingProcessActivtiy extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,BookingVehicleAdapter.OnClickEvents {
    private View pickup_tab_layoutView;
    private View delivery_tab_layoutView;
    private View shipment_tab_layoutView;
    private RelativeLayout deleveryRl;
    private ScrollView shipmentScrollView;

    int AUTOCOMPLETE_REQUEST_CODE_DEST = 2;
    int AUTOCOMPLETE_REQUEST_CODE_DELIVERY_CONTACT = 3;
    int AUTOCOMPLETE_REQUEST_CODE_PICKUP = 5;
    BookingVehicleAdapter bookingVehicleAdapter;
    BookingVehicleListResponse bookingVehicleListResponse;
    List<VehicleList> vehicleList = new ArrayList<>();

    ///tab views
    private TextView pickupTv;
    private View pickVV;

    private TextView deliveryTv;
    private View deliveryVV;

    private TextView shipmentTv;
    private View shipmentVV;

    private TextView paymentTv;
    private View paymentVV;


    //pickupTab feilds

    public TextView pickupAddress, contactNameTv, contactNumberTv,goodsTpeSpinner,goodsSizeSpinner;
    RadioGroup contactRadioGP;
    ImageView pickContact;
    Button proceedBtnPick;


    //Delivery Views
    TextView deliveryAddressTv, deliveryContactNameTv, deliveryContactNumberTv,edtSourceLocationBottomSheet;
    RadioGroup deliveryContactRadioGP;
    ImageView selectContactDelivery;

    RelativeLayout deliveryMapRl;
    RecyclerView vehicleListRV;
    LinearLayout deliveryRl, selectLocationLL;
    Button deliveryProceedBtn,proceedBtn;


    private double destLatitude, destLongitude;
    private String destName;
    public String goodsType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_process_activtiy);

        initViews();


    }

    private void initViews() {
        pickup_tab_layoutView = findViewById(R.id.pickup_tab_layoutView);
        delivery_tab_layoutView = findViewById(R.id.delivery_tab_layoutView);
        shipment_tab_layoutView = findViewById(R.id.shipment_tab_layoutView);

        deleveryRl = findViewById(R.id.deleveryRl);
        shipmentScrollView = findViewById(R.id.shipmentScrollView);


        pickupTv = findViewById(R.id.pickupTv);
        pickVV = findViewById(R.id.pickupVV);


        deliveryTv = findViewById(R.id.deliveryTv);
        deliveryVV = findViewById(R.id.deliveryVV);


        shipmentTv = findViewById(R.id.shipmentTv);
        shipmentVV = findViewById(R.id.shipmentVV);


        paymentTv = findViewById(R.id.paymentTv);
        paymentVV = findViewById(R.id.paymentVV);

        //pcickUpcontact     views
        pickupAddress = findViewById(R.id.pickupAddress);
        pickContact = findViewById(R.id.pickContact);
        contactNameTv = findViewById(R.id.nameEdit);
        contactNumberTv = findViewById(R.id.mobileNumber);
        contactRadioGP = findViewById(R.id.contactRadioGP);
        proceedBtnPick = findViewById(R.id.proceedBtnPick);


        //delieryViews

        deliveryAddressTv = findViewById(R.id.deliveryAddress);
        deliveryContactNameTv = findViewById(R.id.nameDeliveryEdit);
        deliveryContactNumberTv = findViewById(R.id.mobileDeliveryNumber);
        selectContactDelivery = findViewById(R.id.selectContact);
        edtSourceLocationBottomSheet = findViewById(R.id.edtSourceLOcation);

        deliveryContactRadioGP = findViewById(R.id.deliveryContactRadioGP);
        deliveryProceedBtn = findViewById(R.id.deliveryProceedBtn);


        deliveryMapRl = findViewById(R.id.deliveryMapRl);
        proceedBtn = findViewById(R.id.proceedBtn);
        deliveryRl = findViewById(R.id.deliveryRl);
        selectLocationLL = findViewById(R.id.sourceLL);



        //shipment
        goodsTpeSpinner = findViewById(R.id.goodsTpeSpinner);
        goodsSizeSpinner = findViewById(R.id.goodsSizeSpinner);
        vehicleListRV = findViewById(R.id.vehicleListRV);




        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        updateTabVisibilty();




        goodsTpeSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsTypeDialogFragmentForCustomer goodsTypeDialogFragmentForCustomer =
                        GoodsTypeDialogFragmentForCustomer.newInstance();
                goodsTypeDialogFragmentForCustomer.setCancelable(false);
                goodsTypeDialogFragmentForCustomer.show(getSupportFragmentManager(),
                        InvoiceBottomDialogFragmentForDriver.TAG);
            }
        });


        goodsSizeSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsSizeDialogFragmentForCustomer goodsTypeDialogFragmentForCustomer =
                        GoodsSizeDialogFragmentForCustomer.newInstance();
                goodsTypeDialogFragmentForCustomer.setCancelable(false);
                goodsTypeDialogFragmentForCustomer.show(getSupportFragmentManager(),
                        InvoiceBottomDialogFragmentForDriver.TAG);
            }
        });

        pickContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        selectContactDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        contactRadioGP.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.myContact:
                        contactNameTv.setText("" + HighwayPrefs.getString(BookingProcessActivtiy.this, Constants.NAME));
                        contactNumberTv.setText("" + HighwayPrefs.getString(BookingProcessActivtiy.this, Constants.USERMOBILE));
                        break;
                    case R.id.someoneElse:
                        contactNameTv.setText("");
                        contactNumberTv.setText("");

                        break;
                }


            }
        });


        deliveryContactRadioGP.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.myDeliveryContact:
                        deliveryContactNameTv.setText("" + HighwayPrefs.getString(BookingProcessActivtiy.this, Constants.NAME));
                        deliveryContactNumberTv.setText("" + HighwayPrefs.getString(BookingProcessActivtiy.this, Constants.USERMOBILE));
                        break;
                    case R.id.deliverySomeoneElse:
                        deliveryContactNameTv.setText("");
                        deliveryContactNumberTv.setText("");

                        break;
                }


            }
        });


        proceedBtnPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickup_tab_layoutView.setVisibility(View.GONE);
                delivery_tab_layoutView.setVisibility(VISIBLE);
                deliveryMapRl.setVisibility(VISIBLE);

                pickupTv.setTextColor(getResources().getColor(R.color.green));
                pickVV.setBackgroundColor(getResources().getColor(R.color.green));

                deliveryTv.setTextColor(getResources().getColor(R.color.black));
                deliveryVV.setBackgroundColor(getResources().getColor(R.color.black));

                HighwayApplication.getInstance().getBookingHTripRequest().setTripRecevirName(contactNameTv.getText().toString());
                HighwayApplication.getInstance().getBookingHTripRequest().setTripRecevirNumber(contactNumberTv.getText().toString());
            }
        });
        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickup_tab_layoutView.setVisibility(View.GONE);
                delivery_tab_layoutView.setVisibility(VISIBLE);
                deliveryMapRl.setVisibility(View.GONE);
                deliveryRl.setVisibility(VISIBLE);
                deliveryProceedBtn.setVisibility(VISIBLE);
                deliveryAddressTv.setText(""+destName);

                HighwayApplication.getInstance().getBookingHTripRequest().setDestAddress(destName);
                HighwayApplication.getInstance().getBookingHTripRequest().setDestLat(destLongitude);
                HighwayApplication.getInstance().getBookingHTripRequest().setDestLong(destLongitude);


                pickupTv.setTextColor(getResources().getColor(R.color.green));
                pickVV.setBackgroundColor(getResources().getColor(R.color.green));

                deliveryTv.setTextColor(getResources().getColor(R.color.black));
                deliveryVV.setBackgroundColor(getResources().getColor(R.color.black));

            }
        });


        deliveryProceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickup_tab_layoutView.setVisibility(View.GONE);
                delivery_tab_layoutView.setVisibility(View.GONE);
                shipment_tab_layoutView.setVisibility(VISIBLE);
                deliveryMapRl.setVisibility(View.GONE);
                deliveryRl.setVisibility(VISIBLE);
                deliveryProceedBtn.setVisibility(VISIBLE);
                deliveryAddressTv.setText(""+destName);

                HighwayApplication.getInstance().getBookingHTripRequest().setTripRecevirName(deliveryContactNameTv.getText().toString());
                HighwayApplication.getInstance().getBookingHTripRequest().setTripRecevirNumber(deliveryContactNumberTv.getText().toString());
                HighwayApplication.getInstance().getBookingHTripRequest().setDestLat(destLongitude);
                HighwayApplication.getInstance().getBookingHTripRequest().setDestLong(destLongitude);


                pickupTv.setTextColor(getResources().getColor(R.color.green));
                pickVV.setBackgroundColor(getResources().getColor(R.color.green));

                deliveryTv.setTextColor(getResources().getColor(R.color.green));
                deliveryVV.setBackgroundColor(getResources().getColor(R.color.green));

                shipmentTv.setTextColor(getResources().getColor(R.color.black));
                shipmentVV.setBackgroundColor(getResources().getColor(R.color.black));

                showBookingVehicleListRv();
                getBookingVehicle();

            }
        });




        selectLocationLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);

                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.FULLSCREEN, fields)
                        .setCountry("IN")
                        .build(BookingProcessActivtiy.this);
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE_DEST);
            }
        });

    }

    private void updateTabVisibilty() {
        pickupTv.setTextColor(getResources().getColor(R.color.black));
        pickVV.setBackgroundColor(getResources().getColor(R.color.black));


        contactNameTv.setText("" + HighwayPrefs.getString(BookingProcessActivtiy.this, Constants.NAME));
        contactNumberTv.setText("" + HighwayPrefs.getString(BookingProcessActivtiy.this, Constants.USERMOBILE));
        pickupAddress.setText("" + HighwayApplication.getInstance().getBookingHTripRequest().getSourceAddress());
        pickup_tab_layoutView.setVisibility(VISIBLE);


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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == AUTOCOMPLETE_REQUEST_CODE_DEST) {
                Place placeDest = Autocomplete.getPlaceFromIntent(data);

                if (!TextUtils.isEmpty(placeDest.getName())) {

                    if (TextUtils.isEmpty(placeDest.getAddress())) {
                        edtSourceLocationBottomSheet.setText("" + placeDest.getName());
                    } else {
                        edtSourceLocationBottomSheet.setText(placeDest.getName() + "" + placeDest.getAddress());
                    }
                    if (placeDest.getLatLng() != null) {
                        LatLng latLng = placeDest.getLatLng();

                        destName = placeDest.getName();
                        destLatitude = latLng.latitude;
                        destLongitude = latLng.longitude;
                    }

                }
            } else if (requestCode == AUTOCOMPLETE_REQUEST_CODE_DELIVERY_CONTACT) {
            } else if (requestCode == AUTOCOMPLETE_REQUEST_CODE_PICKUP) {
            } else {

                throw new IllegalStateException("Unexpected value: " + requestCode);
            }
        }


    }

    public void getBookingVehicle() {

        BookingVehicleListRequest bookingVehicleListRequest = new BookingVehicleListRequest();

        String user_Id = HighwayPrefs.getString(getApplicationContext(), Constants.ID);

        HighwayApplication.getInstance().getBookingHTripRequest().setUserId(user_Id);
        bookingVehicleListRequest.setUserId(user_Id);

        bookingVehicleListRequest.setStartLat(Double.parseDouble(String.valueOf(HighwayApplication.getInstance().getBookingHTripRequest().getSourceLat())));
        bookingVehicleListRequest.setStartLong(Double.parseDouble(String.valueOf(HighwayApplication.getInstance().getBookingHTripRequest().getSourceLong())));
        bookingVehicleListRequest.setEndLat(Double.parseDouble(String.valueOf(HighwayApplication.getInstance().getBookingHTripRequest().getDestLat())));
        bookingVehicleListRequest.setEndLong(Double.parseDouble(String.valueOf(HighwayApplication.getInstance().getBookingHTripRequest().getDestLong())));

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
                        bookingVehicleAdapter.setOnClickEvents(BookingProcessActivtiy.this);
                        bookingVehicleAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<BookingVehicleListResponse> call, Throwable t) {
                Toast.makeText(BookingProcessActivtiy.this, "Booking Vehicle list Response failed", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void showBookingVehicleListRv() {

        bookingVehicleAdapter = new BookingVehicleAdapter(bookingVehicleListResponse, getApplicationContext(), this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, true);
        vehicleListRV.setLayoutManager(mLayoutManager);
        vehicleListRV.setItemAnimator(new DefaultItemAnimator());
        bookingVehicleAdapter.setOnClickEvents(this);
        vehicleListRV.setAdapter(bookingVehicleAdapter);
    }


    @Override
    public void onCLickInfo(int position) {

         /* if (vehicleList != null && vehicleList.size() > 0)
            showInfoDialog(vehicleInfoList.get(position));  */

        if (bookingVehicleListResponse.getVehicleData().getVehicleList() != null
                && bookingVehicleListResponse.getVehicleData().getVehicleList().size() > 0) {
            //showInfoDialog(bookingVehicleListResponse.getVehicleData().getVehicleList().get(position).getVInfo());

        }
    }

    @Override
    public void onCLickTruck(int position, String fare) {

        if (vehicleList != null && vehicleList.size() > 0) {
            //bookTruckTv.setText("BOOK " + vehicleList.get(position).getVehicleName());
            HighwayApplication.getInstance().getBookingHTripRequest().setVehicleTypeId(vehicleList.get(position).getVehicleId());
            HighwayApplication.getInstance().getBookingHTripRequest().setTripFare(fare);
            HighwayPrefs.putString(getApplicationContext(),"bookVehicleName",vehicleList.get(position).getVehicleName()); // send vehicle name on booking conformed
        }
    }
}