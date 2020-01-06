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
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.CancelTrip;
import com.highway.drivermodule.driverAdapter.CancelTripAdapterForDriver;

import java.util.ArrayList;
import java.util.List;


public class CancelFragmentForDriver extends Fragment {
    private List<CancelTrip> cancelTrips = new ArrayList<>();
    private RecyclerView recyclerViewForDriver;
    CancelTripAdapterForDriver cancelTripAdapterForDriver;
    DashBoardActivity dashBoardActivity;
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

    public void cancleUpdatedTripListForDriver(List<CancelTrip> cancelTrips) {
       // Log.d("OncreateView ", "cancelF");
        if (cancelTrips != null && cancelTrips.size() > 0) {
            cancelTripAdapterForDriver = new CancelTripAdapterForDriver(cancelTrips, getContext());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerViewForDriver.setLayoutManager(layoutManager);
            recyclerViewForDriver.setItemAnimator(new DefaultItemAnimator());
            recyclerViewForDriver.setAdapter(cancelTripAdapterForDriver);

        } else {
            Toast.makeText(dashBoardActivity, "Something went wrong for cancle trip for driver", Toast.LENGTH_SHORT).show();

        }
    }


}
