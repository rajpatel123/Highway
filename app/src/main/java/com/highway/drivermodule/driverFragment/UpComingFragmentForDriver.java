package com.highway.drivermodule.driverFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.userTrip.UpcomingTrip;
import com.highway.drivermodule.driverActivity.DriverAllTripsActivity;
import com.highway.drivermodule.driverAdapter.UpComingTripAdapterForDriver;

import java.util.ArrayList;
import java.util.List;


public class UpComingFragmentForDriver extends Fragment {
    private List<UpcomingTrip> upcomingTrips = new ArrayList<>();
    RecyclerView upComingRecyclerForDriver;
    DriverAllTripsActivity dashBoardActivity;
    UpComingTripAdapterForDriver upComingTripAdapterForDriver;
    private Context context;

    public UpComingFragmentForDriver() {
        // Required empty public constructor
    }

    public static UpComingFragmentForDriver newInstance() {
        UpComingFragmentForDriver fragment = new UpComingFragmentForDriver();
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
        View view = inflater.inflate(R.layout.fragment_driver_up_coming, container ,false);
        upComingRecyclerForDriver = view.findViewById(R.id.UpComingRecyclerForDriver);
        return view;
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

    public void upcomingUpdatedTripListForDriver(List<UpcomingTrip> upcomingTrips) {
        if (upcomingTrips != null && upcomingTrips.size() > 0) {
            upComingTripAdapterForDriver = new UpComingTripAdapterForDriver(upcomingTrips, getContext());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            upComingRecyclerForDriver.setLayoutManager(layoutManager);
            upComingRecyclerForDriver.setItemAnimator(new DefaultItemAnimator());
            upComingRecyclerForDriver.setAdapter(upComingTripAdapterForDriver);

        } else {
          Toast.makeText(getActivity(), "Something went wrong of upcoming trip list for Driver" , Toast.LENGTH_SHORT).show();
        }


    }
}
