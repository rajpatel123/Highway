package com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment;

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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.highway.R;
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.common.base.commonModel.customer_diver_owner_Models_class.CompletedTrip;
import com.highway.ownermodule.vehicleOwner.vehicleOwnerAdapter.VehicleOwnerCompletedTripAdapter;

import java.util.ArrayList;
import java.util.List;


public class VehicleCompletedFragment extends Fragment {
    VehicleDashBoardFragment vehicleDashBoardFragment;
    List<CompletedTrip> completedTripList = new ArrayList<>();
    RecyclerView completedRecycler;
    DashBoardActivity dashBoardActivity;
    Context context;
    VehicleOwnerCompletedTripAdapter vehicleOwnerCompletedTripAdapter;
    SwipeRefreshLayout swiptorefresh;

    public VehicleCompletedFragment() {

    }


    public static VehicleCompletedFragment newInstance(String param1, String param2) {
        VehicleCompletedFragment fragment = new VehicleCompletedFragment();
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

        View view = inflater.inflate(R.layout.fragment_vehicle_completed, container, false);
        completedRecycler = view.findViewById(R.id.CompletedRecyclerView);
        swiptorefresh = view.findViewById(R.id.swiptorefresh);


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

    public void completedUpdatedTripList(List<CompletedTrip> completedTrips, VehicleDashBoardFragment vehicleDashBoardFragment) {
        if (completedTrips != null && completedTrips.size() > 0) {
            this.vehicleDashBoardFragment = vehicleDashBoardFragment;
            vehicleOwnerCompletedTripAdapter = new VehicleOwnerCompletedTripAdapter(completedTrips, getContext());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            completedRecycler.setLayoutManager(layoutManager);
            completedRecycler.setItemAnimator(new DefaultItemAnimator());
            completedRecycler.setAdapter(vehicleOwnerCompletedTripAdapter);

        } else {
            Toast.makeText(dashBoardActivity, "Some thing is wrong", Toast.LENGTH_SHORT).show();
        }
    }

}
