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
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.CompletedTrip;
import com.highway.drivermodule.driverAdapter.OnCompletedTripAdapterForDriver;

import java.util.ArrayList;
import java.util.List;


public class CompletedFragmentForDriver extends Fragment {
    private List<CompletedTrip> completedTrips = new ArrayList<>();
    RecyclerView completedRecyclerForDriver;
    DashBoardActivity dashBoardActivity;
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

    public void completedUpdatedTripListForDriver(List<CompletedTrip> completedTrips) {
        if (completedTrips != null && completedTrips.size() > 0) {
            onCompletedTripAdapterForDriver = new OnCompletedTripAdapterForDriver(completedTrips, getContext());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            completedRecyclerForDriver.setLayoutManager(layoutManager);
            completedRecyclerForDriver.setItemAnimator(new DefaultItemAnimator());
            completedRecyclerForDriver.setAdapter(onCompletedTripAdapterForDriver);
        } else {
            Toast.makeText(dashBoardActivity, "Something went wrong for completed trip list for Driver", Toast.LENGTH_SHORT).show();

        }

    }

}



