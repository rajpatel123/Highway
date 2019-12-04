package com.highway.drivermodule.driverFragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.highway.R;
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.commonretrofit.RestClient;
import com.highway.drivermodule.adapter.OncompletedTripAdapter;
import com.highway.drivermodule.diverModels.AllDriverTripsRequest;
import com.highway.drivermodule.diverModels.AllDriverTripsResponse;
import com.highway.drivermodule.diverModels.CompletedTrip;
import com.highway.drivermodule.driverAdapter.DriverFragmentAdapter;
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

        DriverFragmentAdapter driverFragmentAdapter = new DriverFragmentAdapter(getActivity().
                getSupportFragmentManager(), driverFragmentList);

        driverViewPager.setAdapter(driverFragmentAdapter);
        driverTabLayout.setupWithViewPager(driverViewPager);
        driverViewPager.setOffscreenPageLimit(5);
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getCompletedDetail();

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void getCompletedDetail() {

        AllDriverTripsRequest allDriverTripsRequest = new AllDriverTripsRequest();
        allDriverTripsRequest.setUserId("3");

        Utils.showProgressDialog(getContext());
        RestClient.allDriverTrips(allDriverTripsRequest, new Callback<AllDriverTripsResponse>() {
            @Override
            public void onResponse(Call<AllDriverTripsResponse> call, Response<AllDriverTripsResponse> response) {
                Utils.dismissProgressDialog();


                if (response.body() != null) {

                    AllDriverTripsResponse allDriverTripsResponse = response.body();

                        if (allDriverTripsResponse != null) {
                            if (allDriverTripsResponse.getCancelTrips() != null && allDriverTripsResponse.getCancelTrips().size() > 0) {
                                dashBoardActivity.setCancelTrips(allDriverTripsResponse.getCancelTrips());
                            }
                            if (allDriverTripsResponse.getCompletedTrips() != null && allDriverTripsResponse.getCompletedTrips().size() > 0) {
                                dashBoardActivity.setCompletedTrips(allDriverTripsResponse.getCompletedTrips());
                            }
                            if (allDriverTripsResponse.getOngoingTrips() != null && allDriverTripsResponse.getOngoingTrips().size() > 0) {
                                dashBoardActivity.setOngoingTrips(allDriverTripsResponse.getOngoingTrips());
                            }
                            if (allDriverTripsResponse.getUpcomingTrips() != null && allDriverTripsResponse.getUpcomingTrips().size() > 0) {
                                dashBoardActivity.setUpcomingTrips(allDriverTripsResponse.getUpcomingTrips());
                            }

                            updateAllFragment();

                        }





                } else {
                    Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();

                }

            }

            public void updateAllFragment() {
                driverCancelFragment.updateList(dashBoardActivity.getCancelTrips());
                driverOnGoingFragment.updateList(dashBoardActivity.getOngoingTrips());
                driverUpComingFragment.updateList(dashBoardActivity.getUpcomingTrips());
                driverCompletedFragment.updateList(dashBoardActivity.getCompletedTrips());


            }

            @Override
            public void onFailure(Call<AllDriverTripsResponse> call, Throwable t) {
                Toast.makeText(getActivity(), " Failure", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
