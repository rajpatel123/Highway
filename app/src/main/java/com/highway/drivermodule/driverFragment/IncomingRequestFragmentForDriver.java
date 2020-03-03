package com.highway.drivermodule.driverFragment;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.highway.R;
import com.highway.broadCastReceiver.MySenderBroadCast;
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.CancelTrip;
import com.highway.commonretrofit.RestClient;
import com.highway.drivermodule.driverAdapter.CancelTripAdapterForDriver;
import com.highway.drivermodule.driverModelClass.BookingAcceptRejectData;
import com.highway.drivermodule.driverModelClass.BookingAcceptRejectResponse;
import com.highway.utils.BaseUtil;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;
import com.highway.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IncomingRequestFragmentForDriver extends Fragment implements View.OnClickListener {

    public String TAG = getClass().getSimpleName();
    public Button btnReject;
    public Button btnAccept,btnStartTrip;
    public TextView lblCount;
    public CircleImageView imgUser;
    public TextView lblUserName;
    public RatingBar ratingUser;
    public TextView pickupAddress;
    public TextView dropAddress;
    public TextView lblLocationDistance;
    public LinearLayout pickupAddressLayout;
    public LinearLayout dropAddressLayout;

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
    public JSONObject pushData;
    public String userId;

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
                pushData = ((DashBoardActivity) getActivity()).pushData;
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

        btnReject = view.findViewById(R.id.btnReject);
        btnAccept = view.findViewById(R.id.btnAccept);
        btnStartTrip = view.findViewById(R.id.btnStartTrip);
        lblCount = view.findViewById(R.id.lblCount);
        imgUser = view.findViewById(R.id.imgUser);
        lblUserName = view.findViewById(R.id.lblUserName);
        ratingUser = view.findViewById(R.id.ratingUser);
        pickupAddress = view.findViewById(R.id.pickup_address);
        dropAddress = view.findViewById(R.id.drop_address);
        lblLocationDistance = view.findViewById(R.id.lblLocationDistance);
        pickupAddressLayout = view.findViewById(R.id.pickup_address_layout);
        dropAddressLayout = view.findViewById(R.id.drop_address_layout);
        context = getActivity();
        mPlayer = MediaPlayer.create(getActivity(), R.raw.alert_tone);
        init();



        btnAccept.setOnClickListener(this);
        btnReject.setOnClickListener(this);
        btnStartTrip.setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dashBoardActivity = (DashBoardActivity) getActivity();
    }

    void init() {
        if (!mPlayer.isPlaying())
            mPlayer.start();

        countDownTimer = new CountDownTimer(time_to_left * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                lblCount.setText(String.valueOf(millisUntilFinished / 1000));
                setTvZoomInOutAnimation(lblCount);
            }

            public void onFinish() {
                stopMediaPlayer();
                ((DashBoardActivity)getActivity()).replaceFragment(new DashBoardFragmentForDriver());
            }
        };

        countDownTimer.start();
        userId = HighwayPrefs.getString(getContext(), Constants.ID);
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
            case R.id.btnReject:
                if (Utils.isInternetConnected(context)) {
                    Utils.showProgressDialog(context);
                    try {
                        if (countDownTimer != null) {
                            if (mPlayer != null & mPlayer.isPlaying())
                                mPlayer.stop();
                                countDownTimer.cancel();

                        }
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
                        if (countDownTimer != null) {
                            if (mPlayer != null & mPlayer.isPlaying())
                                mPlayer.stop();
                            countDownTimer.cancel();
                        }
                        acceptRejectBookingTrip(getAcceptRejectBookingTripParams(Constants.NOTIFICATION_TYPE_TRIP_ACCEPTED), true);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    public BookingAcceptRejectData getAcceptRejectBookingTripParams(String acceptReject) throws JSONException {
        BookingAcceptRejectData acceptRejectData = new BookingAcceptRejectData();
        acceptRejectData.setTripId(pushData.getString(Constants.TRIP_ID));
        acceptRejectData.setUserId(userId);
        acceptRejectData.setAcceptReject(acceptReject);
        return acceptRejectData;
    }

    public void acceptRejectBookingTrip(BookingAcceptRejectData acceptRejectData, boolean isAccepted) {
        RestClient.acceptRejectBookingTrip(acceptRejectData, new Callback<BookingAcceptRejectResponse>() {
            @Override
            public void onResponse(Call<BookingAcceptRejectResponse> call, Response<BookingAcceptRejectResponse> response) {
                if (response.code() == 200 && response.body() != null) {
                    Utils.dismissProgressDialog();
                    BookingAcceptRejectResponse resp = response.body();

                    if (isAccepted) {
                        LatLng latLng = new LatLng(Double.parseDouble(resp.getCustomerDetails().getStartTripLat()),
                                Double.parseDouble(resp.getCustomerDetails().getStartTripLong()));
                        pickupAddress.setText(""+Utils.getAddress(getActivity(),latLng));

                        LatLng latLngD = new LatLng(Double.parseDouble(resp.getCustomerDetails().getEndTripLat()),
                                Double.parseDouble(resp.getCustomerDetails().getEndTripLong()));
                        pickupAddress.setText(""+Utils.getAddress(getActivity(),latLng));

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
    public void onDetach() {
        super.onDetach();
    }


}
