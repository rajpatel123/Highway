package com.highway.drivermodule.driverFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.highway.R;
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.GetAllTripByUserIdRequest;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.GetAllTripByUserIdResponse;
import com.highway.commonretrofit.RestClient;
import com.highway.drivermodule.driverAdapter.FragmentTabModeAdapterForDriver;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;
import com.highway.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashBoardFragmentForDriver extends Fragment {

    private TabLayout driverTabLayout;
    private ViewPager driverViewPager;

    DashBoardActivity dashBoardActivity;
    UpComingFragmentForDriver upComingFragmentForDriver;
    OnGoingFragmentForDriver onGoingFragmentForDriver;
    PendingFragmentForDriver pendingFragmentForDriver;
    CompletedFragmentForDriver completedFragmentForDriver;
    CancelFragmentForDriver cancelFragmentForDriver;

    List<Fragment> driverFragmentList = new ArrayList<>();
    private String userId;

    public DashBoardFragmentForDriver() {
        // Required empty public constructor
    }


    public static DashBoardFragmentForDriver newInstance() {
        DashBoardFragmentForDriver fragment = new DashBoardFragmentForDriver();
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
        View view = inflater.inflate(R.layout.fragment_driver_dash_board, container, false);

        driverTabLayout = view.findViewById(R.id.drivertabMode);
        driverViewPager = view.findViewById(R.id.driverViewPager);

        upComingFragmentForDriver = new UpComingFragmentForDriver();
        onGoingFragmentForDriver = new OnGoingFragmentForDriver();
     //   pendingFragmentForDriver = new PendingFragmentForDriver();
        completedFragmentForDriver = new CompletedFragmentForDriver();
        cancelFragmentForDriver = new CancelFragmentForDriver();

        driverFragmentList.add(upComingFragmentForDriver);
        driverFragmentList.add(onGoingFragmentForDriver);
    //    driverFragmentList.add(pendingFragmentForDriver);
        driverFragmentList.add(completedFragmentForDriver);
        driverFragmentList.add(cancelFragmentForDriver);

        FragmentTabModeAdapterForDriver fragmentTabModeAdapterForDriver = new FragmentTabModeAdapterForDriver(getActivity().
                getSupportFragmentManager(), driverFragmentList);

        driverViewPager.setAdapter(fragmentTabModeAdapterForDriver);
        driverViewPager.setOffscreenPageLimit(4);
        driverTabLayout.setupWithViewPager(driverViewPager);

        driverTabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        driverViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return view;

    }
/*
    @Override
    public void onStart() {
        super.onStart();
        getDriverCompletedDetail();

    }

    }*/

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
    public void onResume() {
        super.onResume();
        getDriverCompletedDetail();
    }


    public void getDriverCompletedDetail() {

        GetAllTripByUserIdRequest getAllTripByUserIdRequest = new GetAllTripByUserIdRequest();
        userId = HighwayPrefs.getString(getContext(), Constants.ID);
        /*allHighwayTripsRequest.setUserId(userId);*/
        getAllTripByUserIdRequest.setUserId(userId);

        Utils.showProgressDialog(getContext());
        RestClient.allDriverTrips(getAllTripByUserIdRequest, new Callback<GetAllTripByUserIdResponse>() {
            @Override
            public void onResponse(Call<GetAllTripByUserIdResponse> call, Response<GetAllTripByUserIdResponse> response) {
                Utils.dismissProgressDialog();
                if (response.body() != null && response.body().getStatus()!=null    )   {
                    if (response.body().getStatus()) {
                        GetAllTripByUserIdResponse getAllTripByUserIdResponse = response.body();
                        if (getAllTripByUserIdResponse != null) {
                            if (getAllTripByUserIdResponse.getCancelTrips() != null && getAllTripByUserIdResponse.getCancelTrips().size() > 0) {
                                dashBoardActivity.setCancelTrips(getAllTripByUserIdResponse.getCancelTrips());
                            }
                            if (getAllTripByUserIdResponse.getCompletedTrips() != null && getAllTripByUserIdResponse.getCompletedTrips().size() > 0) {
                                dashBoardActivity.setCompletedTrips(getAllTripByUserIdResponse.getCompletedTrips());
                            }
                            if (getAllTripByUserIdResponse.getOngoingTrips() != null && getAllTripByUserIdResponse.getOngoingTrips().size() > 0) {
                                dashBoardActivity.setOngoingTrips(getAllTripByUserIdResponse.getOngoingTrips());
                            }
                            if (getAllTripByUserIdResponse.getUpcomingTrips() != null && getAllTripByUserIdResponse.getUpcomingTrips().size() > 0) {
                                dashBoardActivity.setUpcomingTrips(getAllTripByUserIdResponse.getUpcomingTrips());
                            }
                            updateAllFragment();
                        }
                    }

                } else {
                    Toast.makeText(dashBoardActivity, "some thig went wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetAllTripByUserIdResponse> call, Throwable t) {
                Toast.makeText(dashBoardActivity, " Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateAllFragment() {
        upComingFragmentForDriver.upcomingUpdatedTripListForDriver(dashBoardActivity.getUpcomingTrips());
        onGoingFragmentForDriver.onGoingUpdatedTripListForDriver(dashBoardActivity.getOngoingTrips());
        completedFragmentForDriver.completedUpdatedTripListForDriver(dashBoardActivity.getCompletedTrips());
        cancelFragmentForDriver.cancleUpdatedTripListForDriver(dashBoardActivity.getCancelTrips());

    }


}
