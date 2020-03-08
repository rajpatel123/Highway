package com.highway.drivermodule.driverFragment;

import android.Manifest;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.highway.R;
import com.highway.broadCastReceiver.MySenderBroadCast;
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.CancelTrip;
import com.highway.common.base.firebaseService.NotificationPushData;
import com.highway.commonretrofit.RestClient;
import com.highway.drivermodule.driverAdapter.CancelTripAdapterForDriver;
import com.highway.drivermodule.driverModelClass.BookingAcceptRejectData;
import com.highway.drivermodule.driverModelClass.BookingAcceptRejectResponse;
import com.highway.drivermodule.updateTripStatusByDriver.UpdateTripStatusByDriverReq;
import com.highway.drivermodule.updateTripStatusByDriver.UpdateTripStatusByDriverResp;
import com.highway.utils.BaseUtil;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;
import com.highway.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.highway.utils.Constants.ARRIVED;
import static com.highway.utils.Constants.COMPLETED;
import static com.highway.utils.Constants.DROPPED;
import static com.highway.utils.Constants.INVOICE;
import static com.highway.utils.Constants.PICKEDUP;
import static com.highway.utils.Constants.RATING;
import static com.highway.utils.Constants.TRIP_NEW;
import static com.highway.utils.Constants.TRIP_STARTED;


public class IncomingRequestFragmentForDriver extends Fragment implements View.OnClickListener, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {


    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;

    FrameLayout onlineFramelayout;

    RelativeLayout mylocation;
    private DashBoardActivity activity;
    public String TAG = getClass().getSimpleName();
    public Button btnAccept, btnReject, btnCancelTrip, btnTapToDrop, btnArrived;

    public TextView lblCount;
    public CircleImageView imgUser;
    public TextView lblUserName, lblLocationType, locationAddressTV;
    public RatingBar ratingUser;
    public TextView pickupAddress;
    public TextView dropAddress;
    public TextView lblLocationDistance;
    public LinearLayout pickupAddressLayout;
    public LinearLayout dropAddressLayout, lnrLocationHeader;
    public ImageView navigateToMap;
    private SupportMapFragment mapFragment;

    public TextView lblCarType;

    Integer arrivalTime = 0;
    MediaPlayer mPlayer;


    Context context;
    CountDownTimer countDownTimer;

    public List<CancelTrip> cancelTrips = new ArrayList<>();
    public RecyclerView recyclerViewForDriver;
    CancelTripAdapterForDriver cancelTripAdapterForDriver;
    DashBoardActivity dashBoardActivity;
    public int time_to_left = 60;

    View goingtoPickupLocationView, incomingCallView;
    public JSONObject pushData;
    private NotificationPushData data = new NotificationPushData();
    public String userId;


    TextView userName;
    RatingBar userRating;
    ImageView imgCall;
    Button btnCancel;

    CircleImageView statusArrivedImg;
    CircleImageView statusPickedUpImg;
    CircleImageView statusFinishedImg;
    CircleImageView userImg, user_img;
    ImageView imgMsg;


    Button btnpickedUp, btnCancelafterArrived;


    Unbinder unbinder;
    AlertDialog otpDialog;
    AlertDialog KmDialog;
    String STATUS = "";
    Context thisContext;


    MySenderBroadCast mySenderBroadCast = new MySenderBroadCast();

    private BroadcastReceiver mInnerReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("com.highway.broadCastReceiver.ACTION_SEND".equals(intent.getAction())) {
                String intentExtra = intent.getStringExtra("om.highway.EXTRA_DATA");
                btnAccept.setText("inner BroadCast Receiver" + intentExtra);
            }
        }
    };


    public IncomingRequestFragmentForDriver() {
        // Required empty public constructor
    }

    public static IncomingRequestFragmentForDriver newInstance() {
        IncomingRequestFragmentForDriver fragment = new IncomingRequestFragmentForDriver();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            try {
                //pushData = ((DashBoardActivity) getActivity()).pushData;
                // data = BaseUtil.objectFromString(pushData.toString(), NotificationPushData.class);

                data.setDestination("Delhi, Testing location");
                data.setSource("Delhi, Testing location");
                data.setMobile("4638746864");
                data.setTripId("#HIG3345");
                data.setTripId("NEW_TRIP");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e(TAG, BaseUtil.jsonFromModel(pushData));
        }

        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        getActivity().registerReceiver(mySenderBroadCast, intentFilter);

    }

    @Override
    public void onStart() {
        super.onStart();
//        IntentFilter intentFilter = new IntentFilter("com.highway.broadCastReceiver.ACTION_SEND");
//        getActivity().registerReceiver(mInnerReceiver,intentFilter);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //  getActivity().unregisterReceiver(mySenderBroadCast);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_incoming_call, container, false);

        ((DashBoardActivity) getActivity()).appBarLayout.setVisibility(View.GONE);

        userName = view.findViewById(R.id.lblUserName);
        imgCall = view.findViewById(R.id.imgCall);
        btnCancel = view.findViewById(R.id.btnCancel);

        statusArrivedImg = view.findViewById(R.id.status_arrived_img);
        statusPickedUpImg = view.findViewById(R.id.status_picked_up_img);
        statusFinishedImg = view.findViewById(R.id.status_finished_img);
        user_img = view.findViewById(R.id.user_img);
        userImg = view.findViewById(R.id.imgUser);
        imgMsg = view.findViewById(R.id.imgMsg);

        btnTapToDrop = view.findViewById(R.id.tapToDrop);
        btnArrived = view.findViewById(R.id.btnArrived);
        btnCancel = view.findViewById(R.id.btnCancel);
        btnCancelafterArrived = view.findViewById(R.id.btnCancelafterArrived);
        btnpickedUp = view.findViewById(R.id.btnpickedUp);


        goingtoPickupLocationView = view.findViewById(R.id.goingtoPickupLocation);
        incomingCallView = view.findViewById(R.id.incomingCall);
        btnReject = view.findViewById(R.id.btnReject);
        btnAccept = view.findViewById(R.id.btnAccept);
        lblCount = view.findViewById(R.id.lblCount);
        imgUser = view.findViewById(R.id.imgUser);
        lblUserName = view.findViewById(R.id.lblUserName);
        ratingUser = view.findViewById(R.id.ratingUser);
        pickupAddress = view.findViewById(R.id.pickup_address);
        dropAddress = view.findViewById(R.id.drop_address);
        lblLocationDistance = view.findViewById(R.id.lblLocationDistance);
        pickupAddressLayout = view.findViewById(R.id.pickup_address_layout);
        dropAddressLayout = view.findViewById(R.id.drop_address_layout);
        lnrLocationHeader = view.findViewById(R.id.lnrLocationHeader);
        context = getActivity();
        mPlayer = MediaPlayer.create(getActivity(), R.raw.alert_tone);


        btnAccept.setOnClickListener(this);
        btnReject.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnCancelafterArrived.setOnClickListener(this);
        btnTapToDrop.setOnClickListener(this);
        btnArrived.setOnClickListener(this);


        locationAddressTV = view.findViewById(R.id.lblLocationName);
        lblLocationType = view.findViewById(R.id.lblLocationType);
        navigateToMap = view.findViewById(R.id.navigation_img);
        navigateToMap.setVisibility(View.VISIBLE);
        onlineFramelayout = view.findViewById(R.id.online);
        mylocation = view.findViewById(R.id.mylocation);

        mylocation.setOnClickListener(this);
        navigateToMap.setOnClickListener(this);


        mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnpickedUp.setOnClickListener(this);
        init(TRIP_NEW);
        return view;
    }


    void init(String status) {

        switch (status) {
            case TRIP_NEW:
                if (data != null) {
                    if (!mPlayer.isPlaying())
                        mPlayer.start();

                    countDownTimer = new CountDownTimer(time_to_left * 1000, 1000) {
                        public void onTick(long millisUntilFinished) {
                            lblCount.setText(String.valueOf(millisUntilFinished / 1000));
                            setTvZoomInOutAnimation(lblCount);
                        }

                        public void onFinish() {
                            stopMediaPlayer();
                            ((DashBoardActivity) getActivity()).replaceFragment(new DashBoardFragmentForDriver(), "");
                        }
                    };

                    incomingCallView.setVisibility(View.VISIBLE);
                    countDownTimer.start();
                    userId = HighwayPrefs.getString(getContext(), Constants.ID);
                    pickupAddress.setText(data.getSource());
                    dropAddress.setText(data.getDestination());
                }

                break;
            case TRIP_STARTED:
                break;
            case ARRIVED:
                btnArrived.setVisibility(View.GONE);
                btnpickedUp.setVisibility(View.VISIBLE);
                statusArrivedImg.setColorFilter(ContextCompat.getColor(context, R.color.black_disabled),
                        android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case PICKEDUP:
                btnArrived.setVisibility(View.GONE);
                btnpickedUp.setVisibility(View.GONE);
                btnCancel.setVisibility(View.GONE);
                btnTapToDrop.setVisibility(View.VISIBLE);
                locationAddressTV.setText("Drop Location");

                statusArrivedImg.setColorFilter(ContextCompat.getColor(context, R.color.black_disabled),
                        android.graphics.PorterDuff.Mode.MULTIPLY);

                statusPickedUpImg.setColorFilter(ContextCompat.getColor(context, R.color.black_disabled),
                        android.graphics.PorterDuff.Mode.MULTIPLY);

                btnCancelafterArrived.setVisibility(View.GONE);

                break;
            case DROPPED:
                ((DashBoardActivity) getActivity()).replaceFragment(new DriverOnlineFragment(), "");
                ((DashBoardActivity) getActivity()).showBottomSheet();


                break;
            case COMPLETED:
                ((DashBoardActivity) getActivity()).replaceFragment(new DriverOnlineFragment(), "");
                ((DashBoardActivity) getActivity()).showBottomSheet();

                break;
            case RATING:
                ((DashBoardActivity) getActivity()).showratingBottomSheet();

                break;
            case INVOICE:
                break;

        }


    }


    public void setTvZoomInOutAnimation(final TextView textView) {
        final float startSize = 20;
        final float endSize = 13;
        final int animationDuration = 900; // Animation duration in ms

        ValueAnimator animator = ValueAnimator.ofFloat(startSize, endSize);
        animator.setDuration(animationDuration);

        animator.addUpdateListener(valueAnimator -> {
            float animatedValue = (Float) valueAnimator.getAnimatedValue();
            textView.setTextSize(animatedValue);
        });

        animator.setRepeatCount(2);
        animator.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.mylocation:          // for location
                updateMyLocation();
                break;

            case R.id.navigation_img:
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr=28.535517,77.391029&daddr=28.613939,77.209023"));
                startActivity(intent);
                break;

            case R.id.btnReject:
                if (Utils.isInternetConnected(context)) {
                    Utils.showProgressDialog(context);
                    try {

                        acceptRejectBookingTrip(getAcceptRejectBookingTripParams(Constants.NOTIFICATION_TYPE_TRIP_REJECTED), false);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case R.id.btnAccept:
                if (Utils.isInternetConnected(context)) {
                    Utils.showProgressDialog(context);
                    try {

                        acceptRejectBookingTrip(getAcceptRejectBookingTripParams(Constants.NOTIFICATION_TYPE_TRIP_ACCEPTED), true);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case R.id.tapToDrop:
                init(DROPPED);
                break;

            case R.id.btnArrived:
                updateDriverStatus();
                init(ARRIVED);
                break;

            case R.id.btnCancel:

                stopMediaPlayer();
                ((DashBoardActivity) getActivity()).replaceFragment(new DashBoardFragmentForDriver(), "");

                break;

            case R.id.btnpickedUp:

                init(PICKEDUP);
                break;

            case R.id.btnCancelafterArrived:
                stopMediaPlayer();
                ((DashBoardActivity) getActivity()).replaceFragment(new DashBoardFragmentForDriver(), "");

                break;
        }
    }

    private void updateDriverStatus() {

        userId = HighwayPrefs.getString(getActivity(), Constants.ID);
        UpdateTripStatusByDriverReq updateTripStatusByDriverReq = new UpdateTripStatusByDriverReq();
        updateTripStatusByDriverReq.setDriverId(userId);
        updateTripStatusByDriverReq.setTripId("");
        updateTripStatusByDriverReq.setTRIPSTATS("");
        updateTripStatusByDriverReq.setUpdatedAt("");

        RestClient.updateTripStatusByDriver(updateTripStatusByDriverReq, new Callback<UpdateTripStatusByDriverResp>() {
            @Override
            public void onResponse(Call<UpdateTripStatusByDriverResp> call, Response<UpdateTripStatusByDriverResp> response) {
                if (response.body()!=null){
                    if (response.body().getStatus()){

                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateTripStatusByDriverResp> call, Throwable t) {
                Toast.makeText(getActivity(), "failure!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public BookingAcceptRejectData getAcceptRejectBookingTripParams(String acceptReject) throws JSONException {
        BookingAcceptRejectData acceptRejectData = new BookingAcceptRejectData();
//        acceptRejectData.setTripId(pushData.getString(Constants.TRIP_ID));
        acceptRejectData.setTripId(data.getTripId());
        acceptRejectData.setUserId(userId);
        acceptRejectData.setAcceptReject(acceptReject);
        return acceptRejectData;
    }

    public void acceptRejectBookingTrip(BookingAcceptRejectData acceptRejectData, boolean isAccepted) {
        RestClient.acceptRejectBookingTrip(acceptRejectData, new Callback<BookingAcceptRejectResponse>() {
            @Override
            public void onResponse(Call<BookingAcceptRejectResponse> call, Response<BookingAcceptRejectResponse> response) {
                Utils.dismissProgressDialog();

                incomingCallView.setVisibility(View.GONE);
                goingtoPickupLocationView.setVisibility(View.VISIBLE);
                lnrLocationHeader.setVisibility(View.VISIBLE);
                locationAddressTV.setText("Pick UP Location");

                if (response.code() == 200 && response.body() != null) {
                    BookingAcceptRejectResponse resp = response.body();
                    BaseUtil.jsonFromModel(resp);
                    if (isAccepted) {
//                        LatLng latLng = new LatLng(Double.parseDouble(resp.getCustomerDetails().getStartTripLat()),
//                                Double.parseDouble(resp.getCustomerDetails().getStartTripLong()));
//                        //pickupAddress.setText("" + Utils.getAddress(getActivity(), latLng));
//
//                        LatLng latLngD = new LatLng(Double.parseDouble(resp.getCustomerDetails().getEndTripLat()),
//                                Double.parseDouble(resp.getCustomerDetails().getEndTripLong()));
//                        pickupAddress.setText("" + Utils.getAddress(getActivity(), latLng));


                        if (countDownTimer != null) {
                            if (mPlayer != null & mPlayer.isPlaying())
                                mPlayer.stop();
                            countDownTimer.cancel();
                        }
                        btnAccept.setVisibility(View.GONE);
                        btnReject.setVisibility(View.GONE);


                    }

                }
            }

            @Override
            public void onFailure(Call<BookingAcceptRejectResponse> call, Throwable t) {
                Utils.dismissProgressDialog();

            }
        });
    }

    public void rejectTripAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Booking Rejected!")
                .setMessage(message)
                .setPositiveButton("OKAY", (dialog, which) -> {

                })/*
                .setNegativeButton("CANCEL", (dialog, which) -> {

                })*/.show();
    }

//    public void getDistance(LatLng source, LatLng destination) {
//        GoogleDirection.withServerKey(BuildConfig.google_map_key)
//                .from(source)
//                .to(destination)
//                .transportMode(TransportMode.DRIVING)
//                .execute(this);
//    }


    @Override
    public void onStop() {
        super.onStop();
        stopMediaPlayer();
    }

    void stopMediaPlayer() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        stopMediaPlayer();
        //   getActivity().unregisterReceiver(mInnerReceiver);  /// broadcastReceiver
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dashBoardActivity = (DashBoardActivity) getActivity();
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        // mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMinZoomPreference(10.0f);
        mMap.setMaxZoomPreference(18.0f);

//        try {
//            mMap.setMapStyle(
//                    MapStyleOptions.loadRawResourceStyle(getActivity(), R.raw.style));
//        } catch (Resources.NotFoundException e) {
//            e.printStackTrace();
//            // Oops, looks like the map style resource couldn't be found!
//        }
        mMap.getUiSettings().setMyLocationButtonEnabled(false);

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

//            if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION)
//                    != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions((Activity)mContext, new String[]{
//                        android.Manifest.permission.ACCESS_FINE_LOCATION
//                }, 10);
//            }
            if (ContextCompat.checkSelfPermission(getActivity(),
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
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
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
        if (ContextCompat.checkSelfPermission(getActivity(),
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
        mLastLocation = location;
        //Place current location marker
        updateMyLocation();
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


}
