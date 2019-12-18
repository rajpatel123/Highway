package com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment;

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
import com.highway.ownermodule.vehicleOwner.vehicleOwnerAdapter.FragmentTabAdapterForVehicleOwner;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;
import com.highway.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoardFragmentForVehicleOwner extends Fragment {

    private TabLayout vehicleOwnerTabMode;
    private ViewPager vehicleOwnerViewPager;



    DashBoardFragmentForVehicleOwner dashBoardFragmentForVehicleOwner;
    DashBoardActivity dashBoardActivity;
    UpComingFragmentForVehicleOwner upComingFragmentForVehicleOwner;
    OnGoingFragmentForVehicleOwner onGoingFragmentForVehicleOwner;
    PendingFragmentForVehicleOwner pendingFragmentForVehicleOwner;
    CompletedFragmentForVehicleOwner completedFragmentForVehicleOwner;
    CancelFragmentForVehicleOwner cancelFragmentForVehicleOwner;


    List<Fragment> vehicleOwnerfragmentlist = new ArrayList<>();
    private String userId;

    public DashBoardFragmentForVehicleOwner() {
    }

    public static DashBoardFragmentForVehicleOwner newInstance() {
        DashBoardFragmentForVehicleOwner fragment = new DashBoardFragmentForVehicleOwner();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vehicle_dash_board, container, false);

        vehicleOwnerViewPager = view.findViewById(R.id.VehicleOwnerViewPager);
        vehicleOwnerTabMode = view.findViewById(R.id.VehicleOwnertabMode);

        upComingFragmentForVehicleOwner = new UpComingFragmentForVehicleOwner();
        onGoingFragmentForVehicleOwner = new OnGoingFragmentForVehicleOwner();
        pendingFragmentForVehicleOwner = new PendingFragmentForVehicleOwner();
        completedFragmentForVehicleOwner = new CompletedFragmentForVehicleOwner();
        cancelFragmentForVehicleOwner = new CancelFragmentForVehicleOwner();

        vehicleOwnerfragmentlist.add(upComingFragmentForVehicleOwner);
        vehicleOwnerfragmentlist.add(onGoingFragmentForVehicleOwner);
        vehicleOwnerfragmentlist.add(pendingFragmentForVehicleOwner);
        vehicleOwnerfragmentlist.add(completedFragmentForVehicleOwner);
        vehicleOwnerfragmentlist.add(cancelFragmentForVehicleOwner);

        FragmentTabAdapterForVehicleOwner fragmentTabAdapterForVehicleOwner = new FragmentTabAdapterForVehicleOwner(getActivity()
                .getSupportFragmentManager(), vehicleOwnerfragmentlist);

        vehicleOwnerViewPager.setAdapter(fragmentTabAdapterForVehicleOwner);
        vehicleOwnerViewPager.setOffscreenPageLimit(5);
        vehicleOwnerTabMode.setupWithViewPager(vehicleOwnerViewPager);

        vehicleOwnerTabMode.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
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
        vehicleOwnerViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        getVehivleOwnerCompletedDetail();

    }

    public void getVehivleOwnerCompletedDetail() {

        AllHighwayTripsRequest allHighwayTripsRequest = new AllHighwayTripsRequest();
        userId = HighwayPrefs.getString(getContext(), Constants.ID);
        allHighwayTripsRequest.setUserId(userId);
        /*allHighwayTripsRequest.setUserId("5");*/

     /*   VehicleOwnerCompletedTripRequest vehicleOwnerCompletedTripRequest = new VehicleOwnerCompletedTripRequest();
        vehicleOwnerCompletedTripRequest.setUserId("5");*/

        Utils.showProgressDialog(getContext());

        RestClient.allVehicleOwnerCompletedTrip(allHighwayTripsRequest, new Callback<AllHighwayTripsResponse>() {
            @Override
            public void onResponse(Call<AllHighwayTripsResponse> call, Response<AllHighwayTripsResponse> response) {
                Utils.dismissProgressDialog();

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
                            updateAllVehicleOwnerFragment();
                        }
                    }
                } else {
                    Toast.makeText(dashBoardActivity, "Response failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AllHighwayTripsResponse> call, Throwable t) {
                Toast.makeText(dashBoardActivity, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateAllVehicleOwnerFragment() {
        completedFragmentForVehicleOwner.completedUpdatedTripList(dashBoardActivity.getCompletedTrips(), this);
        onGoingFragmentForVehicleOwner.vehicleOnGoingUpdateList(dashBoardActivity.getOngoingTrips());
        cancelFragmentForVehicleOwner.vehicleCancleUpdatedTripList(dashBoardActivity.getCancelTrips());
        upComingFragmentForVehicleOwner.vehicleUpcomingUpdatedTripList(dashBoardActivity.getUpcomingTrips());

    }

}
