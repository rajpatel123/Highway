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
import com.highway.drivermodule.adapter.UpComingTripAdapter;
import com.highway.drivermodule.diverModels.UpcomingTrip;

import java.util.ArrayList;
import java.util.List;


public class DriverUpComingFragment extends Fragment {
    private List<UpcomingTrip> upcomingTrips = new ArrayList<>();
    RecyclerView recyclerView;
    DashBoardActivity dashBoardActivity;
    UpComingTripAdapter upComingTripAdapter;
    private Context context;

    public DriverUpComingFragment() {
        // Required empty public constructor
    }

    public static DriverUpComingFragment newInstance() {
        DriverUpComingFragment fragment = new DriverUpComingFragment();
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
      /*  binding = DataBindingUtil.inflate(inflater, R.layout.fragment_driver_up_coming, container, false);
        View view = binding.getRoot();*/
        LayoutInflater inflater1 = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater1.inflate(R.layout.fragment_driver_up_coming, container ,false);

        recyclerView = view.findViewById(R.id.upcomingRecycler);

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

    public void updateListUpComing(List<UpcomingTrip> upcomingTrips) {

        if (upcomingTrips != null && upcomingTrips.size() > 0) {
            upComingTripAdapter = new UpComingTripAdapter(upcomingTrips, getContext());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(upComingTripAdapter);


        } else {
            Toast.makeText(dashBoardActivity, "Something went wrong", Toast.LENGTH_SHORT).show();

        }


    }
}
