package com.highway.drivermodule.driverFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.commonretrofit.RestClient;
import com.highway.databinding.FragmentDriverOnGoingBinding;
import com.highway.databinding.FragmentDriverUpComingBinding;
import com.highway.drivermodule.adapter.CancelTripAdapter;
import com.highway.drivermodule.adapter.OngoingTripAdapter;
import com.highway.drivermodule.adapter.UpComingTripAdapter;
import com.highway.drivermodule.diverModels.AllDriverTripsRequest;
import com.highway.drivermodule.diverModels.AllDriverTripsResponse;
import com.highway.drivermodule.diverModels.CancelTrip;
import com.highway.drivermodule.diverModels.OngoingTrip;
import com.highway.drivermodule.diverModels.UpcomingTrip;
import com.highway.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DriverOnGoingFragment extends Fragment {
    private List<OngoingTrip> ongoingTrips = new ArrayList<>();
    RecyclerView recyclerViewNew;
    DashBoardActivity dashBoardActivity;
    OngoingTripAdapter ongoingTripAdapter;
    FragmentDriverOnGoingBinding binding;

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
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_driver_on_going, container, false);
        View view = binding.getRoot();

        recyclerViewNew = view.findViewById(R.id.oncoming);


        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dashBoardActivity = (DashBoardActivity) getActivity();

    }
    public void updateListOnGoing(List<OngoingTrip> ongoingTrips) {
            if (ongoingTrips != null && ongoingTrips.size() > 0) {
                ongoingTripAdapter = new OngoingTripAdapter(ongoingTrips, getContext());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerViewNew.setLayoutManager(layoutManager);
                recyclerViewNew.setItemAnimator(new DefaultItemAnimator());
                recyclerViewNew.setAdapter(ongoingTripAdapter);


            }





    }




    @Override
    public void onDetach() {
        super.onDetach();
    }


    }



