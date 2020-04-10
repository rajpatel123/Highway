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
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.userTrip.CompletedTrip;
import com.highway.drivermodule.driverActivity.DriverAllTripsActivity;
import com.highway.drivermodule.driverActivity.TripDetailsForDriverActivity;
import com.highway.drivermodule.driverAdapter.OnCompletedTripAdapterForDriver;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class CompletedFragmentForDriver extends Fragment {
    private List<CompletedTrip> completedTrips = new ArrayList<>();
    RecyclerView completedRecyclerForDriver;
    DriverAllTripsActivity dashBoardActivity;
    OnCompletedTripAdapterForDriver onCompletedTripAdapterForDriver;
    Context context;

    public CompletedFragmentForDriver() {
        // Required empty public constructor
    }

    public static CompletedFragmentForDriver newInstance() {
        CompletedFragmentForDriver fragment = new CompletedFragmentForDriver();
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
        View view = inflater.inflate(R.layout.fragment_driver_completed, container, false);
        completedRecyclerForDriver = view.findViewById(R.id.CompletedRecyclerForDriver);

        tripDetailforDriver();
        return view;
    }

    private void tripDetailforDriver() {
        onCompletedTripAdapterForDriver.setTripDetailListInterface(new OnCompletedTripAdapterForDriver.TripDetailListInterface() {
            @Override
            public void TripDetailList(String title) {
                Intent intent = new Intent(getActivity(), TripDetailsForDriverActivity.class);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);


                intent.putExtra("SourceAddLatlog", completedTrips.get(0).getSourceLat());
                intent.putExtra("SourceAddLongitude",completedTrips.get(0).getSourceLong());
                intent.putExtra("DestAddLatlog", completedTrips.get(0).getSourceLat());
                intent.putExtra("DestAddLongitude", completedTrips.get(0).getDestinationLong());

                intent.putExtra("CompleteDate", completedTrips.get(0).getEndDate());
                intent.putExtra("PickupTime", completedTrips.get(0).getPickupTime());
                intent.putExtra("DropTime", completedTrips.get(0).getDropTime());
                intent.putExtra("VehicleName", completedTrips.get(0).getVehicleName());
                intent.putExtra("VehicleNumber", completedTrips.get(0).getVehicleNumber());
                intent.putExtra("UserName",completedTrips.get(0).getName());
                intent.putExtra("Faircharge",completedTrips.get(0).getFare());

                getActivity().startActivity(intent);



            }
        });
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

    public void completedUpdatedTripListForDriver(List<CompletedTrip> completedTrips) {
        if (completedTrips != null && completedTrips.size() > 0) {
            onCompletedTripAdapterForDriver = new OnCompletedTripAdapterForDriver(completedTrips, getContext());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            completedRecyclerForDriver.setLayoutManager(layoutManager);
            completedRecyclerForDriver.setItemAnimator(new DefaultItemAnimator());
            completedRecyclerForDriver.setAdapter(onCompletedTripAdapterForDriver);
        } else {
          //  Toast.makeText(dashBoardActivity, "Something went wrong for completed trip list for Driver", Toast.LENGTH_SHORT).show();

        }

    }

}



