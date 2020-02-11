package com.highway.drivermodule.driverFragment;

import android.animation.ValueAnimator;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.CancelTrip;
import com.highway.drivermodule.driverAdapter.CancelTripAdapterForDriver;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class IncomingRequestFragmentForDriver extends Fragment {
    private Button btnReject;
    private Button btnAccept;
    private TextView lblCount;
    private CircleImageView imgUser;
    private TextView lblUserName;
    private RatingBar ratingUser;
    private TextView pickupAddress;
    private TextView dropAddress;
    private TextView lblLocationDistance;
    private LinearLayout pickupAddressLayout;
    private LinearLayout dropAddressLayout;

    private TextView lblCarType;

    Integer arrivalTime = 0;
    MediaPlayer mPlayer;


    Context context;
    CountDownTimer countDownTimer;

    private List<CancelTrip> cancelTrips = new ArrayList<>();
    private RecyclerView recyclerViewForDriver;
    CancelTripAdapterForDriver cancelTripAdapterForDriver;
    DashBoardActivity dashBoardActivity;
    private int time_to_left = 60;


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

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_incoming_call, container, false);


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

        mPlayer = MediaPlayer.create(getActivity(), R.raw.alert_tone);
        init();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dashBoardActivity = (DashBoardActivity) getActivity();
    }

    void init() {
//        data = BaseActivity.DATUM;
//        if (data != null) {
////            lblUserName.setText(String.format("%s %s", data.getUser().getFirstName(), data.getUser().getLastName()));
////            ratingUser.setRating(Float.parseFloat(data.getUser().getRating()));
////            pickupAddress.setText(data.getSAddress());
////            lblCarType.setText("Car Type: " + data.getServicetype().getName());
////            dropAddress.setText(data.getDAddress());
////           // Glide.with(activity()).load(BuildConfig.BASE_IMAGE_URL + data.getUser().getPicture()).apply(RequestOptions.placeholderOf(R.drawable.user).dontAnimate().error(R.drawable.user)).into(imgUser);
////            LatLng providerLat = new LatLng(Double.parseDouble(HighwayPrefs.getString(context, "current_latitude")), Double.parseDouble(HighwayPrefs.getString(context, "current_longitude")));
////            LatLng source = new LatLng(data.getSLatitude(), data.getSLongitude());
////
////            ((MainActivity) context).drawDirectionToStop(data.getRouteKey());
////
////            if (data.getServiceRequired() != null && data.getServiceRequired().equalsIgnoreCase("rental")) {
////                dropAddressLayout.setVisibility(View.GONE);
////            }
////
////            getDistance(providerLat, source);
//            if (!mPlayer.isPlaying())
//                mPlayer.start();
//        }


        if (!mPlayer.isPlaying())
            mPlayer.start();

        countDownTimer = new CountDownTimer(time_to_left * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                lblCount.setText(String.valueOf(millisUntilFinished / 1000));
                setTvZoomInOutAnimation(lblCount);
            }

            public void onFinish() {
                stopMediaPlayer();
//                Intent intent = new Intent(INTENT_FILTER);
//                context.sendBroadcast(intent);
            }
        };

        countDownTimer.start();
    }


    private void setTvZoomInOutAnimation(final TextView textView) {
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
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


}
