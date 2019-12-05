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
import com.highway.commonretrofit.RestClient;
import com.highway.drivermodule.diverModels.AllDriverTripsRequest;
import com.highway.drivermodule.diverModels.AllDriverTripsResponse;
import com.highway.ownermodule.vehicleOwner.vehicleOwnerAdapter.VehicleOwnerTabAdapter;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.VehicleOwnerCompletedTripRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.VehicleOwnerCompletedTripResponse;
import com.highway.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleDashBoardFragment extends Fragment {

    private TabLayout vehicleOwnerTabMode;
    private ViewPager vehicleOwnerViewPager;

    DashBoardActivity dashBoardActivity;
    VehicleDashBoardFragment vehicleDashBoardFragment;

    VehicleUpComingFragment vehicleUpComingFragment;
    VehicleOnGoingFragment vehicleOnGoingFragment;
    VehiclePendingFragment vehiclePendingFragment;
    VehicleCompletedFragment vehicleCompletedFragment;
    VehicleCancelFragment vehicleCancelFragment;


    List<Fragment> vehicleOwnerfragmentlist = new ArrayList<>();

    public VehicleDashBoardFragment() {
    }

    public static VehicleDashBoardFragment newInstance() {
        VehicleDashBoardFragment fragment = new VehicleDashBoardFragment();
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
        View view =  inflater.inflate(R.layout.fragment_vehicle_dash_board, container, false);

        vehicleOwnerViewPager = view.findViewById(R.id.VehicleOwnerViewPager);
        vehicleOwnerTabMode= view.findViewById(R.id.VehicleOwnertabMode);

        vehicleUpComingFragment = new VehicleUpComingFragment();
        vehicleOnGoingFragment = new VehicleOnGoingFragment();
        vehiclePendingFragment = new VehiclePendingFragment();
        vehicleCompletedFragment = new VehicleCompletedFragment();
        vehicleCancelFragment = new VehicleCancelFragment();

        vehicleOwnerfragmentlist.add(vehicleUpComingFragment);
        vehicleOwnerfragmentlist.add(vehicleOnGoingFragment);
        vehicleOwnerfragmentlist.add(vehiclePendingFragment);
        vehicleOwnerfragmentlist.add(vehicleCompletedFragment);
        vehicleOwnerfragmentlist.add(vehicleCancelFragment);

        VehicleOwnerTabAdapter vehicleOwnerTabAdapter = new VehicleOwnerTabAdapter(getActivity()
                .getSupportFragmentManager(),vehicleOwnerfragmentlist);

        vehicleOwnerViewPager.setAdapter(vehicleOwnerTabAdapter);
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
        getVehivleOwnerCompletedDetail();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        dashBoardActivity = (DashBoardActivity) getActivity();
    }


    public void getVehivleOwnerCompletedDetail(){

        AllDriverTripsRequest allDriverTripsRequest = new AllDriverTripsRequest();
        allDriverTripsRequest.setUserId("5");

     /*   VehicleOwnerCompletedTripRequest vehicleOwnerCompletedTripRequest = new VehicleOwnerCompletedTripRequest();
        vehicleOwnerCompletedTripRequest.setUserId("5");*/

        Utils.showProgressDialog(getContext());

        RestClient.allDriverTrips(allDriverTripsRequest, new Callback<AllDriverTripsResponse>() {
            @Override
            public void onResponse(Call<AllDriverTripsResponse> call, Response<AllDriverTripsResponse> response) {
                Utils.dismissProgressDialog();

                if (response.body()!=null){
                    if (response.body().getStatus()){

                        /*VehicleOwnerCompletedTripResponse vehicleOwnerCompletedTripResponse = response.body();*/
                        AllDriverTripsResponse allDriverTripsResponse = response.body();
                        if (allDriverTripsResponse != null){

                            if (allDriverTripsResponse.getCompletedTrips()!=null && allDriverTripsResponse.getCompletedTrips().size()>0){
                                dashBoardActivity.setCompletedTrips(allDriverTripsResponse.getCompletedTrips());
                            }
                           updateAllVehicleOwnerFragment();
                        }
                    }
                }else{
                    Toast.makeText(dashBoardActivity, "Response failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AllDriverTripsResponse> call, Throwable t) {
                Toast.makeText(dashBoardActivity, "failed", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void updateAllVehicleOwnerFragment(){

    }

}
