package com.highway.drivermodule.driverFragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import com.highway.drivermodule.driverAdapter.DriverOnCompletedTripAdapter;
import com.highway.common.base.commonModel.customer_diver_owner_Models_class.CompletedTrip;

import java.util.ArrayList;
import java.util.List;


public class DriverCompletedFragment extends Fragment {
    private List<CompletedTrip> completedTrips = new ArrayList<>();
    RecyclerView recyclerViewNew;
    DashBoardActivity dashBoardActivity;
    DriverOnCompletedTripAdapter driverOnCompletedTripAdapter;
    Context context;

    public DriverCompletedFragment() {
        // Required empty public constructor
    }


    public static DriverCompletedFragment newInstance() {
        DriverCompletedFragment fragment = new DriverCompletedFragment();
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

        LayoutInflater inflater1 = (LayoutInflater) dashBoardActivity.getSystemService(dashBoardActivity.LAYOUT_INFLATER_SERVICE);
        View view = inflater1.inflate(R.layout.fragment_driver_up_coming, container ,false);
        recyclerViewNew = view.findViewById(R.id.completedRecycler);
        Log.d("OncreateView ", "completed");

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

    public void updateList(List<CompletedTrip> completedTrips) {
        Log.d("OncreateView ", "completed");

        if (completedTrips != null &&completedTrips.size() > 0) {
            driverOnCompletedTripAdapter = new DriverOnCompletedTripAdapter(completedTrips, getContext());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerViewNew.setLayoutManager(layoutManager);
            recyclerViewNew.setItemAnimator(new DefaultItemAnimator());
            recyclerViewNew.setAdapter(driverOnCompletedTripAdapter);


        } else {
            Toast.makeText(dashBoardActivity, "Something went wrong", Toast.LENGTH_SHORT).show();

        }







        }


    }



