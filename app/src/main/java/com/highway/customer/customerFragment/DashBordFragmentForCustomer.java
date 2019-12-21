package com.highway.customer.customerFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.highway.R;
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.AllHighwayTripsRequest;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.AllHighwayTripsResponse;
import com.highway.commonretrofit.RestClient;
import com.highway.customer.customerAdapter.FragmentTabModeAdapterForCustomer;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBordFragmentForCustomer extends Fragment {
    private TabLayout myBookingTabLayout;
    private ViewPager myBookingViewPager;
    CancelFragmentForCustomer cancelFragmentForCustomer;
    CompletedFragmentForCustomer completedFragmentForCustomer;
    OnGoingFragmentForCustomer onGoingFragmentForCustomer;
    UpCommingFragmentForCustomer upCommingFragmentForCustomer;
    PendingFragmentForCustomer pendingFragmentForCustomer;
    DashBoardActivity dashBoardActivity;

    List<Fragment> customerfragmentList = new ArrayList<>();
    private String userId;


    public static DashBordFragmentForCustomer newInstance() {
        DashBordFragmentForCustomer fragment = new DashBordFragmentForCustomer();
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
        View view = inflater.inflate(R.layout.fragment_customer_dash_bord, container, false);

        myBookingTabLayout = view.findViewById(R.id.tabModeOfMyBooking);
        myBookingViewPager = view.findViewById(R.id.myBookingViewPager);

        upCommingFragmentForCustomer = new UpCommingFragmentForCustomer();
        onGoingFragmentForCustomer = new OnGoingFragmentForCustomer();
        pendingFragmentForCustomer = new PendingFragmentForCustomer();
        completedFragmentForCustomer = new CompletedFragmentForCustomer();
        cancelFragmentForCustomer = new CancelFragmentForCustomer();

        customerfragmentList.add(upCommingFragmentForCustomer);
        customerfragmentList.add(onGoingFragmentForCustomer);
        customerfragmentList.add(pendingFragmentForCustomer);
        customerfragmentList.add(completedFragmentForCustomer);
        customerfragmentList.add(cancelFragmentForCustomer);

        FragmentTabModeAdapterForCustomer fragmentAdapter = new FragmentTabModeAdapterForCustomer(getActivity().
                getSupportFragmentManager(), customerfragmentList);

        myBookingViewPager.setAdapter(fragmentAdapter);
        myBookingViewPager.setOffscreenPageLimit(5);
        myBookingTabLayout.setupWithViewPager(myBookingViewPager);

        myBookingTabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
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
        myBookingViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        getAllTripByCustomer();
    }

    public void getAllTripByCustomer() {

        AllHighwayTripsRequest allHighwayTripsRequest = new AllHighwayTripsRequest();
        userId = HighwayPrefs.getString(getContext(), Constants.ID);
        /* allHighwayTripsRequest.setUserId(userId);*/
        allHighwayTripsRequest.setUserId("5");

        RestClient.allCustomerTrip(allHighwayTripsRequest, new Callback<AllHighwayTripsResponse>() {
            @Override
            public void onResponse(Call<AllHighwayTripsResponse> call, Response<AllHighwayTripsResponse> response) {

                if (response.body() != null) {
                    if (response.body().getStatus()) {

                        AllHighwayTripsResponse allHighwayTripsResponse = response.body();
                        if (allHighwayTripsResponse != null) {
                            if (allHighwayTripsResponse.getCompletedTrips() != null && allHighwayTripsResponse.getCompletedTrips().size() > 0) {
                                dashBoardActivity.setCompletedTrips(allHighwayTripsResponse.getCompletedTrips());
                            }
                            if (allHighwayTripsResponse.getOngoingTrips() != null && allHighwayTripsResponse.getOngoingTrips().size() > 0) {
                                dashBoardActivity.setOngoingTrips(allHighwayTripsResponse.getOngoingTrips());
                            }
                            if (allHighwayTripsResponse.getUpcomingTrips() != null && allHighwayTripsResponse.getUpcomingTrips().size() > 0) {
                                dashBoardActivity.setUpcomingTrips(allHighwayTripsResponse.getUpcomingTrips());
                            }
                            if (allHighwayTripsResponse.getCancelTrips() != null && allHighwayTripsResponse.getCancelTrips().size() > 0) {
                                dashBoardActivity.setCancelTrips(allHighwayTripsResponse.getCancelTrips());
                            }
                            updateAllCustomerFragment();
                        }
                    }
                } else {
                    Toast.makeText(dashBoardActivity, "response failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AllHighwayTripsResponse> call, Throwable t) {
                Toast.makeText(dashBoardActivity, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateAllCustomerFragment() {
        completedFragmentForCustomer.completedUpdatedTripListForCustomer(dashBoardActivity.getCompletedTrips());
        onGoingFragmentForCustomer.ongoingUpdatedTripListForCustomer(dashBoardActivity.getOngoingTrips());
        cancelFragmentForCustomer.cancelUpdatedTripListForCustomer(dashBoardActivity.getCancelTrips());
        upCommingFragmentForCustomer.upcomingUpdatedTripListForCustomer(dashBoardActivity.getUpcomingTrips());
    }
}
