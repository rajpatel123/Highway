package com.highway.drivermodule.driverFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.userTrip.CancelTrip;
import com.highway.drivermodule.driverActivity.CancelTripDetailsByDriverActivity;
import com.highway.drivermodule.driverActivity.DriverAllTripsActivity;
import com.highway.drivermodule.driverAdapter.CancelTripAdapterForDriver;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class CancelFragmentForDriver extends Fragment {
    private List<CancelTrip> cancelTrips = new ArrayList<>();
    private RecyclerView recyclerViewForDriver;
    CancelTripAdapterForDriver cancelTripAdapterForDriver;
    DriverAllTripsActivity dashBoardActivity;
    Context context;


    public CancelFragmentForDriver() {
        // Required empty public constructor
    }

    public static CancelFragmentForDriver newInstance() {
        CancelFragmentForDriver fragment = new CancelFragmentForDriver();
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
        View view = inflater.inflate(R.layout.fragment_driver_cancel, container ,false);
        recyclerViewForDriver = view.findViewById(R.id.CancelRecyclerForDriver);

        //cancelTripDetails();
        return view;
    }

    private void cancelTripDetails() {
        if (cancelTrips != null && cancelTrips.size() > 0) {
            if (cancelTripAdapterForDriver != null) {
                cancelTripAdapterForDriver.setTripDetailListInterface(new CancelTripAdapterForDriver.CancleTripDetailInterface() {
                    @Override
                    public void TripDetailListClick(String sourceLat, String sourceLong, String destinationLat, String destinationLong,
                                                    String name, String role, String vehicleName, String vehicleNumber, String fare,
                                                    String status, String tripType, String startDate, String endDate, String pickupTime,
                                                    String dropTime) {

                        Intent intent = new Intent(getActivity(), CancelTripDetailsByDriverActivity.class);
                        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);

                        intent.putExtra("sourceLat", sourceLat);
                        intent.putExtra("sourceLong", sourceLong);
                        intent.putExtra("destinationLat", destinationLat);
                        intent.putExtra("destinationLong", destinationLong);
                        intent.putExtra("name", name);
                        intent.putExtra("role", role);
                        intent.putExtra("vehicleName", vehicleName);
                        intent.putExtra("vehicleNumber", vehicleNumber);
                        intent.putExtra("fare", fare);
                        intent.putExtra("status", status);
                        intent.putExtra("tripType", tripType);
                        intent.putExtra("startDate", startDate);
                        intent.putExtra("endDate", endDate);
                        intent.putExtra("pickupTime", pickupTime);
                        intent.putExtra("dropTime", dropTime);

                        getActivity().startActivity(intent);


                    }
                });
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dashBoardActivity = (DriverAllTripsActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void cancleUpdatedTripListForDriver(List<CancelTrip> cancelTrips) {
       // Log.d("OncreateView ", "cancelF");
        if (cancelTrips != null && cancelTrips.size() > 0) {
            cancelTripAdapterForDriver = new CancelTripAdapterForDriver(cancelTrips, getContext());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerViewForDriver.setLayoutManager(layoutManager);
            recyclerViewForDriver.setItemAnimator(new DefaultItemAnimator());
            recyclerViewForDriver.setAdapter(cancelTripAdapterForDriver);

        } else {
         //   Toast.makeText(dashBoardActivity, "Something went wrong for cancle trip for driver", Toast.LENGTH_SHORT).show();

        }
    }


}
