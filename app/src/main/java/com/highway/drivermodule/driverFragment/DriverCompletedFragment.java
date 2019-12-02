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
import com.highway.databinding.FragmentDriverCompletedBinding;
import com.highway.databinding.FragmentDriverOnGoingBinding;
import com.highway.drivermodule.adapter.OncompletedTripAdapter;
import com.highway.drivermodule.adapter.OngoingTripAdapter;
import com.highway.drivermodule.adapter.UpComingTripAdapter;
import com.highway.drivermodule.diverModels.AllDriverTripsRequest;
import com.highway.drivermodule.diverModels.AllDriverTripsResponse;
import com.highway.drivermodule.diverModels.CancelTrip;
import com.highway.drivermodule.diverModels.CompletedTrip;
import com.highway.drivermodule.diverModels.UpcomingTrip;
import com.highway.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DriverCompletedFragment extends Fragment {
    private List<CompletedTrip> completedTrips = new ArrayList<>();
    RecyclerView recyclerViewNew;
    DashBoardActivity dashBoardActivity;
    OncompletedTripAdapter oncompletedTripAdapter;
    FragmentDriverCompletedBinding binding;

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
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_driver_completed, container, false);
        View view = binding.getRoot();

        recyclerViewNew = view.findViewById(R.id.completed);


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
        if (this.completedTrips != null && this.completedTrips.size() > 0) {
            oncompletedTripAdapter = new OncompletedTripAdapter(this.completedTrips, getContext());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerViewNew.setLayoutManager(layoutManager);
            recyclerViewNew.setItemAnimator(new DefaultItemAnimator());
            recyclerViewNew.setAdapter(oncompletedTripAdapter);


        } else {
            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();

        }


    }


}
