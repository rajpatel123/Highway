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
import com.highway.commonretrofit.RestClient;
import com.highway.common.base.commonModel.customer_diver_owner_Models_class.AllHighwayTripsRequest;
import com.highway.common.base.commonModel.customer_diver_owner_Models_class.AllHighwayTripsResponse;
import com.highway.drivermodule.driverAdapter.DriverFragmentTabModeAdapter;
import com.highway.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DriverDashBoardFragment extends Fragment {

    private TabLayout driverTabLayout;
    private ViewPager driverViewPager;

    DriverUpComingFragment driverUpComingFragment;
    DashBoardActivity dashBoardActivity;
    DriverOnGoingFragment driverOnGoingFragment;
    DriverPendingFragment driverPendingFragment;
    DriverCompletedFragment driverCompletedFragment;
    DriverCancelFragment driverCancelFragment;

    List<Fragment> driverFragmentList = new ArrayList<>();

    public DriverDashBoardFragment() {
        // Required empty public constructor
    }


    public static DriverDashBoardFragment newInstance() {
        DriverDashBoardFragment fragment = new DriverDashBoardFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_driver_dash_board, container, false);

        driverTabLayout = view.findViewById(R.id.drivertabMode);
        driverViewPager = view.findViewById(R.id.driverViewPager);
        driverViewPager.setOffscreenPageLimit(6);

        driverUpComingFragment = new DriverUpComingFragment();
        driverCancelFragment = new DriverCancelFragment();
        driverCompletedFragment = new DriverCompletedFragment();
        driverOnGoingFragment = new DriverOnGoingFragment();
        driverPendingFragment = new DriverPendingFragment();

        driverFragmentList.add(driverUpComingFragment);
        driverFragmentList.add(driverCancelFragment);
        driverFragmentList.add(driverCompletedFragment);
        driverFragmentList.add(driverOnGoingFragment);
        driverFragmentList.add(driverPendingFragment);

        DriverFragmentTabModeAdapter driverFragmentTabModeAdapter = new DriverFragmentTabModeAdapter(getActivity().
                getSupportFragmentManager(), driverFragmentList);

        driverViewPager.setAdapter(driverFragmentTabModeAdapter);
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

    @Override
    public void onStart() {
        super.onStart();
        getCompletedDetail();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        dashBoardActivity = (DashBoardActivity) getActivity();

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void getCompletedDetail() {

        AllHighwayTripsRequest allHighwayTripsRequest = new AllHighwayTripsRequest();
        allHighwayTripsRequest.setUserId("3");

        Utils.showProgressDialog(getContext());
        RestClient.allDriverTrips(allHighwayTripsRequest, new Callback<AllHighwayTripsResponse>() {
            @Override
            public void onResponse(Call<AllHighwayTripsResponse> call, Response<AllHighwayTripsResponse> response) {
                Utils.dismissProgressDialog();
                if (response.body() != null) {
                    if (response.body().getStatus().booleanValue() == true) {

                        AllHighwayTripsResponse allHighwayTripsResponse = response.body();

                        if (allHighwayTripsResponse != null) {
                            if (allHighwayTripsResponse.getCancelTrips() != null && allHighwayTripsResponse.getCancelTrips().size() > 0) {
                                dashBoardActivity.setCancelTrips(allHighwayTripsResponse.getCancelTrips());
                            }
                            if (allHighwayTripsResponse.getCompletedTrips() != null && allHighwayTripsResponse.getCompletedTrips().size() > 0) {
                                dashBoardActivity.setCompletedTrips(allHighwayTripsResponse.getCompletedTrips());
                            }
                            if (allHighwayTripsResponse.getOngoingTrips() != null && allHighwayTripsResponse.getOngoingTrips().size() > 0) {
                                dashBoardActivity.setOngoingTrips(allHighwayTripsResponse.getOngoingTrips());
                            }
                            if (allHighwayTripsResponse.getUpcomingTrips() != null && allHighwayTripsResponse.getUpcomingTrips().size() > 0) {
                                dashBoardActivity.setUpcomingTrips(allHighwayTripsResponse.getUpcomingTrips());
                            }

                            updateAllFragment();

                        }


                    }


                } else {
                    Toast.makeText(dashBoardActivity, "some thig went wrong", Toast.LENGTH_SHORT).show();

                }

            }



            @Override
            public void onFailure(Call<AllHighwayTripsResponse> call, Throwable t) {
                Toast.makeText(dashBoardActivity, " Failure", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void updateAllFragment() {
        driverCancelFragment.updateListCancel(dashBoardActivity.getCancelTrips());
        driverOnGoingFragment.updateListOnGoing(dashBoardActivity.getOngoingTrips());
        driverUpComingFragment.updateListUpComing(dashBoardActivity.getUpcomingTrips());
        driverCompletedFragment.updateList(dashBoardActivity.getCompletedTrips());


    }


}
