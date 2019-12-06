package com.highway.drivermodule.driverFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.drivermodule.driverAdapter.DriverOnGoingTripAdapter;
import com.highway.common.base.commonModel.customer_diver_owner_Models_class.OngoingTrip;

import java.util.ArrayList;
import java.util.List;


public class DriverOnGoingFragment extends Fragment {
    private List<OngoingTrip> ongoingTrips = new ArrayList<>();
    RecyclerView recyclerViewNew;
    DashBoardActivity dashBoardActivity;
    DriverOnGoingTripAdapter driverOnGoingTripAdapter;
    private Context context;

    public DriverOnGoingFragment() {
        // Required empty public constructor
    }

       public static DriverOnGoingFragment newInstance() {
        DriverOnGoingFragment fragment = new DriverOnGoingFragment();
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
        LayoutInflater inflater1 = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater1.inflate(R.layout.fragment_driver_on_going, container ,false);

        recyclerViewNew = view.findViewById(R.id.DriverOnGoingRecyclerView);


        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dashBoardActivity = (DashBoardActivity) getActivity();

    }
    public void updateListOnGoing(List<OngoingTrip> ongoingTrips) {
            if (ongoingTrips != null && ongoingTrips.size() > 0) {
                driverOnGoingTripAdapter = new DriverOnGoingTripAdapter(ongoingTrips, getContext());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerViewNew.setLayoutManager(layoutManager);
                recyclerViewNew.setItemAnimator(new DefaultItemAnimator());
                recyclerViewNew.setAdapter(driverOnGoingTripAdapter);


            }





    }




    @Override
    public void onDetach() {
        super.onDetach();
    }


    }



