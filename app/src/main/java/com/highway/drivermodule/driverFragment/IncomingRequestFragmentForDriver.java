package com.highway.drivermodule.driverFragment;

import android.Manifest;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
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
import android.os.Handler;
import android.text.TextUtils;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

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
import com.highway.common.base.HighwayApplication;
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.common.base.firebaseService.NotificationPushData;
import com.highway.commonretrofit.RestClient;
import com.highway.drivermodule.driverActivity.LocationTrack;
import com.highway.drivermodule.driverModelClass.BookingAcceptRejectData;
import com.highway.drivermodule.driverModelClass.BookingAcceptRejectResponse;
import com.highway.drivermodule.driverModelClass.update_driver_location.UpdateDriverLocationReqst;
import com.highway.drivermodule.driverModelClass.update_driver_location.UpdateDriverLocationResp;
import com.highway.drivermodule.drivermodels.TripStatus;
import com.highway.drivermodule.updateTripStatusByDriver.UpdateTripStatusByDriverReq;
import com.highway.drivermodule.updateTripStatusByDriver.UpdateTripStatusByDriverResp;
import com.highway.utils.BaseUtil;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;
import com.highway.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static com.highway.utils.Constants.ARRIVED;
import static com.highway.utils.Constants.COMPLETED;
import static com.highway.utils.Constants.DROPPED;
import static com.highway.utils.Constants.INVOICE;
import static com.highway.utils.Constants.PICKEDUP;
import static com.highway.utils.Constants.RATING;
import static com.highway.utils.Constants.TRIP_ACCEPTED;
import static com.highway.utils.Constants.TRIP_CANCELED;
import static com.highway.utils.Constants.TRIP_NEW;


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

    public TextView customer_name;
    MediaPlayer mPlayer;
    Context context;
    CountDownTimer countDownTimer;
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
    String mobileNo;

    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissionsRejected = new ArrayList<>();
    private ArrayList<String> permissions = new ArrayList<>();
    private final static int ALL_PERMISSIONS_RESULT = 101;
    public LocationTrack locationTrack;

    public int TIMECOUNT = 10000;
    public boolean ISTRAVERSING = true;
    public double longitude,latitude;
    Handler handler = new Handler();


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
        if (pushData == null && ((DashBoardActivity) getActivity()).pushData != null) {
            try {
                pushData = ((DashBoardActivity) getActivity()).pushData;
                data = BaseUtil.objectFromString(pushData.toString(), NotificationPushData.class);
                HighwayApplication.getInstance().setCurrentTripId(data.getTripId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            data = new NotificationPushData();
            TripStatus tripStatus = HighwayApplication.getInstance().getTripStatus();
            if (tripStatus != null) {
                data.setTripId(tripStatus.getBookingTripId());
                data.setMobile(tripStatus.getMobile());
                data.setSource("" + tripStatus.getSourceAddress());
                data.setDestination("" + tripStatus.getDestinationAddress());
                data.setCustomer("" + tripStatus.getMobile());
                data.setName("" + tripStatus.getName());
                mobileNo = data.getMobile();
                data.setType(tripStatus.getCurrentTripStatus());
                HighwayApplication.getInstance().setCurrentTripId(data.getTripId());

            }

        }
        Log.e(TAG, BaseUtil.jsonFromModel(pushData));


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
        getActivity().unregisterReceiver(mySenderBroadCast);
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
        customer_name = view.findViewById(R.id.customer_name);
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
        imgCall.setOnClickListener(this);


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
        init(data.getType());

        gpsTrackingWithOutServiceClass();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.removeCallbacks(sendData);
                ISTRAVERSING = false;

                gpsTrackingWithOutServiceClass();
            }
        }, 5000);                ///  update gps location

        return view;
    }

    /////////////location update//////////////////////

    private void gpsTrackingWithOutServiceClass() {
        permissions.add(ACCESS_FINE_LOCATION);
        permissions.add(ACCESS_COARSE_LOCATION);

        permissionsToRequest = findUnAskedPermissions(permissions);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (permissionsToRequest.size() > 0)
                requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
        }

        locationTrack = new LocationTrack(getActivity());

        if (locationTrack.canGetLocation()) {

            longitude = locationTrack.getLongitude();
            latitude = locationTrack.getLatitude();
//            Toast.makeText(getActivity(), "Longitude:" + Double.toString(longitude) + "\n" +
//                    "Latitude:" + Double.toString(latitude), Toast.LENGTH_SHORT).show();

            ISTRAVERSING = true;
            handler.postDelayed(sendData, TIMECOUNT);

        } else {

            locationTrack.showSettingsAlert();
        }
    }


    private Runnable sendData = new Runnable() {
        public void run() {
            try {
                //prepare and send the data here..
                // gpsTrackingWithServiceClass();
                if (ISTRAVERSING) {
                    handler.postDelayed(sendData, TIMECOUNT);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    private ArrayList<String> findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList<String> result = new ArrayList<String>();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    private boolean hasPermission(String permission) {
        if (canMakeSmores()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (getActivity().checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {

            case ALL_PERMISSIONS_RESULT:
                for (String perms : permissionsToRequest) {
                    if (!hasPermission(perms)) {
                        permissionsRejected.add(perms);
                    }
                }

                if (permissionsRejected.size() > 0) {


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                            showMessageOKCancel("These permissions are mandatory for the application. Please allow access.",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(permissionsRejected.toArray(new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                            }
                                        }
                                    });
                            return;
                        }
                    }

                }

                break;
        }

    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(getActivity())
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
    /////////////location update//////////////////////

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
                            ((DashBoardActivity) getActivity()).replaceFragment(DriverOnlineFragment.newInstance(), "");
                        }
                    };

                    incomingCallView.setVisibility(View.VISIBLE);
                    pickupAddress.setText("" + data.getSource());
                    dropAddress.setText("" + data.getSource());
                    userName.setText(data.getCustomer());
                    mobileNo = data.getMobile();

                    countDownTimer.start();
                    userId = HighwayPrefs.getString(getContext(), Constants.ID);
                    pickupAddress.setText(data.getSource());
                    dropAddress.setText(data.getDestination());
                }

                break;
            case TRIP_ACCEPTED:
                incomingCallView.setVisibility(View.GONE);
                goingtoPickupLocationView.setVisibility(View.VISIBLE);
                lnrLocationHeader.setVisibility(View.VISIBLE);
                locationAddressTV.setText(data.getSource());
                lblLocationType.setText("PickUp Location");
                lblLocationType.setVisibility(View.VISIBLE);

                customer_name.setText(data.getCustomer());
                STATUS = TRIP_ACCEPTED;

                break;
            case ARRIVED:
                lnrLocationHeader.setVisibility(View.VISIBLE);
                incomingCallView.setVisibility(View.GONE);
                btnCancelafterArrived.setVisibility(View.VISIBLE);
                goingtoPickupLocationView.setVisibility(View.VISIBLE);
                btnArrived.setVisibility(View.GONE);
                btnpickedUp.setVisibility(View.VISIBLE);
                lblLocationType.setText("Pick Up Location");
                lblLocationType.setVisibility(View.VISIBLE);

                statusArrivedImg.setColorFilter(ContextCompat.getColor(context, R.color.black_disabled),
                        android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case PICKEDUP:
                btnArrived.setVisibility(View.GONE);
                btnpickedUp.setVisibility(View.GONE);
                btnCancel.setVisibility(View.GONE);
                btnTapToDrop.setVisibility(View.VISIBLE);
                locationAddressTV.setText(data.getDestination());
                lblLocationType.setText("Drop Location");
                incomingCallView.setVisibility(View.GONE);
                goingtoPickupLocationView.setVisibility(View.VISIBLE);
                lblLocationType.setVisibility(View.VISIBLE);
                lnrLocationHeader.setVisibility(View.VISIBLE);

                statusArrivedImg.setColorFilter(ContextCompat.getColor(context, R.color.black_disabled),
                        android.graphics.PorterDuff.Mode.MULTIPLY);

                statusPickedUpImg.setColorFilter(ContextCompat.getColor(context, R.color.black_disabled),
                        android.graphics.PorterDuff.Mode.MULTIPLY);

                btnCancelafterArrived.setVisibility(View.GONE);

               // updateDriverLocation();  ///////////  driver continuously location updation

                break;
            case DROPPED:
                ((DashBoardActivity) getActivity()).replaceFragment(DriverOnlineFragment.newInstance(), "");
                ((DashBoardActivity) getActivity()).showInvoiceBottomSheetDriver();


                break;
            case COMPLETED:
                ((DashBoardActivity) getActivity()).replaceFragment(DriverOnlineFragment.newInstance(), "");
                ((DashBoardActivity) getActivity()).showInvoiceBottomSheetDriver();

                break;
            case RATING:
                ((DashBoardActivity) getActivity()).replaceFragment(DriverOnlineFragment.newInstance(), "");
                ((DashBoardActivity) getActivity()).showratingBottomSheetDriver();
                break;
            case INVOICE:
                ((DashBoardActivity) getActivity()).replaceFragment(DriverOnlineFragment.newInstance(), "");
                ((DashBoardActivity) getActivity()).showratingBottomSheetDriver();
                break;

        }
    }

    private void updateDriverLocation() {

        UpdateDriverLocationReqst updateDriverLocationReqst = new UpdateDriverLocationReqst();
        userId = HighwayPrefs.getString(getActivity(),Constants.ID);
        updateDriverLocationReqst.setDriverId(userId);
        updateDriverLocationReqst.setLat(latitude);
        updateDriverLocationReqst.setLong(longitude);

        RestClient.getUpdateDriverLocation(updateDriverLocationReqst, new Callback<UpdateDriverLocationResp>() {
            @Override
            public void onResponse(Call<UpdateDriverLocationResp> call, Response<UpdateDriverLocationResp> response) {
                if (response!=null && response.code()==200 && response.body()!=null){
                    if (response.body().getStatus()){

                    }
                }

            }

            @Override
            public void onFailure(Call<UpdateDriverLocationResp> call, Throwable t) {
                Toast.makeText(getActivity(), "gps location failed", Toast.LENGTH_SHORT).show();
            }
        });


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
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + locationAddressTV.getText().toString());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                break;

            case R.id.btnReject:
                if (Utils.isInternetConnected(context)) {
                    //Utils.showProgressDialog(context);

                    confirmPopup("Are you sure want to reject?", true);
//                    try {
//                       // acceptRejectBookingTrip(getAcceptRejectBookingTripParams(Constants.NOTIFICATION_TYPE_TRIP_REJECTED), false);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
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
                confirmPopup("Are you sure want to drop?", false);
                break;

            case R.id.btnArrived:
                updateDriverStatus(ARRIVED, data.getTripId());
                STATUS = ARRIVED;
                init(ARRIVED);
                break;

            case R.id.btnCancel:
                updateDriverStatus(TRIP_CANCELED, data.getTripId());
                stopMediaPlayer();
                ((DashBoardActivity) getActivity()).replaceFragment(DriverOnlineFragment.newInstance(), "");

                break;

            case R.id.btnpickedUp:
                updateDriverStatus(PICKEDUP, data.getTripId());
                STATUS = PICKEDUP;
                init(PICKEDUP);
                break;

            case R.id.btnCancelafterArrived:
                STATUS = TRIP_CANCELED;
                updateDriverStatus(TRIP_CANCELED, data.getTripId());
                stopMediaPlayer();
                ((DashBoardActivity) getActivity()).replaceFragment(DriverOnlineFragment.newInstance(), "");

                break;

            case R.id.imgCall:
                if (!TextUtils.isEmpty(mobileNo)) {
                    callTask();
                }

                break;
        }
    }

    void confirmPopup(String message, boolean isReject) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(dashBoardActivity);
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("YES", (dialog, id) -> {
                    if (isReject) {
                        ((DashBoardActivity) getActivity()).replaceFragment(DriverOnlineFragment.newInstance(), "");
                    } else {
                        STATUS = DROPPED;
                        updateDriverStatus(DROPPED, data.getTripId());
                        init(DROPPED);
                    }
                })
                .setNegativeButton("NO", (dialog, id) -> dialog.cancel());
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void callTask() {
        if (isPermissionGranted()) {
            call_action();
        }
    }

    private void call_action() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + data.getMobile()));
        startActivity(intent);
    }

    public boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (getActivity().checkSelfPermission(android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission is granted");
                return true;
            } else {
                Log.v("TAG", "Permission is revoked");
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE,}, 1);
                return false;
            }
        } else {
            Log.v("TAG", "Permission is granted");
            return true;
        }
    }



    private void updateDriverStatus(String status, String tripId) {

        userId = HighwayPrefs.getString(getActivity(), Constants.ID);
        UpdateTripStatusByDriverReq updateTripStatusByDriverReq = new UpdateTripStatusByDriverReq();
        updateTripStatusByDriverReq.setDriverId(userId);
        updateTripStatusByDriverReq.setTripId(tripId);
        updateTripStatusByDriverReq.setTRIPSTATS(status);
        updateTripStatusByDriverReq.setUpdatedAt("" + System.currentTimeMillis());

        RestClient.updateTripStatusByDriver(updateTripStatusByDriverReq, new Callback<UpdateTripStatusByDriverResp>() {
            @Override
            public void onResponse(Call<UpdateTripStatusByDriverResp> call, Response<UpdateTripStatusByDriverResp> response) {
                if (response.body() != null) {
                    if (response.body().getStatus()) {


                        Log.d("Status Update", "" + response.body().getMessage());
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
        acceptRejectData.setDriverId(userId);
        acceptRejectData.setStatus(acceptReject);
        acceptRejectData.setUpdatedAt("" + System.currentTimeMillis());
        return acceptRejectData;
    }

    public void acceptRejectBookingTrip(BookingAcceptRejectData acceptRejectData, boolean isAccepted) {
        RestClient.acceptRejectBookingTrip(acceptRejectData, new Callback<BookingAcceptRejectResponse>() {
            @Override
            public void onResponse(Call<BookingAcceptRejectResponse> call, Response<BookingAcceptRejectResponse> response) {
                Utils.dismissProgressDialog();


                if (response.code() == 200 && response.body() != null) {
                    BookingAcceptRejectResponse resp = response.body();
                    BaseUtil.jsonFromModel(resp);

                    if (!resp.getStatus()) {
                        stopMediaPlayer();
                        ((DashBoardActivity) getActivity()).replaceFragment(DriverOnlineFragment.newInstance(), "");
                    }

                    if (isAccepted) {

                        incomingCallView.setVisibility(View.GONE);
                        goingtoPickupLocationView.setVisibility(View.VISIBLE);
                        lnrLocationHeader.setVisibility(View.VISIBLE);
                        locationAddressTV.setText(data.getSource());
                        lblLocationType.setText("PickUp Location");
                        customer_name.setText(data.getCustomer());
                        STATUS = TRIP_ACCEPTED;

                        if (countDownTimer != null) {
                            if (mPlayer != null && mPlayer.isPlaying())
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
        locationTrack.stopListener();

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
        if (ContextCompat.checkSelfPermission(dashBoardActivity,
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

    private void showAlertDiolog(String message) {

        final android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(dashBoardActivity);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_show_alert_dialog_driver_not_responding, null);
        dialogBuilder.setView(dialogView);

        final android.app.AlertDialog dialog = dialogBuilder.create();
        Button done = dialogView.findViewById(R.id.btn_done);

        TextView text_cancel = dialogView.findViewById(R.id.btnCancel);
        text_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        try {

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (!dashBoardActivity.isFinishing())
                dialogBuilder.create().show();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

}
