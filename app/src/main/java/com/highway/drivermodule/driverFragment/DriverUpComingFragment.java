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
import com.highway.common.base.commonModel.registration.RegistrationResponse;
import com.highway.commonretrofit.RestClient;
import com.highway.databinding.FragmentDriverUpComingBinding;
import com.highway.drivermodule.adapter.OngoingTripAdapter;
import com.highway.drivermodule.adapter.UpComingTripAdapter;
import com.highway.drivermodule.diverModels.AllDriverTripsRequest;
import com.highway.drivermodule.diverModels.AllDriverTripsResponse;
import com.highway.drivermodule.diverModels.OngoingTrip;
import com.highway.drivermodule.diverModels.UpcomingTrip;
import com.highway.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DriverUpComingFragment extends Fragment {
    private List<UpcomingTrip> upcomingTrips = new ArrayList<>();
    RecyclerView recyclerView;
    DashBoardActivity dashBoardActivity;
    UpComingTripAdapter upComingTripAdapter;
    FragmentDriverUpComingBinding binding;

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
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_driver_up_coming, container, false);
        View view = binding.getRoot();

        recyclerView = view.findViewById(R.id.upcoming);


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

    public void updateList(List<UpcomingTrip> upcomingTrips) {

        if (this.upcomingTrips != null && this.upcomingTrips.size() > 0) {
            upComingTripAdapter = new UpComingTripAdapter(this.upcomingTrips, getContext());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(upComingTripAdapter);


        } else {
            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();

        }


    }
}
