package com.highway.customer.customerFragment;

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
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.CompletedTrip;
import com.highway.customer.customerAdapter.CompletedTripAdapterForCustomer;
import com.highway.drivermodule.driverAdapter.CancelTripAdapterForDriver;

import java.util.ArrayList;
import java.util.List;


public class CompletedFragmentForCustomer extends Fragment {

    private RecyclerView completedRecyclerforCustomer;
    DashBoardActivity dashBoardActivity;
    CompletedTripAdapterForCustomer completedTripAdapterForCustomer;
    List<CompletedTrip> completedTrips = new ArrayList<>();

    public CompletedFragmentForCustomer() {
        // Required empty public constructor
    }


    public static CompletedFragmentForCustomer newInstance(String param1, String param2) {
        CompletedFragmentForCustomer fragment = new CompletedFragmentForCustomer();
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
        View view = inflater.inflate(R.layout.fragment_customer_completed, container, false);
        completedRecyclerforCustomer = view.findViewById(R.id.completedRecyForCus);
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


    public void completedUpdatedTripListForCustomer(List<CompletedTrip> completedTrips) {
        // Log.d("OncreateView ", "cancelF");
        if (completedTrips != null && completedTrips.size() > 0) {
            completedTripAdapterForCustomer = new CompletedTripAdapterForCustomer(completedTrips, getContext());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            completedRecyclerforCustomer.setLayoutManager(layoutManager);
            completedRecyclerforCustomer.setItemAnimator(new DefaultItemAnimator());
            completedRecyclerforCustomer.setAdapter(completedTripAdapterForCustomer);

        } else {
            Toast.makeText(dashBoardActivity, "No completed data  for customer !", Toast.LENGTH_SHORT).show();

        }
    }

}
