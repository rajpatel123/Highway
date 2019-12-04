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
import com.highway.databinding.FragmentDriverCancelBinding;
import com.highway.drivermodule.adapter.CancelTripAdapter;
import com.highway.drivermodule.adapter.OngoingTripAdapter;
import com.highway.drivermodule.diverModels.AllDriverTripsRequest;
import com.highway.drivermodule.diverModels.AllDriverTripsResponse;
import com.highway.drivermodule.diverModels.CancelTrip;
import com.highway.drivermodule.diverModels.OngoingTrip;
import com.highway.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DriverCancelFragment extends Fragment {
    private List<CancelTrip> cancelTrips = new ArrayList<>();
    FragmentDriverCancelBinding binding;
    RecyclerView recyclerView;
    CancelTripAdapter cancelTripAdapter;
    DashBoardActivity dashBoardActivity;


    public DriverCancelFragment() {
        // Required empty public constructor
    }

    public static DriverCancelFragment newInstance() {
        DriverCancelFragment fragment = new DriverCancelFragment();
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
                inflater, R.layout.fragment_driver_cancel, container, false);
        View view = binding.getRoot();

        recyclerView = view.findViewById(R.id.cancel);

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

    public void updateList(List<CancelTrip> cancelTrips) {

        if (this.cancelTrips != null && this.cancelTrips.size() > 0) {
            cancelTripAdapter = new CancelTripAdapter(this.cancelTrips, getContext());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(cancelTripAdapter);


        }

    }


}
